package com.example.fileencryptionsystem.service.textimage;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class StrongerTextImageEncryptionService extends TextImageEncryptionService {

  private final Aead aead;

  @Override
  public Aead getAead() {
    return aead;
  }

  public StrongerTextImageEncryptionService() throws GeneralSecurityException, IOException {
    String keySetPath = "AES256_GCM_keyset.json";
    KeysetHandle handle;
    if(Files.exists(Paths.get(keySetPath))) {
      File keyFile = new File(keySetPath);
      handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile));
    } else {
      handle = KeysetHandle.generateNew(KeyTemplates.get("AES256_GCM"));
      CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(new File(keySetPath)));
    }

    aead = handle.getPrimitive(Aead.class);
  }
}
