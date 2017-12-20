package com.khh.security.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.omnicns.java.jpa.convert.AttributeViewControllConverter;
import com.omnicns.java.jpa.convert.ConverterAttribute;
import com.khh.security.SecurityManager;

public class PrivacyEncryptor extends ConverterAttribute implements AttributeConverter<String, String>, AttributeViewControllConverter<String, String>{
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	public String convertToDatabaseColumn(String attribute) {
		try {
			return SecurityManager.getInstance().privacyEncode(attribute);
		} catch (InvalidKeyException | UnsupportedEncodingException| NoSuchAlgorithmException | NoSuchPaddingException| InvalidAlgorithmParameterException| IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			return SecurityManager.getInstance().privacyDecode(dbData);
		} catch (InvalidKeyException | UnsupportedEncodingException| NoSuchAlgorithmException | NoSuchPaddingException| InvalidAlgorithmParameterException| IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException(e);
		}
	}



}
