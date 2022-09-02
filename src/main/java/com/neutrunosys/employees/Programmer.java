package com.neutrunosys.employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer implements Employee{

    private String lastName;
    private String firstName;
    private LocalDate dob;
    private int linesOfCode;
    private int yearsOfExp;
    private int iq;

    private final String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);
    private final String programmerRegex = "\\w+\\=(?<locpd>\\w+)\\,\\w+\\=(?<yoe>\\w+)\\,\\w+\\=(?<iq>\\w+)";
    private final Pattern programmerPat = Pattern.compile(programmerRegex);
    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Programmer(String personText) {
            Matcher peopleMat = peoplePat.matcher(personText);
            if (peopleMat.find()) {
                this.lastName = peopleMat.group("lastName");
                this.firstName = peopleMat.group("firstName");
                this.dob = LocalDate.from(dtFormatter.parse(peopleMat.group("dob")));
                Matcher programmerMat = programmerPat.matcher(peopleMat.group("details"));
                if (programmerMat.find()) {
                    this.linesOfCode = Integer.parseInt(programmerMat.group("locpd"));
                    this.yearsOfExp = Integer.parseInt(programmerMat.group("yoe"));
                    this.iq = Integer.parseInt(programmerMat.group("iq"));
                }
            }
    }
    public int getSalary() {
        // salary = 3000 + locpd * yoe * iq
        return 3000 + linesOfCode + yearsOfExp + iq;
    }

    @Override
    public String toString() {
        return String.format("%s, %s: %s", lastName, firstName, moneyFormat.format(getSalary()));
    }
}
