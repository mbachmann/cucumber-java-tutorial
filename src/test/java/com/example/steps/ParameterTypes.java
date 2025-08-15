package com.example.steps;

import java.math.BigDecimal;

import io.cucumber.java.ParameterType;

public class ParameterTypes {

	@ParameterType("(?i)checked|unchecked|on|off|true|false|yes|no")
	public boolean state(String input) {
		String s = input.trim().toLowerCase();
		return s.equals("checked") || s.equals("on") || s.equals("true") || s.equals("yes");
	}

	@ParameterType("(?i)[A-Z]{3}\\s+\\d+(?:\\.\\d+)?")
	public Money money(String text) {
		String[] parts = text.trim().split("\\s+");
		String currency = parts[0].toUpperCase();
		BigDecimal amount = new BigDecimal(parts[1]);
		return new Money(currency, amount);
	}
}
