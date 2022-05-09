package com.example.fileencryptionsystem.controller;

import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.example.fileencryptionsystem.service.EncryptionService;
import com.example.fileencryptionsystem.util.MediaTypeUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.POST, RequestMethod.GET,
    RequestMethod.PUT})
@Slf4j
public class ApiController {

  private final EncryptionService encryptionService;

  public ApiController(EncryptionService encryptionService) {
    this.encryptionService = encryptionService;
  }

  @PostMapping(path = "/encrypt",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseBody
  ResponseEntity<Resource> encrypt(@RequestPart MultipartFile file, @RequestPart EncryptionRequest encryptionRequest) {
    try {
      File outputFile = encryptionService.encryptFile(file, encryptionRequest);
      ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(new File(outputFile.getAbsolutePath()).toPath()));
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + outputFile.getName())
          .contentLength(outputFile.length())
          .contentType(MediaTypeUtil.getMediaTypeForFileName(outputFile.toPath()))
          .body(resource);
    } catch (IllegalStateException | GeneralSecurityException | IOException e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping(path = "/decrypt",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseBody
  ResponseEntity<Resource> decrypt(@RequestPart MultipartFile file, @RequestPart DecryptionRequest decryptionRequest) {
    try {
      File outputFile = encryptionService.decryptFile(file, decryptionRequest);
      ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(new File(outputFile.getAbsolutePath()).toPath()));
      return ResponseEntity.ok()
          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + outputFile.getName())
          .contentLength(outputFile.length())
          .contentType(MediaTypeUtil.getMediaTypeForFileName(outputFile.toPath()))
          .body(resource);
    } catch (IllegalStateException | GeneralSecurityException | IOException e) {
      return ResponseEntity.badRequest().build();
    }
  }

}
