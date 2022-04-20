package com.example.fileencryptionsystem.service;

import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
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
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StreamingEncryptionService {

  private final StreamingAead streamingAead;

  public StreamingEncryptionService() throws GeneralSecurityException, IOException {
    StreamingAeadConfig.register();

    String keySetPath = "streaming_AEAD_keyset.json";
    KeysetHandle handle;
    if(Files.exists(Paths.get(keySetPath))) {
      File keyFile = new File(keySetPath);
      handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile));
    } else {
      handle = KeysetHandle.generateNew(KeyTemplates.get("AES256_CTR_HMAC_SHA256_1MB"));
      CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(new File(keySetPath)));
    }

    streamingAead = handle.getPrimitive(StreamingAead.class);
  }


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
        streamingAead.newEncryptingStream(new FileOutputStream(outputFile), key.getBytes(
            StandardCharsets.UTF_8));
        InputStream plaintextStream = new FileInputStream(inputFilePath)) {
      byte[] chunk = new byte[1024];
      int chunkLen;
      while ((chunkLen = plaintextStream.read(chunk)) != -1) {
        ciphertextStream.write(chunk, 0, chunkLen);
      }
    }
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

    InputStream ciphertextStream =
        streamingAead.newDecryptingStream(new FileInputStream(inputFilePath),  key.getBytes(StandardCharsets.UTF_8));

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

}
