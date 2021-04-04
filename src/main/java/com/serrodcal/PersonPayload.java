package com.serrodcal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PersonPayload {

    public String name;
    public LocalDate birth;
    public Status status;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(String birth) {
        this.birth = LocalDate.parse(birth, formatter);
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("deceased")) {
            this.status = Status.DECEASED;
        } else {
            this.status = Status.LIVING;
        }
    }
}
