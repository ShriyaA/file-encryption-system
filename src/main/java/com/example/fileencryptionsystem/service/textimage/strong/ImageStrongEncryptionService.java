package com.example.fileencryptionsystem.service.textimage.strong;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class ImageStrongEncryptionService extends StrongTextImageEncryptionService {

  public ImageStrongEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetPath() {
    return "IMAGE_AES128_GCM_keyset.json";
  }

}
