package com.example.fileencryptionsystem.service;

import com.example.fileencryptionsystem.filestorage.FilesStorageServiceImpl;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class EncryptionService {

  private final StrongTextImageEncryptionService strongTextImageEncryptionService;
  private final StrongerTextImageEncryptionService strongerTextImageEncryptionService;
  private final StrongStreamingEncryptionService strongStreamingEncryptionService;
  private final StrongerStreamingEncryptionService strongerStreamingEncryptionService;
  private final FilesStorageServiceImpl filesStorageService;

  public EncryptionService(StrongTextImageEncryptionService strongTextImageEncryptionService,
      StrongerTextImageEncryptionService strongerTextImageEncryptionService,
      StrongStreamingEncryptionService strongStreamingEncryptionService,
      StrongerStreamingEncryptionService strongerStreamingEncryptionService,
      FilesStorageServiceImpl filesStorageService) {
    this.strongTextImageEncryptionService = strongTextImageEncryptionService;
    this.strongerTextImageEncryptionService = strongerTextImageEncryptionService;
    this.strongStreamingEncryptionService = strongStreamingEncryptionService;
    this.strongerStreamingEncryptionService = strongerStreamingEncryptionService;
    this.filesStorageService = filesStorageService;
  }

  public void encryptFile(MultipartFile file, EncryptionRequest encryptionRequest) throws GeneralSecurityException, IOException {
    filesStorageService.save(file);
    Resource tempFile = filesStorageService.load(file.getOriginalFilename());
    encryptFile(tempFile.getFile().getAbsolutePath(), encryptionRequest.getKey(), encryptionRequest.getFileType(), encryptionRequest.getEncryptionLevel());
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

  public void decryptFile(MultipartFile file, DecryptionRequest decryptionRequest) throws GeneralSecurityException, IOException {
    filesStorageService.save(file);
    Resource tempFile = filesStorageService.load(file.getOriginalFilename());
    decryptFile(tempFile.getFile().getAbsolutePath(), decryptionRequest.getKey(), decryptionRequest.getFileType(), decryptionRequest.getEncryptionLevel());
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
