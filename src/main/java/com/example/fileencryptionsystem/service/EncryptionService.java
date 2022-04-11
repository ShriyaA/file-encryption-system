package com.example.fileencryptionsystem.service;

import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

  KeysetHandle handle;

  public EncryptionService() throws GeneralSecurityException, IOException {
    AeadConfig.register();

    //TODO: dont create new if key already exists
    handle = KeysetHandle.generateNew(KeyTemplates.get("AES128_GCM"));
    String keysetFilename = "my_keyset.json";
    CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(new File(keysetFilename)));
  }

  public void encryptFiles(List<EncryptionRequest> encryptionRequests) {
    encryptionRequests.forEach(er -> {
      try {
        encryptFile(er.getInputFilePath(), er.getKey(), er.getOutputFilePath());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void encryptFile(String inputFilePath, String key, String outputFilePath) throws GeneralSecurityException, IOException {
    File inputFile = new File(inputFilePath);
    byte[] plaintext = Files.readAllBytes(inputFile.toPath());

    Aead aead = handle.getPrimitive(Aead.class);
    byte[] cipherText = aead.encrypt(plaintext, key.getBytes(StandardCharsets.UTF_8));

    try (FileOutputStream stream = new FileOutputStream(outputFilePath)) {
      stream.write(cipherText);
    }
  }

  public void decryptFiles(List<EncryptionRequest> encryptionRequests) {
    encryptionRequests.forEach(er -> {
      try {
        decryptFile(er.getInputFilePath(), er.getKey(), er.getOutputFilePath());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void decryptFile(String inputFilePath, String key, String outputFilePath) throws GeneralSecurityException, IOException {
    File inputFile = new File(inputFilePath);
    byte[] cipherText = Files.readAllBytes(inputFile.toPath());

    Aead aead = handle.getPrimitive(Aead.class);
    byte[] plainText = aead.decrypt(cipherText, key.getBytes(StandardCharsets.UTF_8));

    try (FileOutputStream stream = new FileOutputStream(outputFilePath)) {
      stream.write(plainText);
    }
  }

}
