package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.service.streaming.stronger.AudioStrongerEncryptionService;
import com.example.fileencryptionsystem.service.textimage.strong.TextStrongEncryptionService;
import com.example.fileencryptionsystem.service.textimage.stronger.ImageStrongerEncryptionService;
import com.example.fileencryptionsystem.service.textimage.stronger.TextStrongerEncryptionService;
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

  TextStrongEncryptionService textStrongEncryptionService = new TextStrongEncryptionService();
  TextStrongerEncryptionService textStrongerEncryptionService = new TextStrongerEncryptionService();
  ImageStrongerEncryptionService imageStrongerEncryptionService = new ImageStrongerEncryptionService();
  AudioStrongerEncryptionService audioStrongerEncryptionService = new AudioStrongerEncryptionService();

  public TextImageEncryptionServiceTest() throws GeneralSecurityException, IOException {
  }


  @Test
  public void testEncryptionDecryptionText_TXT() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    textStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    textStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionText_TXT() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      textStrongerEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(new File(decryptedFile).toPath()));
  }

  @Test
  public void testEncryptionDecryptionText_PDF() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.pdf");

    assert(url != null);

    String inputFile = url.getPath();
    textStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    textStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionText_PDF() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.pdf");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      textStrongerEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(new File(decryptedFile).toPath()));
  }

  @Test
  public void testEncryptionDecryptionImage_JPG() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.jpg");

    assert(url != null);

    String inputFile = url.getPath();
    imageStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    imageStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionText_JPG() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.jpg");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      imageStrongerEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(new File(decryptedFile).toPath()));
  }

  @Test
  public void testEncryptionDecryptionImage_PNG() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.png");

    assert(url != null);

    String inputFile = url.getPath();
    imageStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    imageStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionText_PNG() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.png");

    assert(url != null);

    String inputFile = url.getPath();
	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      imageStrongerEncryptionService.decryptFile(inputFile, key));
	assertEquals("decryption failed", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.notExists(new File(decryptedFile).toPath()));
  }

  @Test
  public void testEncryptionDecryptionTextNotEqual_TXT() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
	String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    textStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      textStrongerEncryptionService.decryptFile(encryptedFile, de_key));
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
    textStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      textStrongerEncryptionService.decryptFile(encryptedFile, de_key));
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
    imageStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      imageStrongerEncryptionService.decryptFile(encryptedFile, de_key));
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
    imageStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      imageStrongerEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testStrongEncryptionStrongerDecryptionText_TXT() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    textStrongEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			textStrongerEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testStrongerEncryptionStrongDecryptionText_TXT() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    textStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      textStrongEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testEncryptionAudioDecryptionImage() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.mp3");

    assert(url != null);

    String inputFile = url.getPath();
    audioStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
			textStrongerEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testEncryptionImageDecryptionAudio() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("image_testing.jpg");

    assert(url != null);

    String inputFile = url.getPath();
    imageStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
			audioStrongerEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }
}
