package com.example.fileencryptionsystem.service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

@Slf4j
public abstract class IEncryptionService {

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

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-encrypted." + FilenameUtils.getExtension(inputFilePath);
    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();

    performEncryption(path, outputFile, key.getBytes(StandardCharsets.UTF_8));
  }

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

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-decrypted." + FilenameUtils.getExtension(inputFilePath);
    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();

    performDecryption(path, outputFile, key.getBytes(StandardCharsets.UTF_8));
  }

  protected abstract void performEncryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException;

  protected abstract void performDecryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException;
}
