package com.github.sirlacky;

import com.github.sirlacky.dao.UserDao;
import com.github.sirlacky.model.User;

public class Main {

    public static void main(String[] args) {

    User user = new User();
    user.setUserName("Marek");
    user.setEmail("marek@pl.pl");
    user.setPassword("abc123");

    UserDao userDao = new UserDao();
    userDao.create(user);

    }
}