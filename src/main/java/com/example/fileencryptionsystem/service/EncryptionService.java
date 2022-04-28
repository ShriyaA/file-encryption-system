package com.example.fileencryptionsystem.service;

import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionLevel;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.example.fileencryptionsystem.model.FileType;
import com.example.fileencryptionsystem.service.streaming.StrongStreamingEncryptionService;
import com.example.fileencryptionsystem.service.streaming.StrongerStreamingEncryptionService;
import com.example.fileencryptionsystem.service.textimage.StrongTextImageEncryptionService;
import com.example.fileencryptionsystem.service.textimage.StrongerTextImageEncryptionService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


@Service
@Slf4j
public class EncryptionService {

  private final StrongTextImageEncryptionService strongTextImageEncryptionService;
  private final StrongerTextImageEncryptionService strongerTextImageEncryptionService;
  private final StrongStreamingEncryptionService strongStreamingEncryptionService;
  private final StrongerStreamingEncryptionService strongerStreamingEncryptionService;

  public EncryptionService(StrongTextImageEncryptionService strongTextImageEncryptionService,
      StrongerTextImageEncryptionService strongerTextImageEncryptionService,
      StrongStreamingEncryptionService strongStreamingEncryptionService,
      StrongerStreamingEncryptionService strongerStreamingEncryptionService) {
    this.strongTextImageEncryptionService = strongTextImageEncryptionService;
    this.strongerTextImageEncryptionService = strongerTextImageEncryptionService;
    this.strongStreamingEncryptionService = strongStreamingEncryptionService;
    this.strongerStreamingEncryptionService = strongerStreamingEncryptionService;
  }

  public void encryptFiles(List<EncryptionRequest> encryptionRequests) {
    encryptionRequests.forEach(er -> {
      try {
        encryptFile(er.getInputFilePath(), er.getKey(), er.getFileType(), er.getEncryptionLevel());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void encryptFile(String inputFilePath, String key, FileType fileType, EncryptionLevel encryptionLevel) throws GeneralSecurityException, IOException {

    if (FileType.TEXT == fileType || FileType.IMAGE == fileType) {
      if (EncryptionLevel.STRONG == encryptionLevel) {
        strongTextImageEncryptionService.encryptFile(inputFilePath, key);
      } else if (EncryptionLevel.STRONGER == encryptionLevel) {
        strongerTextImageEncryptionService.encryptFile(inputFilePath, key);
      }
    } else if (FileType.AUDIO == fileType || FileType.VIDEO == fileType) {
      if (EncryptionLevel.STRONG == encryptionLevel) {
        strongStreamingEncryptionService.encryptFile(inputFilePath, key);
      } else if (EncryptionLevel.STRONGER == encryptionLevel) {
        strongerStreamingEncryptionService.encryptFile(inputFilePath, key);
      }
    }
  }

  public void decryptFiles(List<DecryptionRequest> decryptionRequests) {
    decryptionRequests.forEach(dr -> {
      try {
        decryptFile(dr.getInputFilePath(), dr.getKey(), dr.getFileType(), dr.getEncryptionLevel());
      } catch (GeneralSecurityException | IOException e) {
        e.printStackTrace();
      }
    });
  }

  private void decryptFile(String inputFilePath, String key, FileType fileType, EncryptionLevel encryptionLevel) throws GeneralSecurityException, IOException {
    if (FileType.TEXT == fileType || FileType.IMAGE == fileType) {
      if (EncryptionLevel.STRONG == encryptionLevel) {
        strongTextImageEncryptionService.decryptFile(inputFilePath, key);
      } else if (EncryptionLevel.STRONGER == encryptionLevel) {
        strongerTextImageEncryptionService.decryptFile(inputFilePath, key);
      }
    } else if (FileType.AUDIO == fileType || FileType.VIDEO == fileType) {
      if (EncryptionLevel.STRONG == encryptionLevel) {
        strongStreamingEncryptionService.decryptFile(inputFilePath, key);
      } else if (EncryptionLevel.STRONGER == encryptionLevel) {
        strongerStreamingEncryptionService.decryptFile(inputFilePath, key);
      }
    }
  }
}
