package com.example.fileencryptionsystem.service;

import com.example.fileencryptionsystem.filestorage.FilesStorageServiceImpl;
import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionLevel;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.example.fileencryptionsystem.model.FileType;
import com.example.fileencryptionsystem.service.streaming.strong.AudioStrongEncryptionService;
import com.example.fileencryptionsystem.service.streaming.strong.VideoStrongEncryptionService;
import com.example.fileencryptionsystem.service.streaming.stronger.AudioStrongerEncryptionService;
import com.example.fileencryptionsystem.service.streaming.stronger.VideoStrongerEncryptionService;
import com.example.fileencryptionsystem.service.textimage.strong.ImageStrongEncryptionService;
import com.example.fileencryptionsystem.service.textimage.strong.TextStrongEncryptionService;
import com.example.fileencryptionsystem.service.textimage.stronger.ImageStrongerEncryptionService;
import com.example.fileencryptionsystem.service.textimage.stronger.TextStrongerEncryptionService;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@Slf4j
public class EncryptionService {

  private final TextStrongEncryptionService textStrongEncryptionService;
  private final TextStrongerEncryptionService textStrongerEncryptionService;

  private final ImageStrongEncryptionService imageStrongEncryptionService;
  private final ImageStrongerEncryptionService imageStrongerEncryptionService;

  private final AudioStrongEncryptionService audioStrongEncryptionService;
  private final AudioStrongerEncryptionService audioStrongerEncryptionService;

  private final VideoStrongEncryptionService videoStrongEncryptionService;
  private final VideoStrongerEncryptionService videoStrongerEncryptionService;

  private final FilesStorageServiceImpl filesStorageService;

  public EncryptionService(TextStrongEncryptionService textStrongEncryptionService,
      TextStrongerEncryptionService textStrongerEncryptionService,
      ImageStrongEncryptionService imageStrongEncryptionService,
      ImageStrongerEncryptionService imageStrongerEncryptionService,
      AudioStrongEncryptionService audioStrongEncryptionService,
      AudioStrongerEncryptionService audioStrongerEncryptionService,
      VideoStrongEncryptionService videoStrongEncryptionService,
      VideoStrongerEncryptionService videoStrongerEncryptionService,
      FilesStorageServiceImpl filesStorageService) {

    this.textStrongEncryptionService = textStrongEncryptionService;
    this.textStrongerEncryptionService = textStrongerEncryptionService;

    this.imageStrongEncryptionService = imageStrongEncryptionService;
    this.imageStrongerEncryptionService = imageStrongerEncryptionService;

    this.audioStrongEncryptionService = audioStrongEncryptionService;
    this.audioStrongerEncryptionService = audioStrongerEncryptionService;

    this.videoStrongEncryptionService = videoStrongEncryptionService;
    this.videoStrongerEncryptionService = videoStrongerEncryptionService;

    this.filesStorageService = filesStorageService;
  }

  public File encryptFile(MultipartFile file, EncryptionRequest encryptionRequest) throws GeneralSecurityException, IOException {
    Resource tempFile = null;
    try {
      filesStorageService.save(file);
      tempFile = filesStorageService.load(file.getOriginalFilename());
      return encryptFile(tempFile.getFile().getAbsolutePath(),
          encryptionRequest.getKey(), encryptionRequest.getFileType(),
          encryptionRequest.getEncryptionLevel());
    } finally {
      if (tempFile != null) {
        filesStorageService.delete(tempFile.getFile().toString());
      }
    }
  }

  private File encryptFile(String inputFilePath, String key, FileType fileType, EncryptionLevel encryptionLevel) throws GeneralSecurityException, IOException {
    File outputFile = null;

    if (EncryptionLevel.STRONG == encryptionLevel) {
      outputFile = encryptStrong(inputFilePath, key, fileType);
    } else if (EncryptionLevel.STRONGER == encryptionLevel) {
      outputFile = encryptStronger(inputFilePath, key, fileType);
    }

    return outputFile;
  }

