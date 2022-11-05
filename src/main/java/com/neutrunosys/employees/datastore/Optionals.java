package com.neutrunosys.employees.datastore;

import java.time.Year;
import java.util.List;
import java.util.Optional;

public class Optionals {
    record Car(String make, String model, String color, Year year){}
    record Person(Long id, String firstName, String lastName, Optional<Car> car) implements Repository.IDable<Long> {}
    public static void main(String[] args) {
        Repository<Person, Long> repo = new Repository<>();
        Optional<String> optMsg = Optional.of("Hello");
        System.out.println(optMsg);


        String msg = "Hello";
        String msg2 = "Szymek";
        Optional<String> optMsg2 = Optional.ofNullable(msg2);
        System.out.println(optMsg2.filter(s -> s.length() > 3).orElse("Invalida"));

        Person p1 = new Person(100L,"Tom", "Yanks", Optional.of(new Car("Tesla", "X", "Red", Year.of(2018))));
        Person p2 = new Person(200L,"Jerry", "Banks", Optional.of(new Car("Tesla", "X", "Red", Year.of(2018))));
        Person p3 = null;
        Person p4 = new Person(200L,"Jerry", "Banks", Optional.of(new Car("Tesla", "X", "Red", Year.of(2018))));
        Person p5 = new Person(200L,"Jerry", "Banks", Optional.of(new Car("Tesla", "X", "Red", Year.of(2018))));

        List<Optional<Person>> people = List.of(Optional.of(p1),Optional.of(p2),Optional.ofNullable(p3),Optional.of(p4),Optional.of(p5));
        people.stream()
                .filter(Optional::isEmpty)
                .map(Optional::get)
                .map(Person::firstName)
                .forEach(System.out::println);
        repo.save(p2);
        repo.save(p1);
        String fName = repo.findById(200L)
                .map(Person::firstName)
                .orElse("Person not found!");
        System.out.println(fName);

        Optional<Person> optPerson = Optional.ofNullable(p2);
        System.out.println(optPerson
                .flatMap(Person::car)
                .map(Car::make)
                .orElse("Make unknown");


    }
}
