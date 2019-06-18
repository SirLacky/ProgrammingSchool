package com.github.sirlacky;

import com.github.sirlacky.dao.UserDao;
import com.github.sirlacky.dao.ExerciseDao;
import com.github.sirlacky.model.Exercise;
import com.github.sirlacky.model.User;


import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();
        ExerciseDao exerciseDao = new ExerciseDao();
        System.out.println("Witaj w programie! Wybierz interesujący cię panel: ");
        System.out.println("Panel Administracyjny - zarządzanie użytkownikami [1]");
        System.out.println("Panel Administracyjny - zarządzanie zadaniami [2]");
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
            System.out.println("Witaj Adminie. Oto lista zadań: ");

            List<Exercise>exercises = exerciseDao.findAll();
            System.out.println(exercises.toString());

            System.out.println("Wpisz komende: add / edit / delete / quit");
            Scanner scan = new Scanner(System.in);
            String adminCommend = scan.nextLine();

            if (adminCommend.equals("add")) {
                Scanner scan1 = new Scanner(System.in);
                Exercise exercise = new Exercise();
                System.out.println("Podaj tytuł zadania: ");
                String title = scan.next();
                exercise.setTitle(title);
                System.out.println("Podaj opis zadania: ");
                String description = scan.next();
                exercise.setDescription(description);
                exerciseDao.create(exercise);

            } else if (adminCommend.equals("edit")) {
                Scanner scan1 = new Scanner(System.in);
                System.out.println("Podaj id zadania do edycji: ");
                int exerciseId = scan.nextInt();
                Exercise exercise = new Exercise();
                exercise = exerciseDao.readById(exerciseId);
                System.out.println("Edycja zadania: " + exercise.toString());
                System.out.println("Podaj nowy tytuł zadania: ");
                String title = scan.next();
                exercise.setTitle(title);
                System.out.println("Podaj nowy opis zadania: ");
                String description = scan.next();
                exercise.setDescription(description);
                exerciseDao.update(exercise);
                System.out.println("Zedytowano zadanie: " + exercise.toString());

            } else if (adminCommend.equals("delete")) {

                Scanner scan2 = new Scanner(System.in);
                System.out.println("Podaj id zadania do usunięcia: ");
                int exerciseId = scan.nextInt();
                exerciseDao.delete(exerciseId);
                System.out.println("Usunięto zadanie o id: "+exerciseId);

            } else {
                System.out.println("Koniec panelu Administracyjnego");
            }







        } else {
            System.out.println("Wprowadzono nieproprawne dane");
        }
    }
}