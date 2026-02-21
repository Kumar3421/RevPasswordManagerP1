package com.revpm.dao;

import com.revpm.config.DBConnection;
import java.sql.*;

public class SecurityQuestionDAO {

    public static void save(int userId, String question, String hash) throws Exception {

        String delete = "DELETE FROM security_questions WHERE user_id=?";
        String insert = """
            INSERT INTO security_questions(user_id,question,encrypted_answer)
            VALUES (?,?,?)
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement d = con.prepareStatement(delete);
             PreparedStatement i = con.prepareStatement(insert)) {

            d.setInt(1, userId);
            d.executeUpdate();

            i.setInt(1, userId);
            i.setString(2, question);
            i.setString(3, hash);
            i.executeUpdate();
        }
    }

    public static String getAnswer(int userId) throws Exception {

        String sql = "SELECT encrypted_answer FROM security_questions WHERE user_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getString(1) : null;
        }
    }
}