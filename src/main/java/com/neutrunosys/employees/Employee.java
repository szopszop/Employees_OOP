package com.neutrunosys.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    protected final Pattern peoplePattern = Pattern.compile(peopleRegex);
    protected final Matcher peopleMatcher;
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    int getSalary() {
        return 0;
    }

    public Employee(String personText) {
        peopleMatcher = peoplePattern.matcher(personText);
        if (peopleMatcher.find()) {
            this.lastName = peopleMatcher.group("lastName");
            this.firstName = peopleMatcher.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMatcher.group("dob")));
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s", lastName, firstName, moneyFormat.format(getSalary()));
    }
}
