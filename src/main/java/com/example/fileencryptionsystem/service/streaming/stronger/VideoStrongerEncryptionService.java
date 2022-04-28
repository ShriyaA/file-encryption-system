package com.example.fileencryptionsystem.service.streaming.stronger;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class VideoStrongerEncryptionService extends StrongerStreamingEncryptionService {

  public VideoStrongerEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetPath() {
    return "VIDEO_AES256_CTR_HMAC_SHA256_1MB_keyset.json";
  }

}
