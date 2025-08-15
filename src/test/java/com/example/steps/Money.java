package com.example.steps;

import java.math.BigDecimal;

public record Money(String currency, BigDecimal amount) { }