package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NguoiDungService {
    private Connection conn;

    public NguoiDungService(Connection conn) {
        this.conn = conn;
    }

    public void themNguoiDung(String hoTen, String sdt, String email, String matKhau, String diaChi) {
        String sql = "INSERT INTO nguoi_dung (ho_ten, sdt, email, mat_khau, dia_chi) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, hoTen);
            preparedStatement.setString(2, sdt);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, matKhau);
            preparedStatement.setString(5, diaChi);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Them nguoi dung thanh cong!");
            } else {
                System.out.println("Them nguoi dung khong thanh cong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void suaNguoiDung(int idNguoiDung, String hoTen) {
        String sql = "UPDATE nguoi_dung SET ho_ten = ? WHERE idnguoi_dung = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, hoTen);
            preparedStatement.setInt(2, idNguoiDung);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sua nguoi dung thanh cong!");
            } else {
                System.out.println("Sua nguoi dung khong thanh cong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoaNguoiDung(int idNguoiDung) {
        String sql = "DELETE FROM nguoi_dung WHERE idnguoi_dung = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idNguoiDung);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xoa nguoi dung thanh cong!");
            } else {
                System.out.println("Xoa nguoi dung khong thanh cong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean dangKyTaiKhoan(String hoTen, String sdt, String email, String matKhau, String diaChi) {
        if (kiemTraEmailTonTai(email)) {
            System.out.println("Email da ton tai. Dang ky khong thanh cong.");
            return false;
        }

        String sql = "INSERT INTO nguoi_dung (ho_ten, sdt, email, mat_khau, dia_chi) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, hoTen);
            preparedStatement.setString(2, sdt);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, matKhau);
            preparedStatement.setString(5, diaChi);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean dangNhap(String email, String matKhau) {
        int loggedInUserId = Connect.getLoggedInUserId();
        String sql = "SELECT * FROM nguoi_dung WHERE email = ? AND mat_khau = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, matKhau);

            ResultSet resultSet = preparedStatement.executeQuery();
            Connect.setLoggedInUserId(loggedInUserId);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean kiemTraEmailTonTai(String email) {
        String sql = "SELECT * FROM nguoi_dung WHERE email = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getNguoiDungIdByEmail(String email) {
        int userId = -1; // Default value if not found

        String sql = "SELECT idnguoi_dung FROM nguoi_dung WHERE email = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("idnguoi_dung");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
}
