package com.example.fileencryptionsystem.model;

import lombok.Data;

@Data
public class DecryptionRequest {

  String key;
  FileType fileType;
  EncryptionLevel encryptionLevel;
}
