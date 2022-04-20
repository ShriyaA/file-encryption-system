package com.example.fileencryptionsystem.model;

import lombok.Data;

@Data
public class EncryptionRequest {

  String inputFilePath;
  String key;
  FileType fileType;
}
