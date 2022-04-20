package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.service.textimage.StrongerTextImageEncryptionService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

public class TextImageEncryptionServiceTest {

  StrongerTextImageEncryptionService strongerTextImageEncryptionService = new StrongerTextImageEncryptionService();

  public TextImageEncryptionServiceTest() throws GeneralSecurityException, IOException {
  }


  @Test
  public void testEncryptionDecryptionText_TXT() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    strongerTextImageEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    strongerTextImageEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }

  @Test
  public void testEncryptionDecryptionText_PDF() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.pdf");

    assert(url != null);

    String inputFile = url.getPath();
    textImageEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    textImageEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }

  @Test
  public void testEncryptionDecryptionImage_JPG() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.jpg");

    assert(url != null);

    String inputFile = url.getPath();
    textImageEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    textImageEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }

  @Test
  public void testEncryptionDecryptionImage_PNG() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.png");

    assert(url != null);

    String inputFile = url.getPath();
    textImageEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    textImageEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }
}
