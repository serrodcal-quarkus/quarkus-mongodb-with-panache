package com.serrodcal;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class PersonRepository implements ReactivePanacheMongoRepository<Person> {

    // put your custom logic here as instance methods

    public Uni<Person> findByName(String name){
        return find("name", name).firstResult();
    }

    public Uni<List<Person>> findAlive(){
        return list("status", Status.LIVING);
    }

}
