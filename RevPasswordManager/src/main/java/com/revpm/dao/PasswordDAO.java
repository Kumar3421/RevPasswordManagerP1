package com.revpm.dao;

import com.revpm.config.DBConnection;
import com.revpm.util.AESUtil;

import java.sql.*;

public class PasswordDAO {

    public static void add(int userId, String acc, String user, String pass) throws Exception {

        String sql = """
        INSERT INTO password_entries(user_id,account_name,username,encrypted_password)
        VALUES(?,?,?,?)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, acc);
            ps.setString(3, user);
            ps.setString(4, AESUtil.encrypt(pass));
            ps.executeUpdate();
        }
    }

    public static void list(int userId) throws Exception {

        String sql = "SELECT * FROM password_entries WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("account_name") + " | " +
                                rs.getString("username") + " | " +
                                AESUtil.decrypt(rs.getString("encrypted_password"))
                );
            }
        }
    }

    public static void search(int userId, String key) throws Exception {

        String sql = """
        SELECT * FROM password_entries
        WHERE user_id=? AND LOWER(account_name) LIKE ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, "%" + key.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("account_name") + " | " +
                                AESUtil.decrypt(rs.getString("encrypted_password"))
                );
            }
        }
    }
}