package com.example.fileencryptionsystem.service.textimage.strong;

import com.example.fileencryptionsystem.service.textimage.TextImageEncryptionService;
import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeyTemplates;
import com.google.crypto.tink.KeysetHandle;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

public abstract class StrongTextImageEncryptionService extends TextImageEncryptionService {

  private final Aead aead;

  @Override
  public Aead getAead() {
    return aead;
  }

  public StrongTextImageEncryptionService() throws GeneralSecurityException, IOException {
    String keySetPath = getKeySetPath();
    KeysetHandle handle;
    if(Files.exists(Paths.get(keySetPath))) {
      File keyFile = new File(keySetPath);
      handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile));
    } else {
      handle = KeysetHandle.generateNew(KeyTemplates.get("AES128_GCM"));
      CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(new File(keySetPath)));
    }

    aead = handle.getPrimitive(Aead.class);
  }
}
