package org.example.dao;

import org.example.dbconfig.ConnectionManager;
import org.example.model.Indications;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanat1kq on 04/02/2024.
 */
public class ReadingDAO {
    public static final Indications indic=new Indications();
    public List<String> readings= new ArrayList<>();

    /**
     * get actual counter
     *
     * @return List<Indication>
     */
    public List<Indications> getActualCounter(int userId) {
        List<Indications> list = new ArrayList<>();
        String sql = "select name, value, date\n" +
                "from app.indications \n" +
                "where extract(month from date) in (select extract(month from max(date)) from app.indications )\n" +
                "  AND extract(year from date) in (select extract(YEAR from max(date)) from app.indications ) and id_ind_user=?";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
    public List<Indications> putCounter(String nameq, int idUser, int value, int year, int month, int day)  {
        LocalDate date= LocalDate.of(year, month, day);
        List<Indications> list = new ArrayList<>();
//        if (readings.contains(nameq)) {
            String sql = "INSERT INTO app.indications (id_ind_user,name,value,date) VALUES (?, ?, ?, ?)";

            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idUser);
                statement.setString(2, nameq);
                statement.setInt(3, value);
                statement.setDate(4, Date.valueOf(date));
                indic.setName(nameq);
                indic.setValue(value);
                indic.setDate(date);
                list.add(indic);
                statement.executeUpdate();
                return list;
            } catch (SQLException e) {
                throw new RuntimeException("Ошибка при сохранении " + e.getMessage());
            }
//        }
//        else {
//            System.out.println("такого нет");
//        }
//        return null;
    }
    /**
     * get counter by month
     *
     * @return List<Indications>
     * @param year  get year of Indication
     * @param month  get month of Indication
     */
    public List<Indications> getCounterByMonth(int year, int month, int userId){
            List<Indications> list = new ArrayList<>();
            String sql = "SELECT * FROM app.indications WHERE EXTRACT(MONTH FROM date) =? and EXTRACT(YEAR FROM date) =? and id_ind_user=?";
            try (Connection connection = ConnectionManager.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, month);
                preparedStatement.setInt(2, year);
                preparedStatement.setInt(3, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
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
    public  List<Indications> getCounterStory(String role)  {
        if(role.equals("ADMIN")){
            List<Indications> list = new ArrayList<>();
        String sql = "SELECT * FROM app.indications ";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
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
    public void addList(String name)  {
        readings.add(name);
        System.out.println(readings);
    }
    /**
    *add readings
     */
    public void defalt() {
        readings.add("горячая вода");
        readings.add("холодная вода");
        readings.add("отопление");
    }
}


