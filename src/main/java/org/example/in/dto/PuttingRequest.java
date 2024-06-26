package org.example.in.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.*;
//для сохранения данных
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PuttingRequest {
    private String login;
    private String name;
    private int value;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;




}