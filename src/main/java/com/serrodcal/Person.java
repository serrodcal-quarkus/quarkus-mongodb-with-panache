package com.serrodcal;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

import java.time.LocalDate;

public class Person extends ReactivePanacheMongoEntity {

    public String name;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate birth;
    public Status status;

    public Person() { }

    public Person(String name, LocalDate birth, Status status) {
        this.name = name;
        this.birth = birth;
        this.status = status;
    }

    // return name as uppercase in the model
    /*public String getName(){
        return name.toUpperCase();
    }*/

    // store all names in lowercase in the DB
    /*public void setName(String name){
        this.name = name.toLowerCase();
    }*/

}
