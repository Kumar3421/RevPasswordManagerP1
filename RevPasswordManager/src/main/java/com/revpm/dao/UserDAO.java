package com.revpm.dao;

import com.revpm.config.DBConnection;

import java.sql.*;

public class UserDAO {

    public static void register(String name, String email, String hash) throws Exception {

        String sql = "INSERT INTO users(name,email,master_password) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, hash);
            ps.executeUpdate();
        }
    }

    public static ResultSet find(String email) throws Exception {

        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM users WHERE email=?");

        ps.setString(1, email);
        return ps.executeQuery();
    }
}