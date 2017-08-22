package com.airlenet.play.core.helper;

import java.util.regex.Pattern;

public class Patterns {

	public static final String REGEX_USERNAME = "^$|^[\\u4E00-\\u9FA5\\uf900-\\ufa2d_a-zA-Z][\\u4E00-\\u9FA5\\uf900-\\ufa2d\\w]{4,16}$";
	public static final String REGEX_MAIL = "^$|\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	public static final String REGEX_MOBILE = "^$|^[1]([3][0-9]{1}|59|58|82|88|89)[0-9]{8}$";
	public static final String REGEX_PURE_LETTER="[a-zA-Z]+";
	public static final String REGEX_PURE_NUMBER="[^(0-9)]";
	public static final String REGEX_LETTER_NUMBER="[^(a-zA-Z0-9\\u4e00-\\u9fa5)]";
	
	public static final Pattern PATTERN_USERNAME = Pattern.compile(REGEX_USERNAME);
	public static final Pattern PATTERN_MAIL = Pattern.compile(REGEX_MAIL);
	public static final Pattern PATTENR_MOBILE = Pattern.compile(REGEX_MOBILE);
	public static final Pattern PATTENR_PURE_LETTER = Pattern.compile(REGEX_PURE_LETTER);
	public static final Pattern PATTENR_PURE_NUMBER = Pattern.compile(REGEX_PURE_NUMBER);
	public static final Pattern PATTENR_LETTER_NUMBER = Pattern.compile(REGEX_LETTER_NUMBER);

	public static boolean isUsername(String username) {
		return PATTERN_USERNAME.matcher(username).matches();
	}

	public static boolean isEmail(String email) {
		return PATTERN_MAIL.matcher(email).matches();
	}

	public static boolean isMobile(String mobile) {
		return PATTENR_MOBILE.matcher(mobile).matches();
	}
	
	public static boolean isPureLetter(String letter){
		return PATTENR_PURE_LETTER.matcher(letter).matches();
	}
	
	public static boolean isPureNumber(String number){
		return PATTENR_PURE_NUMBER.matcher(number).matches();
	}
	
	public static boolean isLetterNumber(String letterNumber){
		return PATTENR_LETTER_NUMBER.matcher(letterNumber).matches();
	}
	
}
