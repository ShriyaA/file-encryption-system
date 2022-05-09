package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.service.streaming.strong.VideoStrongEncryptionService;
import com.example.fileencryptionsystem.service.streaming.stronger.AudioStrongerEncryptionService;
import com.example.fileencryptionsystem.service.streaming.stronger.VideoStrongerEncryptionService;
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

public class StreamingEncryptionServiceTest {

  VideoStrongEncryptionService videoStrongEncryptionService = new VideoStrongEncryptionService();
  VideoStrongerEncryptionService videoStrongerEncryptionService = new VideoStrongerEncryptionService();
  AudioStrongerEncryptionService audioStrongerEncryptionService = new AudioStrongerEncryptionService();
  TextStrongerEncryptionService textStrongerEncryptionService = new TextStrongerEncryptionService();

  public StreamingEncryptionServiceTest() throws GeneralSecurityException, IOException {
  }

  @Test
  public void testEncryptionDecryptionVideo_MP4() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    videoStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    videoStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_MP4() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();

    Exception exception = assertThrows(IOException.class, () ->
        videoStrongerEncryptionService.decryptFile(inputFile, key));
    assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    //String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    //assert(Files.notExists((new File(decryptedFile).toPath())));
  }

  @Test
  public void testEncryptionDecryptionVideo_AVI() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing.avi");

    assert(url != null);

    String inputFile = url.getPath();
    videoStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    videoStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_AVI() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing.avi");

    assert(url != null);

    String inputFile = url.getPath();

	Exception exception = assertThrows(IOException.class, () ->
      videoStrongerEncryptionService.decryptFile(inputFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    //String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    //assert(Files.notExists(new File(decryptedFile).toPath()));
  }

  @Test
  public void testEncryptionDecryptionAudio_MP3() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.mp3");

    assert(url != null);

    String inputFile = url.getPath();
    audioStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    audioStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_MP3() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.mp3");

    assert(url != null);

    String inputFile = url.getPath();

	Exception exception = assertThrows(IOException.class, () ->
      audioStrongerEncryptionService.decryptFile(inputFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    //String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    //assert(Files.notExists(new File(decryptedFile).toPath()));
  }

  @Test
  public void testEncryptionDecryptionAudio_WAV() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.wav");

    assert(url != null);

    String inputFile = url.getPath();
    audioStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

    audioStrongerEncryptionService.decryptFile(encryptedFile, key);

    String decryptedFile = File.separator + FilenameUtils.getPath(encryptedFile) + FilenameUtils.getBaseName(encryptedFile) + "-decrypted." + FilenameUtils.getExtension(encryptedFile);
    assert(Files.exists(new File(decryptedFile).toPath()));

    assert(FileTestUtils.filesCompareByByte(new File(inputFile).toPath(), new File(decryptedFile).toPath()) == -1L);
  }

  @Test
  public void testDecryptionEncryptionVideo_WAV() {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("audio_testing.wav");

    assert(url != null);

    String inputFile = url.getPath();

	Exception exception = assertThrows(IOException.class, () ->
      audioStrongerEncryptionService.decryptFile(inputFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());

    //String decryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-decrypted." + FilenameUtils.getExtension(inputFile);
    //assert(Files.notExists(new File(decryptedFile).toPath()));
  }

  @Test
  public void testEncryptionDecryptionVideoNotEqual_MP4() throws GeneralSecurityException, IOException {
    String en_key = "testing-key";
    String de_key = "testing-key2";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    videoStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
      videoStrongerEncryptionService.decryptFile(encryptedFile, de_key));
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
    videoStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
      videoStrongerEncryptionService.decryptFile(encryptedFile, de_key));
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
    audioStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
      audioStrongerEncryptionService.decryptFile(encryptedFile, de_key));
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
    audioStrongerEncryptionService.encryptFile(inputFile, en_key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
      audioStrongerEncryptionService.decryptFile(encryptedFile, de_key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testStrongEncryptionStrongerDecryptionVideo_MP4() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    videoStrongEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
      videoStrongerEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testStrongerEncryptionStrongDecryptionVideo_MP4() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    videoStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
      videoStrongEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

  @Test
  public void testEncryptionVideoDecryptionText() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("video-testing-100mb.mp4");

    assert(url != null);

    String inputFile = url.getPath();
    videoStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(GeneralSecurityException.class, () ->
      textStrongerEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("decryption failed", exception.getMessage());
  }

  @Test
  public void testEncryptionTextDecryptionVideo() throws GeneralSecurityException, IOException {
    String key = "testing-key";

    ClassLoader classLoader = getClass().getClassLoader();
    URL url = classLoader.getResource("abc.txt");

    assert(url != null);

    String inputFile = url.getPath();
    textStrongerEncryptionService.encryptFile(inputFile, key);

    String encryptedFile = File.separator + FilenameUtils.getPath(inputFile) + FilenameUtils.getBaseName(inputFile) + "-encrypted." + FilenameUtils.getExtension(inputFile);
    assert(Files.exists(new File(encryptedFile).toPath()));

	Exception exception = assertThrows(IOException.class, () ->
			videoStrongEncryptionService.decryptFile(encryptedFile, key));
	assertEquals("No matching key found for the ciphertext in the stream.", exception.getMessage());
  }

}
