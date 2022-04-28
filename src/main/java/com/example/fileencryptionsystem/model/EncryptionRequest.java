package com.example.fileencryptionsystem.model;

import lombok.Data;

@Data
public class EncryptionRequest {

  String key;
  FileType fileType;
  EncryptionLevel encryptionLevel;
}
