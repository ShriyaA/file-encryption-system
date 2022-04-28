package com.example.fileencryptionsystem.service.streaming.strong;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.stereotype.Component;

@Component
public class AudioStrongEncryptionService extends StrongStreamingEncryptionService {

  public AudioStrongEncryptionService() throws GeneralSecurityException, IOException {
    super();
  }

  protected String getKeySetPath() {
    return "AUDIO_AES128_CTR_HMAC_SHA256_1MB_keyset.json";
  }

}
