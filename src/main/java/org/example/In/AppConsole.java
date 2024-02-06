package org.example.In;

import org.example.model.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.dao.ReadingDAO.*;
import static org.example.dao.UserDAO.createUser;
import static org.example.dao.UserDAO.login;
/**
 * main menu of App
 */
public class AppConsole {
    static public User user= new User();
    static public List<String> readings=new ArrayList<>();
    public static void startApp() throws ParseException {
        while(true) {
            System.out.println("Внимание");
            System.out.println("нажмите цифру желаемого действия");
            System.out.println("1.Войти");
            System.out.println("2.Создать пользователя");
            System.out.println("3.Выйти");
            Scanner scanner = new Scanner(System.in);
            int input = Integer.parseInt(scanner.nextLine().toLowerCase());
            switch (input) {
                case 1:
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Введите пользователя и пароль:");
                    String[] input1 = scan.nextLine().split(" ");
                    if (input1.length<1){
                        System.out.println("невверный формат");
                    }
                    String name = input1[0];
                    String password = input1[1];
                    login(name, password);
                    break;
                case 2:
                    System.out.println("Введите нового пользователя, пароль и права доступа(USER, ADMIN) и :");
                    System.out.println("Пример: юзер 123 ADMIN");
                    System.out.println("ввод производить через пробелы");
                    Scanner sc = new Scanner(System.in);
                    String[] input2 = sc.nextLine().split(" ");
                    if (input2.length<3 ){
                        System.out.println("невверный формат");
                        continue;
                    }
                    String name1=input2[0];
                    String password1=input2[1];
                    String role=input2[2];
                    createUser(name1,password1,role);
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
    public static void AppLoop(String user, String role) throws ParseException {

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
                    getActualCounter(user);
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
                    putCounter(name, value,  year, month,day);
                    break;
                case 3:
                    System.out.println("Введите месяц цифрой от 1 до 12");
                    Scanner in = new Scanner(System.in);
                    int month1 = Integer.parseInt(in.nextLine());
                    System.out.println("Введите год");
                    Scanner in2 = new Scanner(System.in);
                    int year1 = Integer.parseInt(in2.nextLine());
                    getCounterByMonth(year1, month1);
                    break;
                case 4:
                    getCounterStory(role);
                    break;
                case 5:
                    System.out.println("Введите новый вид показания");
                    Scanner scanner4 = new Scanner(System.in);
                    String readingName = scanner4.nextLine().toLowerCase();
                    addList(readingName);
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
