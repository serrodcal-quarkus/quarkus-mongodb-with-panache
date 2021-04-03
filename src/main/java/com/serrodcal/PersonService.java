package com.serrodcal;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PersonService {

    @Inject
    PersonRepository repository;

    public Uni<List<Person>> findAll() {
        return this.repository.findAll().list();
    }

    public Uni<Person> findByName(String name) {
        return this.repository.findByName(name.toUpperCase());
    }

    public Uni<List<Person>> findAlive() {
        return this.repository.findAlive();
    }

    public Uni<Void> createPerson(PersonDTO personDTO) {
        Person person = new Person(personDTO.name, personDTO.birth, personDTO.status);
        return this.repository.persist(person);
    }

}
