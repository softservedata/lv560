package com.softserve.edu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppRegex {
    public static void main(String[] args) {
		//
		String pattern ="\\D+";
		String text = "0123456789";
		//
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		//
		// 1. Is correspond
		if (m.matches()) {
			System.out.println("m.matches() OK");
		} else {
			System.out.println("m.matches() FALSE");
		}
		//
		// 2. Get all parts
		m.reset(); // Reset Iterator
		while (m.find()) {
			System.out.print(text.substring(m.start(), m.end()) + "*");
		}

	}
}
