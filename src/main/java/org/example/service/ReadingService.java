package org.example.service;

import org.example.model.Indications;
import org.example.model.Role;;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface security service.
 */
public interface ReadingService {


    List<Indications> getActualHistory(Long userId);
    List<Indications> getUserHistory(Long userId, Role role);
    List<Indications> getReadingsByMonthAndYear(int year,int month,long userId);

    Indications  putReading(String nameq, long idUser, int value, LocalDate date) ;

    }
