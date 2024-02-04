package org.example;


import org.example.liquibase.Liquibase;

import java.text.ParseException;
import static org.example.In.AppConsole.startApp;
import static org.example.dao.ReadingDAO.*;


/**
 * Created by fanat1kq on 04/02/2024.
 * start of APP
 */

public class Main {


    public static void main(String[] args) throws ParseException{
        while(true) {
            Liquibase liquibase=new org.example.liquibase.Liquibase();
            liquibase.start();
            defalt();
            startApp();
        }
    }
    }
