package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.util.MediaTypeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.io.File;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class MediaTypeUtilTest {

    @Test
    public void testMediaTypeJPEG() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("image_testing.jpg");

        assert(url != null);
        String inputFile = url.getPath();

        assertEquals(MediaType.IMAGE_JPEG, MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

    @Test
    public void testMediaTypePNG() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("image_testing.png");

        assert(url != null);
        String inputFile = url.getPath();

        assertEquals(MediaType.IMAGE_PNG, MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

    @Test
    public void testMediaTypeTXT() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("abc.txt");

        assert(url != null);
        String inputFile = url.getPath();

        assertEquals(MediaType.TEXT_PLAIN, MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

    @Test
    public void testMediaTypePDF() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("abc.pdf");

        assert(url != null);
        String inputFile = url.getPath();

        assertEquals(MediaType.APPLICATION_PDF, MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

    @Test
    public void testMediaTypeWAV() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("audio_testing.wav");

        assert(url != null);
        String inputFile = url.getPath();

        assertEquals(new MediaType("audio", "wav"), MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

    @Test
    public void testMediaTypeMP3() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("audio_testing.mp3");

        assert(url != null);
        String inputFile = url.getPath();

        assertEquals(new MediaType("audio", "mpeg"), MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

    @Test
    public void testMediaTypeMP4() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("video-testing-100mb.mp4");

        assert(url != null);
        String inputFile = url.getPath();

        assertEquals(new MediaType("video", "mp4"), MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

    @Test
    public void testMediaTypeAVI() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("video-testing.avi");

        assert(url != null);
        String inputFile = url.getPath();
        assertEquals(new MediaType("video", "avi"), MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

}
