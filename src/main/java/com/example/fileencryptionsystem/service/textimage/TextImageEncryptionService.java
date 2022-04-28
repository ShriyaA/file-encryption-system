package com.example.fileencryptionsystem.service.textimage;

import com.example.fileencryptionsystem.service.IEncryptionService;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.aead.AeadConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;

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
  protected void performEncryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException {
    byte[] plaintext = Files.readAllBytes(inputFilePath);
    byte[] cipherText = getAead().encrypt(plaintext, keyBytes);
    FileOutputStream stream = new FileOutputStream(outputFile, false);
    stream.write(cipherText);
  }

  @Override
  protected void performDecryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException {
    byte[] cipherText = Files.readAllBytes(inputFilePath);
    byte[] plainText = getAead().decrypt(cipherText, keyBytes);
    FileOutputStream stream = new FileOutputStream(outputFile, false);
    stream.write(plainText);
  }



  protected abstract Aead getAead();

}
