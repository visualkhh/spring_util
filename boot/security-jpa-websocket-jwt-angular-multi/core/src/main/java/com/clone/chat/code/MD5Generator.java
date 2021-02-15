package com.clone.chat.code;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.imageio.ImageIO;

public class MD5Generator {
    private String result;

    public MD5Generator(InputStream input) throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException {


        BufferedImage buffImg = ImageIO.read(input);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(buffImg, "png", outputStream);
        byte[] data = outputStream.toByteArray();
        System.out.println("Start MD5 Digest");
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data);
        byte[] hash = md.digest();
        StringBuilder hexString = new StringBuilder();

        for(int i = 0; i < hash.length; ++i) {
            hexString.append(Integer.toString((hash[i] & 255) + 256, 16).substring(1));
        }

        this.result = hexString.toString();
    }

    public String toString() {
        return this.result;
    }
}
