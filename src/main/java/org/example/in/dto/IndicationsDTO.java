package org.example.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class IndicationsDTO {

    private String name;
    private Integer value;
    private LocalDate date;

}
