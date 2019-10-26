package com.omnicns.medicine.test.java;

import com.omnicns.java.security.AES256Coder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SecurityTest {
	public static void main(String[] args) throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {

		String[][] data = {
				{"dhKkwCSrEawF79pDVTRk9w==",	"v+eRVRO5MZCk883GjEYPcQ==", "zOlWOq0YNJ+eg3jOuK7HkQ==",	"U33VeG/GHAbF9Oa1gpB7qw=="},
				{""}
		};

		String privateKey = "tf5o92u24f9j49oujtf45u2q13r591vm";

		for (int i = 0; i < data.length ; i++) {
			String[] sData = data[i];
			for (int j = 0; j < sData.length; j++) {
				System.out.print(AES256Coder.decode(privateKey, sData[j])+"\t");
			}
			System.out.println();
		}
	}
}
