package org.example;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.*;

/**
 * Created by fanat1kq on 28/01/2024.
 * implements users, indications
 * using HashMap
 */

public class Main {
    static User user= new User();
    static Indications indic =new Indications();
    static HashMap<User,Role> map=new HashMap<>();
    static HashMap<Indications, User> indication=new HashMap<>();

    public static void main(String[] args) throws ParseException {
        while(true) {
            startApp();
        }

    }
    /**
     * main menu of App
     */
    public static void startApp() throws ParseException {
        while(true) {
            System.out.println("нажмите цифру желаемого действия");
            System.out.println("1.Войти");
            System.out.println("2.Создать пользователя");
            System.out.println("3.Выйти");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("1")) {
                login();
            } else if (input.equals("2")) {
                createUser();
            }
            else if (input.equals("3")) {
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("попробуйте еще раз");
            }
        }
    }
    /**
     * login
     * @exception ParseException
     */
    public static void login() throws ParseException {
        while(true) {
            if (map.isEmpty()){
                System.out.println("Сначала создайте пользователя!");
                startApp();
            } else {
                Scanner sc = new Scanner(System.in);
                System.out.println("Введите пользователя и пароль:");
                String[] input = sc.nextLine().split(" ");
                if (input.length<1){
                    System.out.println("невверный формат");
                }
                String nameUser = input[0];
                String password = input[1];
                user.setPassword(password);
                user.setName(nameUser);
                if (map.containsKey(user) ) {
                    System.out.println("Поздравляю! Вы успешно авторизовались!");
                    System.out.println("Права доступа - " + map.get(user));
                    AppLoop();
                } else System.out.println("такого пользователя нет либо неправильный пароль, попробкйте еще раз");
            }
        }}

    /**
     * create user
     * @exception ParseException
     */

    public static void createUser() throws ParseException {
        while(true) {
            System.out.println("Введите нового пользователя, пароль и права доступа(USER, ADMIN) и :");
            System.out.println("Пример: юзер 123 ADMIN");
            System.out.println("ввод производить через пробелы");
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split(" ");
            if (input.length<3 ){
                System.out.println("невверный формат");
                continue;
            }
            user.setName(input[0]);
            user.setPassword(input[1]);
            Role role = Role.valueOf(input[2]);
            if (user.getName().length()>0 && user.getPassword().length()>0 && !user.getPassword().equals(null) &&
                    !user.getName().equals(null)){
                if (map.containsKey(user)) {
                    System.out.println(user.getName() + " уже существует, создайте другого");
                } else {
                    map.put(user, role);
                    startApp();
                }
            } else {System.out.println("Некорректное значение! Введите логин и пароль через пробел:");}
        }}
    /**
     *menu working with indications
     * @exception ParseException
     */
    public static void AppLoop() throws ParseException {

while (true){
        System.out.println("1.Получить актуальное показание");
        System.out.println("2.Подать показания");
        System.out.println("3.Получить показания в конкретный месяц");
        System.out.println("4.История получения");
        System.out.println("5. Добавить перечень");
        System.out.println("6. Обратно в меню");
        System.out.println("7. Выход");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        if (input.equals("1")) {
            getActualCounter();
        } else if (input.equals("2")) {
            putCounter();
        }else if (input.equals("3")) {
            getCounterByMonth();
        }else if (input.equals("4")) {
            getCounterStory();
        }else if (input.equals("5")) {
            addList();
        }else if (input.equals("6")) {
            startApp();
        }
        else if (input.equals("7")) {
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("попробуйте еще раз");
        }}
    }
    /**
     * get actual counter
     * @return HashMap<Indication,User>
     */
    public static HashMap<Indications, User> getActualCounter() {
        LocalDate max = LocalDate.MIN;
        indic.setName(user.getName());
        for (Indications key : indication.keySet()){

            if (key.getDate().compareTo(max)>0)
            {
                max=key.getDate();
            }
        }//
        for (Indications key : indication.keySet()){
            if (indication.containsValue(user) &&key.getDate().getMonth()==max.getMonth() &&key.getDate().getYear()==max.getYear()){
                System.out.println(key);
            }
        }
        return indication;
    }
    /**
     * put counter in HashMap
     */
    public static void putCounter()  {
        System.out.println("Введите название показания");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("Введите количество показания");
        Scanner scanner1 = new Scanner(System.in);
        int value = Integer.parseInt(scanner1.nextLine());
        System.out.println("Введите дату");
        System.out.println("пример 10/04/1995");
        Scanner scanner2 = new Scanner(System.in);
        String[] input = scanner2.nextLine().split("/");
        int day = Integer.parseInt(input[0]);
        int month = Integer.parseInt(input[1]);
        int year = Integer.parseInt(input[2]);

        for (Indications key : indication.keySet()) {
            if (!key.name.equals(name) && !YearMonth.of(key.date.getYear(), key.date.getMonth()).equals(YearMonth.of(year, month))) {
                continue;
            }
            System.out.println("в это месяце уже вводили показания, попробуйте другой месяц");
        }

        Indications indications1 = new Indications(name, value, java.time.LocalDate.of(year, month, day));
        indication.put(indications1,user);
    }

    /**
     * get counter by month
     *
     * @return List<Indications>
     */
    public static List<Indications> getCounterByMonth()  {
        List<Indications> result=new ArrayList<>();
        System.out.println("Введите месяц цифрой от 1 до 12");
        Scanner in = new Scanner(System.in);
        int month = Integer.parseInt(in.nextLine());
        for (Indications key : indication.keySet()){
            if (key.date.getMonth()==(Month.of(month))){
                result.add(key);
        }
    }
        System.out.println(result);
        return result;
    }
    /**
     * add new name of indication in HashMap
     * @return Hashmap of indications
     */
    public static HashMap<Indications, User> getCounterStory()  {
        if(map.get(user).equals(Role.ADMIN)){
            return indication;}
        else System.out.println("у вас нет прав просмотра");

        return null;
    }
    /**
     * add new name of indication in HashMap
     */
    public static void addList()  {
        System.out.println("Введите новый вид показания");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine().toLowerCase();
        Indications indications1 = new Indications(name, 0, java.time.LocalDate.of(1111, 1, 1));
        indication.put(indications1,user);
    }
}