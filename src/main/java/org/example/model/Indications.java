package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
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
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Integer value;
    @Column(name = "date")
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
//    @Temporal(TemporalType.TIMESTAMP)
//    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
//    @Basic
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonProperty("last_charge_date")
//    @Column (columnDefinition = "DATE")
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
