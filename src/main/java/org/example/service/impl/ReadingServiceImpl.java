package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.exception.RegisterException;
import org.example.exception.RoleException;
import org.example.model.Role;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.model.Indications;
import org.example.repository.ReadingRepository;
import org.example.service.ReadingService;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The type Transaction service.
 */
@Service
@RequiredArgsConstructor
public class ReadingServiceImpl implements ReadingService {

    private final ReadingRepository readingDAO;
    private final UserService userService;



    @Transactional(readOnly = true)
    @Override
    public  List<Indications> getReadingsByMonthAndYear(int year,int month,long userId){
        List<Indications> allReadings = readingDAO.findAllByUserId(userId);
        List<Indications> currentReadings = new ArrayList<>();
        YearMonth filterFromUser = YearMonth.of(year, month);

        for (Indications meterReading : allReadings) {
            YearMonth readingYearMonth = YearMonth.of(meterReading.getDate().getYear(),
                    meterReading.getDate().getMonth());
            if (readingYearMonth.equals(filterFromUser)) {
                currentReadings.add(meterReading);
            }
        }

        return currentReadings;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Indications> getUserHistory(Long userId, Role role) {
        if (role.equals("ADMIN")){
        return readingDAO.findAllByUserId(userId);}
        else  throw new RoleException("You can't see history. Only admins can");
    }
    //доделать
    @Transactional(readOnly = true)
    @Override
    public List<Indications> getActualHistory(Long userId) {
        List<Indications> meterReadings = readingDAO.findAll();

        Map<Long, List<Indications>> readingsByType = meterReadings.stream()
                .collect(Collectors.groupingBy(Indications::getId));

        List<Indications> lastReadings = new ArrayList<>();

        for (List<Indications> readings : readingsByType.values()) {
            if (!readings.isEmpty()) {
                Indications lastReading = readings.get(readings.size() - 1);
                lastReadings.add(lastReading);
            }
        }
        return lastReadings;
    }



    @Transactional
    @Override
    public Indications  putReading(String nameq, long idUser, int value, LocalDate date) {
        Indications indications = new Indications();
        indications.setUserId(idUser);
        indications.setName(nameq);
        indications.setDate(date);
        indications.setValue(value);

       Indications update=readingDAO.save(indications);
        return update;
    }

    }
