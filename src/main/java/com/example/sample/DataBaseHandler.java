package com.example.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import static com.example.sample.Const.*;

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" + USERS_FIRSTNAME + "," +
                USERS_LASTNAME + "," + USERS_USERNAME + "," + Const.USERS_PASSWORD + "," +
                Const.USERS_LOCATION + "," + Const.USERS_GENDER + "," + Const.USERS_BALANCE + ")" + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setString(1, user.getFirstName());
            prST.setString(2, user.getLastName());
            prST.setString(3, user.getUserName());
            prST.setString(4, user.getPassword());
            prST.setString(5, user.getLocation());
            prST.setString(6, user.getGender());
            prST.setString(7, String.valueOf(user.getBalance()));

            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    //Need to be modify, he need to return a object but not resultSet
    public User getUser(String loginText) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + USERS_USERNAME + "=?";
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setString(1, loginText);
            resSet = prST.executeQuery();
            if (resSet.next()) {
                return new User(
                        resSet.getString(USERS_FIRSTNAME),
                        resSet.getString(USERS_LASTNAME),
                        resSet.getString(USERS_USERNAME),
                        resSet.getString(USERS_PASSWORD),
                        resSet.getString(USERS_GENDER),
                        resSet.getString(USERS_LOCATION),
                        resSet.getInt(USERS_BALANCE)
                );
            } else {
                return null;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }


    }


    public void saveuser(User user) {

        String update = "UPDATE " + Const.USER_TABLE + " SET " + USERS_BALANCE + " = " +
                user.getBalance() + " WHERE " + USERS_USERNAME + " = '" + user.getUserName() + "'";

        try {
            PreparedStatement prST = getDbConnection().prepareStatement(update);

            prST.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
