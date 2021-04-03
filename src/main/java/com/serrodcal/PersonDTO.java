package com.serrodcal;

import java.time.LocalDate;

public class PersonDTO {

    public String name;
    public LocalDate birth;
    public Status status;

    public PersonDTO() { }

    public PersonDTO(String name, LocalDate birth, Status status) {
        this.name = name;
        this.birth = birth;
        this.status = status;
    }

}
