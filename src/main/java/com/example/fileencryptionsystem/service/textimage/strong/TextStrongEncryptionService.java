package com.example.fileencryptionsystem.service.textimage.strong;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class TextStrongEncryptionService extends StrongTextImageEncryptionService {

  public TextStrongEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetPath() {
    return "TEXT_AES128_GCM_keyset.json";
  }

}
