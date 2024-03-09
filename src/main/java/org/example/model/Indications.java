package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by fanat1kq on 04/02/2024.
 * This class is responsible for keeping the track of indications values, name, date and id
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Indications {
    private int indicationsId;
    private String name;
    private Integer value;
    private LocalDate date;

    public Indications(String name, Integer value, LocalDate date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }
}
