package com.neutrunosys.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee implements IEmployee {

    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    private static final String PEOPLE_REGEX = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    public static final Pattern PEOPLE_PATTERN = Pattern.compile(PEOPLE_REGEX);
    protected final Matcher peopleMatcher;
    protected String lastName;
    protected String firstName;
    protected LocalDate dob;
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");



    public Employee(String personText) {
        peopleMatcher = PEOPLE_PATTERN.matcher(personText);
        if (peopleMatcher.find()) {
            this.lastName = peopleMatcher.group("lastName");
            this.firstName = peopleMatcher.group("firstName");
            this.dob = LocalDate.from(dtFormatter.parse(peopleMatcher.group("dob")));
        }
    }

    public static Employee createEmployee (String employeeText) {
        Matcher peopleMat = Employee.PEOPLE_PATTERN.matcher(employeeText);

        if (peopleMat.find()) {
            return switch (peopleMat.group("role")) {
                case "Programmer" -> new Programmer(peopleMat.group());
                case "Manager" -> new Manager(peopleMat.group());
                case "Analyst" -> new Analyst(peopleMat.group());
                case "CEO" -> new CEO(peopleMat.group());
                default -> new DummyEmployee(peopleMat.group());
            };
        } else {
            return null;
        }
    }

    @Override
    public abstract int getSalary();

    public double getBonus() {
        return getSalary() * 1.10;
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s - %s", lastName, firstName,
                moneyFormat.format(getSalary()), moneyFormat.format(getBonus()));
    }


    private static final class DummyEmployee extends Employee {

        public DummyEmployee(String personText) {
            super(personText);
        }

        @Override
        public int getSalary() {
            return 0;
        }
    }
}
