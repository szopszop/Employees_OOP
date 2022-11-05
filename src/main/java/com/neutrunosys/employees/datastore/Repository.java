package com.neutrunosys.employees.datastore;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repository<T extends Repository.IDable<V>, V> {
    record Person(String firstName, String lastName, Long id) implements IDable<Long> {}
    interface IDable<U> {
        U id();
    }
    private List<T> records = new ArrayList<>();

    List<T> findAll() {
        return records;
    }

    T save(T record) {
        records.add(record);
        return record;
    }

    Optional<T> findById(long id) {
        return  records.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    public static void main(String[] args) {
//        Repository<String> repo = new Repository<>();
//        repo.save("House");
//        repo.save("tree");
//        repo.save("boat");

        Repository<Person, Long> personRepo = new Repository<>();
        personRepo.save(new Person("Jake", "Johnson", 1L));
        personRepo.save(new Person("Szymek", "Tracz", 2L));
        personRepo.save(new Person("Eryk", "Jurek", 3L));
        System.out.println(personRepo.findAll());
//        System.out.println(repo.findAll());
        System.out.println(personRepo.findById(2));
    }
}
