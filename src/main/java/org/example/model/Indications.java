package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
/**
 * Created by fanat1kq on 04/02/2024.
 * This class is responsible for keeping the track of indications values, name, date and id
 */
@Entity
@Table(name = "indications", schema = "app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Indications {
    @Id
    @SequenceGenerator(name = "transaction_generator", sequenceName = "indications_id_seq", allocationSize = 1, schema = "app")
    @GeneratedValue(generator = "transaction_generator", strategy = GenerationType.SEQUENCE)
    private long id;

    @NotNull
    private String name;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "value")
    private Integer value;
    @Column(name = "date")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Indications that = (Indications) o;
        return Objects.equals(name, that.name) && Objects.equals(value, that.value) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, date);
    }
}
