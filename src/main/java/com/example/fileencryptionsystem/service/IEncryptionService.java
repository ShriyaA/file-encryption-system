package com.example.fileencryptionsystem.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

public abstract class IEncryptionService {

  public abstract void encryptFile(String inputFilePath, String key) throws GeneralSecurityException, IOException;

  public abstract void decryptFile(String inputFilePath, String key) throws GeneralSecurityException, IOException;
}
