package com.example.fileencryptionsystem.service.textimage.stronger;

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
import java.nio.file.Path;
import java.security.GeneralSecurityException;

public abstract class StrongerTextImageEncryptionService extends TextImageEncryptionService {

  private final Aead aead;

  @Override
  public Aead getAead() {
    return aead;
  }

  public StrongerTextImageEncryptionService() throws GeneralSecurityException, IOException {
    String keySetFile = getKeySetFileName();
    Path keySetPath = new File(keySetFile).toPath();
    KeysetHandle handle;

    if(Files.exists(keySetPath)) {
      File keyFile = keySetPath.toFile();
      handle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile));
    } else {
      handle = KeysetHandle.generateNew(KeyTemplates.get("AES256_GCM"));
      CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(keySetPath.toFile()));
    }

    aead = handle.getPrimitive(Aead.class);
  }
}
