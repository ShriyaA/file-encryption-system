package com.example.fileencryptionsystem.service;

import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.example.fileencryptionsystem.model.FileType;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EncryptionService {

  private final TextImageEncryptionService textImageEncryptionService;
  private final StreamingEncryptionService streamingEncryptionService;

  public EncryptionService(TextImageEncryptionService textImageEncryptionService, StreamingEncryptionService streamingEncryptionService) {
    this.textImageEncryptionService = textImageEncryptionService;
    this.streamingEncryptionService = streamingEncryptionService;
  }

  public void encryptFiles(List<EncryptionRequest> encryptionRequests) {
    encryptionRequests.forEach(er -> {
      try {
        encryptFile(er.getInputFilePath(), er.getKey(), er.getFileType());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void encryptFile(String inputFilePath, String key, FileType fileType) throws GeneralSecurityException, IOException {

    if (FileType.TEXT == fileType || FileType.IMAGE == fileType) {
      textImageEncryptionService.encryptFile(inputFilePath, key);
    } else if (FileType.AUDIO == fileType || FileType.VIDEO == fileType) {
      streamingEncryptionService.encryptFile(inputFilePath, key);
    }
  }

  public void decryptFiles(List<DecryptionRequest> decryptionRequests) {
    decryptionRequests.forEach(dr -> {
      try {
        decryptFile(dr.getInputFilePath(), dr.getKey(), dr.getFileType());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void decryptFile(String inputFilePath, String key, FileType fileType) throws GeneralSecurityException, IOException {
    if (FileType.TEXT == fileType || FileType.IMAGE == fileType) {
      textImageEncryptionService.decryptFile(inputFilePath, key);
    } else if (FileType.AUDIO == fileType || FileType.VIDEO == fileType) {
      streamingEncryptionService.decryptFile(inputFilePath, key);
    }
  }
}
