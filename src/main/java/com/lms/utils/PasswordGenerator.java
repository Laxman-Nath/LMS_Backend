package com.lms.utils;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

	public String generateRandomPassword() {
		String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCaseChars = "abcdefghijklmnopqrstuvwxyz";
		String digits = "0123456789";
		String allChars = upperCaseChars + lowerCaseChars + digits;
		SecureRandom secureRandom = new SecureRandom();
		StringBuilder password = new StringBuilder();
		password.append(upperCaseChars.charAt(secureRandom.nextInt(upperCaseChars.length())));
		password.append(lowerCaseChars.charAt(secureRandom.nextInt(lowerCaseChars.length())));
		password.append(digits.charAt(secureRandom.nextInt(digits.length())));
		for (int i = 3; i < 8; i++) {
			password.append(allChars.charAt(secureRandom.nextInt(allChars.length())));
		}
		String finalPassword = shufflePassword(password.toString());
		return finalPassword;
	}

	public String shufflePassword(String password) {
		char[] passwordArray = password.toCharArray();
		SecureRandom random = new SecureRandom();
		for (int i = passwordArray.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			char temp = passwordArray[i];
			passwordArray[i] = passwordArray[index];
			passwordArray[index] = temp;
		}
		return new String(passwordArray);
	}
}
