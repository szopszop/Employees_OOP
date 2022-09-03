package com.neutrunosys.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst extends Employee {

    private int projectCount;
    String analystRegex = "\\w+=(?<projectCount>\\w+)";
    Pattern analystPattern = Pattern.compile(analystRegex);


    public Analyst(String personText) {
        super(personText);
        Matcher analystMatcher = analystPattern.matcher(peopleMatcher.group("details"));
        if (analystMatcher.find()) {
            this.projectCount = Integer.parseInt(analystMatcher.group("projectCount"));
        }
    }

    public int getSalary() {
        return 2500 + projectCount;
    }

}
