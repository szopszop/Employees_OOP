package com.neutrunosys.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO extends Employee implements ISuperHuman {

    private int avgStockPrice;
    String ceoRegex = "\\w+=(?<avgStockPrice>\\w+)";
    Pattern ceoPattern = Pattern.compile(ceoRegex);


    public CEO(String personText) {
        super(personText);
        Matcher ceoMatcher = ceoPattern.matcher(peopleMatcher.group("details"));
        if (ceoMatcher.find()) {
            this.avgStockPrice = Integer.parseInt(ceoMatcher.group("avgStockPrice"));
        }
    }

    public int getSalary() {
        return 5000 + avgStockPrice * 10;
    }

    protected void beCEO() {
        System.out.println("I'm CEO!");
    }

    @Override
    public void jump() {
        System.out.println("jump!");
    }
}