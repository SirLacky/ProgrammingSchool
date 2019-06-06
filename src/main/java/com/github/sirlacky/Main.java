package com.github.sirlacky;

import com.github.sirlacky.dao.UserDao;
import com.github.sirlacky.model.User;

public class Main {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();


    System.out.println(userDao.findAll());


    }
}