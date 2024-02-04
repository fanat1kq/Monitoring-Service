package org.example.model;

import java.time.LocalDate;

/**
 * Created by fanat1kq on 04/02/2024.
 * This class is responsible for keeping the track of indications values, name, date and id
 */

public class Indications {
    public int indicationsId;
    public String name;
    public int value;
    public LocalDate date;

    public Indications() {

    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
    /**
     *
     * @return the name of the indication.
     */

    public String getName() {
        return name;
    }
    /**
     * Used to update the name of indication
     * @param name new name of the indication.
     */

    public void setName(String name) {
        this.name = name;
    }
    /**
     *
     * @return the value of the indication.
     */
    public int getValue() {
        return value;
    }
    /**
     * Used to update the indication's value.
     * @param value new value of the indication.
     */

    public void setValue(int value) {
        this.value = value;
    }
    /**
     *
     * @return the date of the indication.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Used to update the date's putting of indication.
     * @param date new date of the indication.
     */

    public void setDate(LocalDate date) {
        this.date = date;
    }
    /**
     * Creates a new Indication object.
     * @param name name of the indication
     * @param value value of the indication
     * @param date date of putting the indication
     */
    public Indications(String name, int value, LocalDate date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }
}