  private File encryptStrong(String inputFilePath, String key, FileType fileType) throws GeneralSecurityException, IOException {
    File outputFile = null;
    if (FileType.TEXT == fileType) {
      outputFile = textStrongEncryptionService.encryptFile(inputFilePath, key);
    } else if (FileType.IMAGE == fileType) {
      outputFile = imageStrongEncryptionService.encryptFile(inputFilePath, key);
    } else if (FileType.AUDIO == fileType) {
      outputFile = audioStrongEncryptionService.encryptFile(inputFilePath, key);
    } else if (FileType.VIDEO == fileType) {
      outputFile = videoStrongEncryptionService.encryptFile(inputFilePath, key);
    }

    return outputFile;
  }

  private File encryptStronger(String inputFilePath, String key, FileType fileType) throws GeneralSecurityException, IOException {
    File outputFile = null;
    if (FileType.TEXT == fileType) {
      outputFile = textStrongerEncryptionService.encryptFile(inputFilePath, key);
    } else if (FileType.IMAGE == fileType) {
      outputFile = imageStrongerEncryptionService.encryptFile(inputFilePath, key);
    } else if (FileType.AUDIO == fileType) {
      outputFile = audioStrongerEncryptionService.encryptFile(inputFilePath, key);
    } else if (FileType.VIDEO == fileType) {
      outputFile = videoStrongerEncryptionService.encryptFile(inputFilePath, key);
    }

    return outputFile;
  }

  public File decryptFile(MultipartFile file, DecryptionRequest decryptionRequest) throws GeneralSecurityException, IOException {
    Resource tempFile = null;
    try {
      filesStorageService.save(file);
      tempFile = filesStorageService.load(file.getOriginalFilename());
      return decryptFile(tempFile.getFile().getAbsolutePath(),
          decryptionRequest.getKey(), decryptionRequest.getFileType(),
          decryptionRequest.getEncryptionLevel());
    } finally {
      if (tempFile != null) {
        filesStorageService.delete(tempFile.getFile().toString());
      }
    }
  }

  private File decryptFile(String inputFilePath, String key, FileType fileType, EncryptionLevel encryptionLevel) throws GeneralSecurityException, IOException {
    File outputFile = null;

    if (EncryptionLevel.STRONG == encryptionLevel) {
      outputFile = decryptStrong(inputFilePath, key, fileType);
    } else if (EncryptionLevel.STRONGER == encryptionLevel) {
      outputFile = decryptStronger(inputFilePath, key, fileType);
    }

    return outputFile;
  }

  private File decryptStrong(String inputFilePath, String key, FileType fileType) throws GeneralSecurityException, IOException {
    File outputFile = null;
    if (FileType.TEXT == fileType) {
      outputFile = textStrongEncryptionService.decryptFile(inputFilePath, key);
    } else if (FileType.IMAGE == fileType) {
      outputFile = imageStrongEncryptionService.decryptFile(inputFilePath, key);
    } else if (FileType.AUDIO == fileType) {
      outputFile = audioStrongEncryptionService.decryptFile(inputFilePath, key);
    } else if (FileType.VIDEO == fileType) {
      outputFile = videoStrongEncryptionService.decryptFile(inputFilePath, key);
    }

    return outputFile;
  }

  private File decryptStronger(String inputFilePath, String key, FileType fileType) throws GeneralSecurityException, IOException {
    File outputFile = null;
    if (FileType.TEXT == fileType) {
      outputFile = textStrongerEncryptionService.decryptFile(inputFilePath, key);
    } else if (FileType.IMAGE == fileType) {
      outputFile = imageStrongerEncryptionService.decryptFile(inputFilePath, key);
    } else if (FileType.AUDIO == fileType) {
      outputFile = audioStrongerEncryptionService.decryptFile(inputFilePath, key);
    } else if (FileType.VIDEO == fileType) {
      outputFile = videoStrongerEncryptionService.decryptFile(inputFilePath, key);
    }

    return outputFile;
  }
}
