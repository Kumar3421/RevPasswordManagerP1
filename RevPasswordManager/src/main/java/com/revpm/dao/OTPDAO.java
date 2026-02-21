package com.revpm.dao;

import com.revpm.config.DBConnection;

import java.sql.*;
import java.time.Instant;

public class OTPDAO {

    public static void save(int userId, String otp) throws Exception {

        String sql = "INSERT INTO otp_requests(user_id,otp_code,expiry_time) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, otp);
            ps.setTimestamp(3, Timestamp.from(Instant.now().plusSeconds(300)));

            ps.executeUpdate();
        }
    }

    public static boolean verify(int userId, String input) throws Exception {

        String sql = """
            SELECT otp_code,expiry_time FROM otp_requests
            WHERE user_id=? ORDER BY id DESC LIMIT 1
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            return rs.next()
                    && rs.getString(1).equals(input)
                    && rs.getTimestamp(2).toInstant().isAfter(Instant.now());
        }
    }
}