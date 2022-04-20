package com.example.fileencryptionsystem.model;

import lombok.Data;

@Data
public class DecryptionRequest {

  String inputFilePath;
  String key;
  FileType fileType;
}
