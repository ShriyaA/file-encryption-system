package com.example.fileencryptionsystem.controller;

import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.example.fileencryptionsystem.service.EncryptionService;
import java.io.IOException;
import java.security.GeneralSecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  ResponseEntity<String> encrypt(@RequestPart MultipartFile file, @RequestPart EncryptionRequest encryptionRequest) {
    try {
      encryptionService.encryptFile(file, encryptionRequest);
      return ResponseEntity.ok("Encryption Completed.");
    } catch (IllegalStateException | GeneralSecurityException | IOException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  @PostMapping(path = "/decrypt",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  ResponseEntity<String> decrypt(@RequestPart MultipartFile file, @RequestPart DecryptionRequest decryptionRequest) {
    try {
      encryptionService.decryptFile(file, decryptionRequest);
      return ResponseEntity.ok("Decryption Completed.");
    } catch (IllegalStateException | GeneralSecurityException | IOException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

}
