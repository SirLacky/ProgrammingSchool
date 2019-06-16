package com.github.sirlacky;

import com.github.sirlacky.dao.SolutionDao;
import com.github.sirlacky.dao.UserDao;
import com.github.sirlacky.model.Solution;
import com.github.sirlacky.model.User;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();
        System.out.println("Witaj w programie! Wybierz interesujący cię panel: ");
        System.out.println("Administrator [1]");
        System.out.println("User [2]");
        int nextInt = scanner.nextInt();

        if (nextInt == 1) {
            System.out.println("Witaj Adminie. Oto lista użytkowników: ");
            List<User> users = userDao.findAll();
            System.out.println(users.toString());

            System.out.println("Wpisz komende: add / edit / delete / quit");
            Scanner scan = new Scanner(System.in);
            String adminCommend = scan.nextLine();

            if (adminCommend.equals("add")) {
                Scanner scan1 = new Scanner(System.in);
                User user = new User();
                System.out.println("Podaj nick użytkownika: ");
                String nick = scan.next();
                user.setUserName(nick);
                System.out.println("Podaj hasło użytkownika: ");
                String password = scan.next();
                user.setPassword(password);
                System.out.println("Podaj maila użytkownika: ");
                String email = scan.next();
                user.setEmail(email);
                userDao.create(user);

            } else if (adminCommend.equals("edit")) {
                Scanner scan1 = new Scanner(System.in);
                System.out.println("Podaj id użytkownika do edycji: ");
                int userId = scan.nextInt();
                User user = new User();
                user = userDao.readById(userId);
                System.out.println("Edycja użytkownika: " + user.toString());
                System.out.println("Podaj nowy nick użytkownika: ");
                String nick = scan.next();
                user.setUserName(nick);
                System.out.println("Podaj nowe hasło użytkownika: ");
                String password = scan.next();
                user.setPassword(password);
                System.out.println("Podaj nowego maila użytkownika: ");
                String email = scan.next();
                user.setEmail(email);
                userDao.update(user);
                System.out.println("Zedytowano dane użytkownika: " + user.toString());

            } else if (adminCommend.equals("delete")) {

                Scanner scan2 = new Scanner(System.in);
                System.out.println("Podaj id użytkownika do usunięcia: ");
                int userId = scan.nextInt();
                userDao.delete(userId);
                System.out.println("Usunięto użytkownika o id: "+userId);
                
            } else {
                System.out.println("Koniec panelu Administracyjnego");
            }


        } else if (nextInt == 2) {
            System.out.println("Witaj Userze");
        } else {
            System.out.println("Wprowadzono nieproprawne dane");
        }
    }
}