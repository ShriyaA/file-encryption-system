package com.example.fileencryptionsystem.model;

public class DecryptionRequest {
  String key;
  FileType fileType;
  EncryptionLevel encryptionLevel;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public FileType getFileType() {
    return fileType;
  }

  public void setFileType(FileType fileType) {
    this.fileType = fileType;
  }

  public EncryptionLevel getEncryptionLevel() {
    return encryptionLevel;
  }

  public void setEncryptionLevel(EncryptionLevel encryptionLevel) {
    this.encryptionLevel = encryptionLevel;
  }
}
