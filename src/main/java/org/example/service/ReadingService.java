package org.example.service;

import org.example.dao.ReadingDAO;
import org.example.model.Indications;

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
     * @param transactionDAO the transaction dao
     * @param playerService  the player service
     */
    public ReadingService(ReadingDAO transactionDAO, UserService playerService) {
        this.readingDAO = transactionDAO;
        this.userService = playerService;
    }

//    @Audit

    public List<Indications> getUserHistory(int userId) {
        return readingDAO.getActualCounter(userId);
    }

//    @Audit

    public List<Indications>  putReading(String nameq, int idUser, int value, LocalDate date) {
        Indications indications = new Indications();
        indications.setName(nameq);
        indications.setDate(date);
        indications.setValue(value);

        List<Indications> update=readingDAO.putCounter(indications);
        return update;
    }

//    @Audit

    }
