package com.example.fileencryptionsystem.controller;

import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.example.fileencryptionsystem.service.EncryptionService;
import java.security.GeneralSecurityException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

  private final EncryptionService encryptionService;

  public ApiController(EncryptionService encryptionService) {
    this.encryptionService = encryptionService;
  }

  @PostMapping(path = "/encrypt",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  ResponseEntity<String> encrypt(@RequestBody List<EncryptionRequest> encryptionRequests) {
    try {
      encryptionService.encryptFiles(encryptionRequests);
      return ResponseEntity.ok("Encryption Completed.");
    } catch (IllegalStateException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  @PostMapping(path = "/decrypt",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseBody
  ResponseEntity<String> decrypt(@RequestBody List<DecryptionRequest> decryptionRequests) {
    try {
      encryptionService.decryptFiles(decryptionRequests);
      return ResponseEntity.ok("Decryption Completed.");
    } catch (IllegalStateException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

}
