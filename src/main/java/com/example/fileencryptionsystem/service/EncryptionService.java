package com.example.fileencryptionsystem.service;

import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


@Service
@CrossOrigin
public class EncryptionService {

  private final KeysetHandle handle;

  public EncryptionService() throws GeneralSecurityException, IOException {
    AeadConfig.register();

    String keySetPath = "my_keyset.json";
    if(Files.exists(Paths.get(keySetPath))) {
      File keyFile = new File(keySetPath);
      handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile));
    } else {
      handle = KeysetHandle.generateNew(KeyTemplates.get("AES256_GCM"));
      CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(new File(keySetPath)));
    }

  }

  public void encryptFiles(List<EncryptionRequest> encryptionRequests) {
    encryptionRequests.forEach(er -> {
      try {
        encryptFile(er.getInputFilePath(), er.getKey());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void encryptFile(String inputFilePath, String key) throws GeneralSecurityException, IOException {
    File inputFile = new File(inputFilePath);

    byte[] plaintext = Files.readAllBytes(inputFile.toPath());

    Aead aead = handle.getPrimitive(Aead.class);
    byte[] cipherText = aead.encrypt(plaintext, key.getBytes(StandardCharsets.UTF_8));

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-encrypted." + FilenameUtils.getExtension(inputFilePath);
    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();
    FileOutputStream stream = new FileOutputStream(outputFile, false);
    stream.write(cipherText);
  }

  public void decryptFiles(List<DecryptionRequest> decryptionRequests) {
    decryptionRequests.forEach(dr -> {
      try {
        decryptFile(dr.getInputFilePath(), dr.getKey());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void decryptFile(String inputFilePath, String key) throws GeneralSecurityException, IOException {
    File inputFile = new File(inputFilePath);
    byte[] cipherText = Files.readAllBytes(inputFile.toPath());

    Aead aead = handle.getPrimitive(Aead.class);
    byte[] plainText = aead.decrypt(cipherText, key.getBytes(StandardCharsets.UTF_8));

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-decrypted." + FilenameUtils.getExtension(inputFilePath);

    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();
    FileOutputStream stream = new FileOutputStream(outputFilePath, false);
    stream.write(plainText);
  }

}
