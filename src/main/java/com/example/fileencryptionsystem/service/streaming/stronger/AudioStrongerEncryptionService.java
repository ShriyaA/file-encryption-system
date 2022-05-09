package com.example.fileencryptionsystem.service.streaming.stronger;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class AudioStrongerEncryptionService extends StrongerStreamingEncryptionService {

  public AudioStrongerEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetFileName() {
    return "AUDIO_AES256_CTR_HMAC_SHA256_1MB_keyset.json";
  }

}
