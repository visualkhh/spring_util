package com.omnicns.test.encrypt;

import com.omnicns.java.security.Base64Coder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BodyEncryptTest {

	@Before
	public void before(){
	}


	@Test
	public void test() throws Exception {
//		String  key = "e89160b73fa99e706bdd5bb07acedfcf";
//		//privacy-key: hits52a35s6lhvj
//		HttpBodyEncryptor httpBodyEncryptor = new HttpBodyEncryptor(key);
//		String body="8dG1F7F+4LqttM2C6RzLuw==";
//		body = new String(httpBodyEncryptor.decode(body.getBytes("UTF-8")),"UTF-8");
		String str="eyJpZCI6MSwidGl0bGUiOiJhIiwiY29udGVudCI6bnVsbCwicHdkIjpudWxsfQ==";
		System.out.println(Base64Coder.decodeString(str));

		str="{\"name\":\"김현하\",\"date\":\"123\",\"number\":44}";
		str="{\"date\":\"123\",\"number\":44}";
		System.out.println(Base64Coder.encodeString(str));


//		String privacyKey = "e89160b73fa99e706bdd5bb07acedfcf";//32byte
//		String src= "visualkhh";
//
//		for(byte at : toString().getBytes()){
//			System.out.printf("%02X\t",at);
//		}
//
//		System.out.println( AES256Coder.encode(privacyKey, src) );
//
//		src="KFayrR+SsrDO/9mMkFEsUQ==";
//		System.out.println( AES256Coder.decode(privacyKey, src) );
//
//		System.out.println("--------");
//
//		src= "visualkhh";
//		byte[] b = {0x63,	0x6F,	0x6D,	0x2E,	0x6F,	0x6D,	0x6E,	0x69,	0x63,	0x6E	,0x73	,0x2E	,0x74	,0x65	,0x73	,0x74	,0x2E	,0x65	,0x6E	,0x63	,0x72	,0x79	,0x70	,0x74	,0x2E	,0x42	,0x6F	,0x64	,0x79	,0x45	,0x6E	,0x63	,0x72	,0x79	,0x70	,0x74	,0x54	,0x65	,0x73	,0x74	,0x40	,0x32	,0x37	,0x61	,0x62	,0x65	,0x32	,0x63	,0x64};
//		String r = new String (AES256Coder.decode(privacyKey, b) , "UTF-8");
//		System.out.println(r);

//		src="KFayrR+SsrDO/9mMkFEsUQ==";
//		System.out.println( AES256Coder.decode(privacyKey, src) );


//		String key = "1234567890123456";
//		String str = "visualkhh";
//		System.out.println(AES256Coder.encode(key,str));
//
//
//
//		String stre = "8dG1F7F+4LqttM2C6RzLuw==";
//		System.out.println(AES256Coder.decode(key,stre));
//
//
//
//		System.out.println(AES256Coder.encode(key,str));
//		System.out.println(AES256Coder.decode(key,stre));
	}



	@After
	public void after() throws Exception {
	}
}
