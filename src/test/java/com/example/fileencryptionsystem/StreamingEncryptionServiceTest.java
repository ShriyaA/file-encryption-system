package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.service.streaming.StrongerStreamingEncryptionService;
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

}
