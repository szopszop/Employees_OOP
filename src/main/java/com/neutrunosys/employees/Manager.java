package com.neutrunosys.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager extends Employee {

    private int orgSize;
    private int directReports;

    String managerRegex = "\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
    Pattern managerPattern = Pattern.compile(managerRegex);

    public Manager(String personText) {
        super(personText);
        Matcher managerMatcher = managerPattern.matcher(peopleMatcher.group("details"));
        if (managerMatcher.find()) {
            this.orgSize = Integer.parseInt(managerMatcher.group("orgSize"));
            this.directReports = Integer.parseInt(managerMatcher.group("dr"));
        }
    }


    public int getSalary() {
        return 3500 + orgSize * directReports / 50;
    }


}
