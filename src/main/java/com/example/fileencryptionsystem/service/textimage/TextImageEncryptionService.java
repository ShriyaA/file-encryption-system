package com.example.fileencryptionsystem.service.textimage;

import com.example.fileencryptionsystem.service.IEncryptionService;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.AeadConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

@Slf4j
public abstract class TextImageEncryptionService extends IEncryptionService {

  static {
    try {
      AeadConfig.register();
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void encryptFile(String inputFilePath, String key) throws GeneralSecurityException, IOException {
    Path path = Paths.get(inputFilePath);
    if (Files.notExists(path)) {
      log.error("No file exists at " + inputFilePath + ". Nothing to encrypt");
      return;
    }

    if (!Files.isReadable(path)) {
      log.error("File at " + inputFilePath + " is not readable");
      return;
    }

    byte[] plaintext = Files.readAllBytes(path);
    byte[] cipherText = getAead().encrypt(plaintext, key.getBytes(StandardCharsets.UTF_8));

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-encrypted." + FilenameUtils.getExtension(inputFilePath);
    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();
    FileOutputStream stream = new FileOutputStream(outputFile, false);
    stream.write(cipherText);
  }

  @Override
  public void decryptFile(String inputFilePath, String key) throws GeneralSecurityException, IOException {
    Path path = Paths.get(inputFilePath);

    if (Files.notExists(Paths.get(inputFilePath))) {
      log.error("No file exists at " + inputFilePath + ". Nothing to decrypt");
      return;
    }

    if (!Files.isReadable(path)) {
      log.error("File at " + inputFilePath + " is not readable");
      return;
    }

    byte[] cipherText = Files.readAllBytes(path);
    byte[] plainText = getAead().decrypt(cipherText, key.getBytes(StandardCharsets.UTF_8));

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-decrypted." + FilenameUtils.getExtension(inputFilePath);
    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();
    FileOutputStream stream = new FileOutputStream(outputFilePath, false);
    stream.write(plainText);
  }

  protected abstract Aead getAead();

}
