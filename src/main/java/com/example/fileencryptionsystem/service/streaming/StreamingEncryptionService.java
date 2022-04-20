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
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;

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
  protected void performEncryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException {
    try (OutputStream ciphertextStream =
        getStreamingAead().newEncryptingStream(new FileOutputStream(outputFile), keyBytes);
        InputStream plaintextStream = new FileInputStream(inputFilePath.toString())) {
      byte[] chunk = new byte[1024];
      int chunkLen;
      while ((chunkLen = plaintextStream.read(chunk)) != -1) {
        ciphertextStream.write(chunk, 0, chunkLen);
      }
    }
  }

  @Override
  protected void performDecryption(Path inputFilePath, File outputFile, byte[] keyBytes)
      throws IOException, GeneralSecurityException {
    InputStream ciphertextStream =
        getStreamingAead().newDecryptingStream(new FileInputStream(inputFilePath.toString()),  keyBytes);

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
