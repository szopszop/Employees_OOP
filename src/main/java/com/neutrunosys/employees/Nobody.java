package com.neutrunosys.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Nobody extends Employee {
    private String lastName;
    private String firstName;
    private LocalDate dob;
    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Nobody(String personText) {
        super(personText);
    }
    public int getSalary() {
        return 0;
    }

}
