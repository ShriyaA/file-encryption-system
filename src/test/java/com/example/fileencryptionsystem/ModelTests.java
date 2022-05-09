package com.example.fileencryptionsystem;

import com.example.fileencryptionsystem.model.DecryptionRequest;
import com.example.fileencryptionsystem.model.EncryptionLevel;
import com.example.fileencryptionsystem.model.EncryptionRequest;
import com.example.fileencryptionsystem.model.FileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelTests {

    @Test
    public void testEncryptionRequest() {
        EncryptionRequest encryptionRequest = new EncryptionRequest();

        assertNull(encryptionRequest.getKey());
        assertNull(encryptionRequest.getFileType());
        assertNull(encryptionRequest.getEncryptionLevel());

        encryptionRequest.setKey("testing-key");
        encryptionRequest.setFileType(FileType.TEXT);
        encryptionRequest.setEncryptionLevel(EncryptionLevel.STRONG);

        assertEquals("testing-key", encryptionRequest.getKey());
        assertEquals(FileType.TEXT, encryptionRequest.getFileType());
        assertEquals(EncryptionLevel.STRONG, encryptionRequest.getEncryptionLevel());
    }

    @Test
    public void testDecryptionRequest() {
        DecryptionRequest decryptionRequest = new DecryptionRequest();

        assertNull(decryptionRequest.getKey());
        assertNull(decryptionRequest.getFileType());
        assertNull(decryptionRequest.getEncryptionLevel());

        decryptionRequest.setKey("testing-key");
        decryptionRequest.setFileType(FileType.TEXT);
        decryptionRequest.setEncryptionLevel(EncryptionLevel.STRONG);

        assertEquals("testing-key", decryptionRequest.getKey());
        assertEquals(FileType.TEXT, decryptionRequest.getFileType());
        assertEquals(EncryptionLevel.STRONG, decryptionRequest.getEncryptionLevel());
    }
}
