The code will work for text file and images. Need changes to support video

Developer Notes:

To compile: 
```console
./mvnw clean install
```

To run: 
```console
./mvnw spring-boot:run
```

The server will start at localhost:8080

Encryption Request Example:

```console
curl -d "@payload.json" -H "Content-Type: application/json" -X POST localhost:8080/encrypt
```

payload.json example:

```json
[
  {
    "inputFilePath": "/Users/pranavkapoor/Desktop/test-image.jpeg",
    "key": "Pranav encryption key"
  },
  {
    "inputFilePath": "/Users/pranavkapoor/Desktop/abc.txt",
    "key": "Pranav encryption key"
  }
]
```

Decryption Request Example:

curl -d "@payload.json" -H "Content-Type: application/json" -X POST localhost:8080/decrypt

payload.json example:

```json
[
  {
    "inputFilePath": "/Users/pranavkapoor/Desktop/test-image-encrypted.jpeg",
    "key": "Pranav encryption key"
  },
  {
    "inputFilePath": "/Users/pranavkapoor/Desktop/abc-encrypted.txt",
    "key": "Pranav encryption key"
  }
]
```