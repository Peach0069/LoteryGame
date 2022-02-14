package com.example.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandler extends Configs  {
    Connection dbConnection;


    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser,dbPass);

        return dbConnection;
    }

    public void signUpUser(User user)  {
                String insert = "INSERT INTO " + Const.USER_TABLE + " (" + Const.USERS_FIRSTNAME +"," +
                 Const.USERS_LASTNAME +","+ Const.USERS_USERNAME + "," + Const.USERS_PASSWORD +"," +
                 Const.USERS_LOCATION + "," + Const.USERS_GENDER + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1,user.getFirstName());
            prST.setString(2,user.getLastName());
            prST.setString(3,user.getUserName());
            prST.setString(4,user.getPassword());
            prST.setString(5,user.getLocation());
            prST.setString(6,user.getGender());

            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setString(1, user.getUserName());
            prST.setString(2, user.getPassword());

            resSet = prST.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;

    }


}
