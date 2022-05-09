package com.example.fileencryptionsystem.util;

import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MediaTypeUtil {

  public static MediaType getMediaTypeForFileName(Path path) {
    try {
      String mimeType = Files.probeContentType(path);
      return MediaType.parseMediaType(mimeType);
    } catch (IOException | InvalidMediaTypeException e) {
      return MediaType.APPLICATION_OCTET_STREAM;
    }
  }
}
