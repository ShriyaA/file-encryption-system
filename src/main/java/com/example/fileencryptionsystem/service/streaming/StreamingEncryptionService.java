package com.example.fileencryptionsystem.service.streaming;

import com.example.fileencryptionsystem.service.IEncryptionService;
import com.google.crypto.tink.StreamingAead;
import com.google.crypto.tink.streamingaead.StreamingAeadConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;

@Slf4j
public abstract class StreamingEncryptionService extends IEncryptionService {

  static {
    try {
      StreamingAeadConfig.register();
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

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-encrypted." + FilenameUtils.getExtension(inputFilePath);
    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();

    try (OutputStream ciphertextStream =
        getStreamingAead().newEncryptingStream(new FileOutputStream(outputFile), key.getBytes(
            StandardCharsets.UTF_8));
        InputStream plaintextStream = new FileInputStream(inputFilePath)) {
      byte[] chunk = new byte[1024];
      int chunkLen;
      while ((chunkLen = plaintextStream.read(chunk)) != -1) {
        ciphertextStream.write(chunk, 0, chunkLen);
      }
    }
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

    InputStream ciphertextStream =
        getStreamingAead().newDecryptingStream(new FileInputStream(inputFilePath),  key.getBytes(StandardCharsets.UTF_8));

    String outputFilePath = File.separator + FilenameUtils.getPath(inputFilePath) + FilenameUtils.getBaseName(inputFilePath) + "-encrypted." + FilenameUtils.getExtension(inputFilePath);
    File outputFile = new File(outputFilePath);
    outputFile.createNewFile();
    OutputStream plaintextStream = new FileOutputStream(outputFile);
    byte[] chunk = new byte[1024];
    int chunkLen;
    while ((chunkLen = ciphertextStream.read(chunk)) != -1) {
      plaintextStream.write(chunk, 0, chunkLen);
    }

    ciphertextStream.close();
    plaintextStream.close();
  }

  protected abstract StreamingAead getStreamingAead();

}
