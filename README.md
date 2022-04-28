
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

We need to use a tool like Postman to make the requests and attach files

Encryption Request Example:

![Encrypt](readme_images/encrypt.png)

Decryption Request Example:

![Decrypt](readme_images/decrypt.png)