package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.service.textimage.StrongerTextImageEncryptionService;
import com.example.fileencryptionsystem.service.textimage.StrongTextImageEncryptionService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class TextImageEncryptionServiceTest {

  StrongerTextImageEncryptionService strongerTextImageEncryptionService = new StrongerTextImageEncryptionService();
  StrongTextImageEncryptionService strongTextImageEncryptionService = new StrongTextImageEncryptionService();

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
  public void testDecryptionEncryptionText_TXT() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(Paths.get(decryptedFile)));
  }

  @Test
  public void testEncryptionDecryptionText_PDF() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.pdf");

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
  public void testDecryptionEncryptionText_PDF() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.pdf");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(Paths.get(decryptedFile)));
  }

  @Test
  public void testEncryptionDecryptionImage_JPG() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.jpg");

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
  public void testDecryptionEncryptionText_JPG() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.jpg");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(Paths.get(decryptedFile)));
  }

  @Test
  public void testEncryptionDecryptionImage_PNG() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.png");

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
  public void testDecryptionEncryptionText_PNG() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.png");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(Paths.get(decryptedFile)));
  }

  @Test
  public void testEncryptionDecryptionTextNotEqual_TXT() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
	String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    strongerTextImageEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testEncryptionDecryptionTextNotEqual_PDF() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
	String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.pdf");

    assert(url != null);

    String inputFile = url.getPath();
    strongerTextImageEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testEncryptionDecryptionImageNotEqual_JPG() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
    String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.jpg");

    assert(url != null);

    String inputFile = url.getPath();
    strongerTextImageEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testEncryptionDecryptionImageNotEqual_PNG() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
    String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.png");

    assert(url != null);

    String inputFile = url.getPath();
    strongerTextImageEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testStrongEncryptionStrongerDecryptionText_TXT() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    strongTextImageEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongerTextImageEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testStrongerEncryptionStrongDecryptionText_TXT() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    strongerTextImageEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			strongTextImageEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("decryption failed", exception.getMessage());
  }
}
