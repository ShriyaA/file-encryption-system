package com.example.fileencryptionsystem.service.streaming.strong;

import com.example.fileencryptionsystem.service.streaming.StreamingEncryptionService;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.StreamingAead;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;

public abstract class StrongStreamingEncryptionService extends StreamingEncryptionService {

  private final StreamingAead streamingAead;

  @Override
  public StreamingAead getStreamingAead() {
    return streamingAead;
  }

  public StrongStreamingEncryptionService() throws GeneralSecurityException, IOException {
    String keySetFile = getKeySetFileName();
    Path keySetPath = new File(keySetFile).toPath();

    KeysetHandle handle;
    if(Files.exists(keySetPath)) {
      File keyFile = keySetPath.toFile();
      handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile));
    } else {
      handle = KeysetHandle.generateNew(KeyTemplates.get("AES128_CTR_HMAC_SHA256_1MB"));
      CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(keySetPath.toFile()));
    }

    streamingAead = handle.getPrimitive(StreamingAead.class);
  }
}
