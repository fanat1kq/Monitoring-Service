package org.example.service;

import org.example.dao.ReadingDAO;
import org.example.model.Indications;
import org.example.model.Role;

import java.time.LocalDate;
import java.util.List;

/**
 * The type Transaction service.
 */

public class ReadingService {

    private final ReadingDAO readingDAO;
    private final UserService userService;

    /**
     * Instantiates a new Transaction service.
     *
     * @param readingDAO the transaction dao
     * @param userService  the player service
     */
    public ReadingService(ReadingDAO readingDAO, UserService userService) {
        this.readingDAO = readingDAO;
        this.userService = userService;
    }

//    @Audit

    public List<Indications> getUserHistory(int userId) {
        return readingDAO.getActualCounter(userId);
    }
    public List<Indications> getAllHistory(Role role) {
        return readingDAO.getCounterStory(role);
    }
    public List<Indications> getCounterByMonth(int year, int month,int userId) {
        return readingDAO.getCounterByMonth(year, month,userId);
    }


//    @Audit

    public Indications  putReading(String nameq, int idUser, int value, LocalDate date) {
        Indications indications = new Indications();
        indications.setIndicationsId(idUser);
        indications.setName(nameq);
        indications.setDate(date);
        indications.setValue(value);

       Indications update=readingDAO.putCounter(indications);
        return update;
    }

//    @Audit

    }
