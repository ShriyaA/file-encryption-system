package com.example.fileencryptionsystem.service.streaming;

import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.StreamingAead;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class StrongStreamingEncryptionService extends StreamingEncryptionService {

  private final StreamingAead streamingAead;

  @Override
  public StreamingAead getStreamingAead() {
    return streamingAead;
  }

  public StrongStreamingEncryptionService() throws GeneralSecurityException, IOException {
    String keySetPath = "AES128_CTR_HMAC_SHA256_1MB_keyset.json";
    KeysetHandle handle;
    if(Files.exists(Paths.get(keySetPath))) {
      File keyFile = new File(keySetPath);
      handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile));
    } else {
      handle = KeysetHandle.generateNew(KeyTemplates.get("AES128_CTR_HMAC_SHA256_1MB"));
      CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(new File(keySetPath)));
    }

    streamingAead = handle.getPrimitive(StreamingAead.class);
  }
}
