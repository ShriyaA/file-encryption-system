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

  public File encryptFile(String inputFileString, String key) throws GeneralSecurityException, IOException {
    Path inputFilePath = Paths.get(inputFileString);
    if (Files.notExists(inputFilePath)) {
      log.error("No file exists at " + inputFileString + ". Nothing to encrypt");
      return null;
    }

    if (!Files.isReadable(inputFilePath)) {
      log.error("File at " + inputFileString + " is not readable");
      return null;
    }

    String outputFileString = File.separator + FilenameUtils.getPath(inputFileString) + FilenameUtils.getBaseName(inputFileString) + "-encrypted." + FilenameUtils.getExtension(inputFileString);
    File outputFile = new File(String.valueOf(Paths.get(outputFileString)));
    outputFile.createNewFile();

    try {
      performEncryption(inputFilePath, outputFile, key.getBytes(StandardCharsets.UTF_8));
    } catch(IOException | GeneralSecurityException e) {
      outputFile.delete();
      throw e;
    }
    return outputFile;
  }

  public File decryptFile(String inputFileString, String key) throws GeneralSecurityException, IOException {
    Path inputFilePath = Paths.get(inputFileString);

    if (Files.notExists(inputFilePath)) {
      log.error("No file exists at " + inputFilePath + ". Nothing to decrypt");
      return null;
    }

    if (!Files.isReadable(inputFilePath)) {
      log.error("File at " + inputFilePath + " is not readable");
      return null;
    }

    String outputFileString = File.separator + FilenameUtils.getPath(inputFileString) + FilenameUtils.getBaseName(inputFileString) + "-decrypted." + FilenameUtils.getExtension(inputFileString);
    File outputFile = new File(String.valueOf(Paths.get(outputFileString)));
    outputFile.createNewFile();

    try {
      performDecryption(inputFilePath, outputFile, key.getBytes(StandardCharsets.UTF_8));
    } catch(IOException | GeneralSecurityException e) {
      outputFile.delete();
      throw e;
    }

    return outputFile;
  }

  protected abstract String getKeySetPath();

  protected abstract void performEncryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException;

  protected abstract void performDecryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException;

}
