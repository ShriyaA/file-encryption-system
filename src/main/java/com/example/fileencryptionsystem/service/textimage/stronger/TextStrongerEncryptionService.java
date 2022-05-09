package com.example.fileencryptionsystem.service.textimage.stronger;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class TextStrongerEncryptionService extends StrongerTextImageEncryptionService {

  public TextStrongerEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetFileName() {
    return "TEXT_AES256_GCM_keyset.json";
  }

}
