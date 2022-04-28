package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.service.streaming.StrongerStreamingEncryptionService;
import com.example.fileencryptionsystem.service.streaming.StrongStreamingEncryptionService;
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

public class StreamingEncryptionServiceTest {

  StrongerStreamingEncryptionService strongerStreamingEncryptionService = new StrongerStreamingEncryptionService();
  StrongStreamingEncryptionService strongStreamingEncryptionService = new StrongStreamingEncryptionService();

  public StreamingEncryptionServiceTest() throws GeneralSecurityException, IOException {
  }

  @Test
  public void testEncryptionDecryptionVideo_MP4() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    strongerStreamingEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_MP4() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(inputFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(decryptedFile)));

	strongerStreamingEncryptionService.encryptFile(decryptedFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(decryptedFile) + FilenameUtils.getBaseName(decryptedFile) + "-encrypted." + FilenameUtils.getExtension(decryptedFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(encryptedFile)) != -1L);
  }

  @Test
  public void testEncryptionDecryptionVideo_AVI() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing.avi");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    strongerStreamingEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_AVI() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing.avi");

    assert(url != null);

    String inputFile = url.getPath();

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(inputFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(decryptedFile)));

	strongerStreamingEncryptionService.encryptFile(decryptedFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(decryptedFile) + FilenameUtils.getBaseName(decryptedFile) + "-encrypted." + FilenameUtils.getExtension(decryptedFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(encryptedFile)) != -1L);
  }

  @Test
  public void testEncryptionDecryptionAudio_MP3() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.mp3");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    strongerStreamingEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_MP3() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.mp3");

    assert(url != null);

    String inputFile = url.getPath();

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(inputFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(decryptedFile)));

	strongerStreamingEncryptionService.encryptFile(decryptedFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(decryptedFile) + FilenameUtils.getBaseName(decryptedFile) + "-encrypted." + FilenameUtils.getExtension(decryptedFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(encryptedFile)) != -1L);
  }

  @Test
  public void testEncryptionDecryptionAudio_WAV() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.wav");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    strongerStreamingEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(Paths.get(decryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(decryptedFile)) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_WAV() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.wav");

    assert(url != null);

    String inputFile = url.getPath();

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(inputFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(decryptedFile)));

	strongerStreamingEncryptionService.encryptFile(decryptedFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(decryptedFile) + FilenameUtils.getBaseName(decryptedFile) + "-encrypted." + FilenameUtils.getExtension(decryptedFile);
    assert(Files.exists(Paths.get(encryptedFile)));

    assert(FileTestUtils.filesCompareByByte(Paths.get(inputFile), Paths.get(encryptedFile)) != -1L);
  }

  @Test
  public void testEncryptionDecryptionVideoNotEqual_MP4() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
    String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testEncryptionDecryptionVideoNotEqual_AVI() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
    String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing.avi");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testEncryptionDecryptionAudioNotEqual_MP3() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
    String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.mp3");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testEncryptionDecryptionAudioNotEqual_WAV() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
    String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.wav");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testStrongEncryptionStrongerDecryptionVideo_MP4() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    strongStreamingEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(IOException.class, () ->
			strongerStreamingEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testStrongerEncryptionStrongDecryptionVideo_MP4() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    strongerStreamingEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(Paths.get(encryptedFile)));

	Exception exception = assertThrows(IOException.class, () ->
			strongStreamingEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }
}
