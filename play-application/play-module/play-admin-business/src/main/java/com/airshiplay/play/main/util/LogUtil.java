package com.airshiplay.play.main.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtil {

	public static String getThrowableString(Throwable thr) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		thr.printStackTrace(pw);
		return sw.toString();
	}
}
