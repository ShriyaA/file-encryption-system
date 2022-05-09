package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.util.MediaTypeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.io.File;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class MediaTypeUtilTest {

    @Test
    public void testInitialization() {
        MediaTypeUtil mediaTypeUtil = new MediaTypeUtil();
        assert(mediaTypeUtil.getClass() == MediaTypeUtil.class);
    }
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

        assertThat(MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()), anyOf(
                is(new MediaType("audio", "wav")),
                is(new MediaType("audio", "x-wav")),
                is(new MediaType("audio", "vnd.wave"))));
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
        assertThat(MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()), anyOf(is(new MediaType("video", "avi")), is(new MediaType("video", "x-msvideo"))));
    }

    @Test
    public void testUnknownExtension() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("unknownextension.pjyp");

        assert(url != null);
        String inputFile = url.getPath();
        assertEquals(MediaType.APPLICATION_OCTET_STREAM, MediaTypeUtil.getMediaTypeForFileName(new File(inputFile).toPath()));
    }

}
