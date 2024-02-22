package org.example.in.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public class IndicationsDTO {

    private String name;
    private Integer value;
    private LocalDate date;

    public IndicationsDTO(String name, Integer value, LocalDate date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public IndicationsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
