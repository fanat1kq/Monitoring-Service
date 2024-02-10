package org.example;


import org.example.dao.ReadingDAO;
import org.example.in.AppConsole;
import org.example.liquibase.Liquibase;

import java.text.ParseException;
import static org.example.dao.ReadingDAO.*;


/**
 * Created by fanat1kq on 04/02/2024.
 * start of APP
 */

public class Main {


    public static void main(String[] args) throws ParseException{
            Liquibase liquibase=new Liquibase();
            liquibase.start();
            ReadingDAO readingDAO= new ReadingDAO();
            readingDAO.defalt();
            AppConsole appConsole= new AppConsole();
            appConsole.startApp();
        }
    }

