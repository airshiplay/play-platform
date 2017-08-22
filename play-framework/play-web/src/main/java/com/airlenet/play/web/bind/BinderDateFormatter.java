package com.airlenet.play.web.bind;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.format.Formatter;

public class BinderDateFormatter implements Formatter<Date> {
	@Override
	public String print(Date object, Locale locale) {
		return new DateTime(object).toString("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		if (Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2}").matcher(text).matches()) {
			return DateUtils.parseDate(text, "yyyyMMdd");
		} else if (Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(text)
				.matches()) {
			return DateUtils.parseDate(text, "yyyy-MM-dd");
		} else if (Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}").matcher(text)
				.matches()) {
			return DateUtils.parseDate(text, "yyyy/MM/dd");
		} else if (Pattern.compile("[0-9]{4}年[0-9]{2}月[0-9]{2}日").matcher(text)
				.matches()) {
			return DateUtils.parseDate(text, "yyyy年MM月dd日");
		} else if (Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}").matcher(text)
				.matches()) {
			return DateUtils.parseDate(text, "yyyy/MM/dd HH:mm:ss");
		}
		throw new ParseException(text, 0);
	}

//	public static void main(String[] args) {
//		// [0-9]{4}-[0-9]{2}-[0-9]{2}
//
//		System.out.println(Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}")
//				.matcher("2015-09-09").matches());
//		System.out.println(Pattern.compile("[0-9]{4}[0-9]{2}[0-9]{2}")
//				.matcher("20150109").matches());
//	}
}
