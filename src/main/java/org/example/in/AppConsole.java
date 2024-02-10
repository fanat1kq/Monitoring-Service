package org.example.in;

import org.example.dao.ReadingDAO;
import org.example.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * main menu of App
 */
public class AppConsole {
    public static List<String> readings=new ArrayList<>();
    Scanner scanner= new Scanner(System.in);;
    final ReadingDAO readingDAO = new ReadingDAO();
    UserDAO userDAO;
    public void startApp()  {
        while(true) {
            System.out.println("Внимание");
            System.out.println("нажмите цифру желаемого действия");
            System.out.println("1.Войти");
            System.out.println("2.Создать пользователя");
            System.out.println("3.Выйти");
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1:
                    System.out.println("Введите пользователя и пароль:");
                    String[] input1 = scanner.nextLine().split(" ");
                    if (input1.length<1){
                        System.out.println("невверный формат");
                    }
                    String name = input1[0];
                    String password = input1[1];
                    userDAO=new UserDAO();
                    userDAO.login(name, password);
                    break;
                case 2:
                    System.out.println("Введите нового пользователя, пароль и права доступа(USER, ADMIN) и :");
                    System.out.println("Пример: юзер 123 ADMIN");
                    System.out.println("ввод производить через пробелы");
                    String[] input2 = scanner.nextLine().split(" ");
                    if (input2.length<3 ){
                        System.out.println("невверный формат");
                        continue;
                    }
                    String name1=input2[0];
                    String password1=input2[1];
                    String role=input2[2];
                    userDAO=new UserDAO();
                    userDAO.createUser(name1,password1,role);
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
            }
        }
    }
    /**
     * Menu of work with indications
     * @param role role of user
     */
    public void AppLoop(String user, String role, int idUSer) {

        while (true) {
            System.out.println("Привет "+ user+"! Права доступа "+ role);
            System.out.println("1.Получить актуальное показание");
            System.out.println("2.Подать показания");
            System.out.println("3.Получить показания в конкретный месяц");
            System.out.println("4.История получения");
            System.out.println("5. Добавить перечень");
            System.out.println("6. Обратно в меню");
            System.out.println("7. Выход");
            Scanner scanner = new Scanner(System.in);
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1:
                    readingDAO.getActualCounter(idUSer);
                    break;
                case 2:
                    System.out.println("Введите название показания из списка");
                    System.out.println(readings);
                    Scanner scanner3 = new Scanner(System.in);
                    String name = scanner3.nextLine();
                    System.out.println("Введите количество показания");
                    Scanner scanner1 = new Scanner(System.in);
                    int value = Integer.parseInt(scanner1.nextLine());
                    System.out.println("Введите дату");
                    System.out.println("пример 10/04/1995");
                    Scanner scanner2 = new Scanner(System.in);
                    String[] input3 = scanner2.nextLine().split("/");
                    int day = Integer.parseInt(input3[0]);
                    int month = Integer.parseInt(input3[1]);
                    int year = Integer.parseInt(input3[2]);
                    readingDAO.putCounter(name,idUSer, value,  year, month,day);
                    break;
                case 3:
                    System.out.println("Введите месяц цифрой от 1 до 12");
                    Scanner in = new Scanner(System.in);
                    int month1 = Integer.parseInt(in.nextLine());
                    System.out.println("Введите год");
                    Scanner in2 = new Scanner(System.in);
                    int year1 = Integer.parseInt(in2.nextLine());
                    readingDAO.getCounterByMonth(year1, month1,idUSer);
                    break;
                case 4:
                    readingDAO.getCounterStory(role);
                    break;
                case 5:
                    System.out.println("Введите новый вид показания");
                    Scanner scanner4 = new Scanner(System.in);
                    String readingName = scanner4.nextLine().toLowerCase();
                    readingDAO.addList(readingName);
                    break;
                case 6:
                    startApp();
                    break;
                case 7:
                    scanner.close();
                    System.exit(0);
            }
        }}

}
