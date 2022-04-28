package com.example.fileencryptionsystem.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.http.MediaType;

public class MediaTypeUtil {

  public static MediaType getMediaTypeForFileName(Path path) {
    try {
      String mimeType = Files.probeContentType(path);
      return MediaType.parseMediaType(mimeType);
    } catch (IOException e) {
      return MediaType.APPLICATION_OCTET_STREAM;
    }
  }
}
