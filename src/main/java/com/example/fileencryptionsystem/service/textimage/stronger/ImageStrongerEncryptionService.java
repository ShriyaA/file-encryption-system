package com.example.fileencryptionsystem.service.textimage.stronger;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class ImageStrongerEncryptionService extends StrongerTextImageEncryptionService {

  public ImageStrongerEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetPath() {
    return "IMAGE_AES256_GCM_keyset.json";
  }

}
