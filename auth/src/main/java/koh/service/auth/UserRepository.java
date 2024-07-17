package koh.service.auth;

import koh.service.auth.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (
                Connection conn = Db.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                LocalDateTime localDateTime = rs.getTimestamp("lastLoginTime").toLocalDateTime();
                User user = new User(id, email, password, localDateTime);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public User save(User user) {
        String sql = "INSERT INTO users (email) VALUES (?)";
        try (
                Connection conn = Db.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            pstmt.setString(1, user.getEmail());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        user.setPassword(rs.getString(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
