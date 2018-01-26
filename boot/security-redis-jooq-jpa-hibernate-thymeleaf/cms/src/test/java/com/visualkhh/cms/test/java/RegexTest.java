package com.visualkhh.cms.test.java;

import com.omnicns.java.string.StringUtil;

public class RegexTest {
	public static void main(String[] args) {
		boolean sw = StringUtil.isMatches("/conts/qtraining/asd/22","/conts/qtraining/.*/[0-9]{2}");
		System.out.printf("->"+sw);
	}
}
