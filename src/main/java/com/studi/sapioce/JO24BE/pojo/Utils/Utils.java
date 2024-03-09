package com.studi.sapioce.JO24BE.pojo.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

	private static final String formatEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	private static final String formatPassword = "^(?=.[A-Z])(?=.[a-z])(?=.\\d)(?=.[\\W_]).{12}$";

	private static final String formatDate = "dd/MM/yyyy";

	public String getFormatEmail() {
		return formatEmail;
	}

	public String getFormatPassword() {
		return formatPassword;
	}

	public String getFormatDate() {
		return formatDate;
	}

	public static boolean estValidFormatString(String regex, String aVerifier) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(aVerifier);
		return matcher.matches();
	}

	public static boolean estValideFormatDate(String dateTexte) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatDate);
        
        try {
            LocalDate.parse(dateTexte, formatter);
            return true; 
        } catch (DateTimeParseException e) {
            return false; 
        }
    }
}
