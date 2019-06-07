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


public class Main {

    public static void main(String[] args) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        UserDao userDao = new UserDao();
        Solution solution = new Solution();
        SolutionDao solutionDao = new SolutionDao();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ArrayList<Solution> odp = new ArrayList<>();
        odp = solutionDao.findAll();

        System.out.println(odp.size());

    }
}