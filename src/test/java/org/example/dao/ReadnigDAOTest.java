//package org.example.dao;
//
//import org.example.model.Indications;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class ReadnigDAOTest {
//    ReadingDAO readingDAO;
//    @BeforeEach
//    public void setUp() {
//
//        readingDAO=new ReadingDAO(connectionManager);
//
//    }
//    @Test
//    public void testGetCounterByMonth() {
//         List<String> readings=new ArrayList<>();
//        readings.add("электричество");
//        List<Indications> expected = readingDAO.putCounter("электричество", 122, 2000, 1, 19);
//        List<Indications> result =  readingDAO.getCounterByMonth(2000,1);
//        Assertions.assertEquals(expected, result);
//    }
//    @Test
//    public void testGetgetActualCounter() {
//        List<String> readings=new ArrayList<>();
//        readings.add("электричество");
//        readingDAO.putCounter("электричество", 122, 2022, 1, 19);
//        readingDAO.putCounter("электричество", 122, 2022, 2, 19);
//
//        List<Indications> expected = readingDAO.putCounter("электричество", 122, 2024, 1, 19);
//        readingDAO.putCounter("электричество", 122, 2023, 12, 19);
//        List<Indications> result =  readingDAO.getActualCounter();
//        Assertions.assertEquals(expected, result);
//    }
//
//
//}
