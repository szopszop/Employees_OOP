package com.neutrunosys.employees;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
                Flinstone1, Fred1, 1/1/1900, Programmer, {locpd=2000,yoe=11,iq=240}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1400,yoe=2,iq=130}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=1100,yoe=5,iq=10}
                Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1700,yoe=10,iq=110}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=1700,yoe=13,iq=140}
                Rubble1, Barney1, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=200,dr=13}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=500,dr=15}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=150,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=30,dr=20}
                Flinstone1, Wilma1, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=5}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=3}
                Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=2}
                Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=10}
                Rubble, Betty, 4/4/1915, CEO, {avgStockPrice=300}
                Tracz, Szymon, 1/1/1993, Noob
                """;

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        int totalSalaries = 0;
        Employee employee;
        while (peopleMat.find()) {
            employee = switch (peopleMat.group("role")) {
                case "Programmer" -> new Programmer(peopleMat.group());
                case "Manager" -> new Manager(peopleMat.group());
                case "Analyst" -> new Analyst(peopleMat.group());
                case "CEO" -> new CEO(peopleMat.group());
                default -> new Nobody(peopleMat.group());
            };
            System.out.println(employee);
            totalSalaries += employee.getSalary();
        }
        NumberFormat currencyInstance  = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout should be %s%n", currencyInstance.format(totalSalaries));
    }
}
