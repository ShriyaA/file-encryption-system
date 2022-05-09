package com.example.fileencryptionsystem.service.streaming.strong;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class VideoStrongEncryptionService extends StrongStreamingEncryptionService {

  public VideoStrongEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetFileName() {
    return "VIDEO_AES128_CTR_HMAC_SHA256_1MB_keyset.json";
  }

}
