package org.example.dao;

import org.example.dbConfig.ConnectionManager;
import org.example.model.Indications;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.example.In.AppConsole.readings;
/**
 * Created by fanat1kq on 04/02/2024.
 */
public class ReadingDAO {
    public static Indications indic=new Indications();
    /**
     * get actual counter
     *
     * @return List<Indication>
     */
    public static List<Indications> getActualCounter() {
        List<Indications> list = new ArrayList<>();
        String sql = "select name, value, date\n" +
                    "from migration.indications \n" +
                    "where extract(month from date) in (select extract(month from max(date)) from migration.indications )\n" +
                    "  AND extract(year from date) in (select extract(YEAR from max(date)) from migration.indications );";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                indic=new Indications();
                indic.setName(resultSet.getString("name"));
                indic.setValue(resultSet.getInt("value"));
                indic.setDate(resultSet.getDate("date").toLocalDate());
                list.add(indic);
            }
            resultSet.close();
            System.out.println(list);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * put counter in HashMap
     *
     * @return List<Indications> get list of Indications
     * @param nameq get name of Indication
     * @param value  get value of Indication
     * @param year  get year of Indication
     * @param month  get month of Indication
     * @param day  get day of Indication
     */
    public static List<Indications> putCounter(String nameq, int value, int year, int month, int day)  {
        Date date= Date.valueOf(LocalDate.of(year, month, day));
        List<Indications> list = new ArrayList<>();
        if (readings.contains(nameq)) {
            String sql = "INSERT INTO migration.indications (name,value,date) VALUES (?, ?, ?)";

            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, nameq);
                statement.setInt(2, value);
                statement.setDate(3, date);
                indic.setName(nameq);
                indic.setValue(value);
                indic.setDate(date.toLocalDate());
                list.add(indic);
                statement.executeUpdate();
                return list;
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при сохранении " + e.getMessage());
            }
        }
        else {
            System.out.println("такого нет");
        }
        return null;
    }
    /**
     * get counter by month
     *
     * @return List<Indications>
     * @param year  get year of Indication
     * @param month  get month of Indication
     */
    public static List<Indications> getCounterByMonth(int year, int month){
            List<Indications> list = new ArrayList<>();
            String sql = "SELECT * FROM migration.indications WHERE EXTRACT(MONTH FROM date) =? and EXTRACT(YEAR FROM date) =?";
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, month);
                preparedStatement.setInt(2, year);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
//                    indic=new Indications();
                    indic.setName(resultSet.getString("name"));
                    indic.setValue(resultSet.getInt("value"));
                    indic.setDate(resultSet.getDate("date").toLocalDate());
                    list.add(indic);
                }
                resultSet.close();
                System.out.println(list);
                return list;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    /**
     * get story of indications
     *
     * @return Hashmap of indications
     * @param role get role of user
     */
    public static List<Indications> getCounterStory(String role)  {
        if(role.equals("ADMIN")){
            List<Indications> list = new ArrayList<>();
        String sql = "SELECT * FROM migration.indications ";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
//                indic=new Indications();
                indic.setName(resultSet.getString("name"));
                indic.setValue(resultSet.getInt("value"));
                indic.setDate(resultSet.getDate("date").toLocalDate());
                list.add(indic);
            }
            resultSet.close();
            System.out.println(list);
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
        else System.out.println("у вас нет прав просмотра");
        return null;
    }
    /**
     * add new name of indication in DB
     */
    public static void addList(String name)  {
        readings.add(name);
        System.out.println(readings);
    }
    /**
    *add readings
     */
    public static void defalt() {
        readings.add("горячая вода");
        readings.add("холодная вода");
        readings.add("отопление");
    }
}


