package com.neutrunosys.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee {

    private int linesOfCode;
    private int yearsOfExp;
    private int iq;

    private final String programmerRegex = "\\w+\\=(?<locpd>\\w+)\\,\\w+\\=(?<yoe>\\w+)\\,\\w+\\=(?<iq>\\w+)";
    private final Pattern programmerPattern = Pattern.compile(programmerRegex);

    public Programmer(String personText) {
        super(personText);
        Matcher programmerMatcher = programmerPattern.matcher(peopleMatcher.group("details"));
        if (programmerMatcher.find()) {
            this.linesOfCode = Integer.parseInt(programmerMatcher.group("locpd"));
            this.yearsOfExp = Integer.parseInt(programmerMatcher.group("yoe"));
            this.iq = Integer.parseInt(programmerMatcher.group("iq"));
        }
    }

    public int getSalary() {
        return 3000 + linesOfCode + yearsOfExp + iq;
    }


}
