package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                System.out.println("Thêm người dùng thành công!");
            } else {
                System.out.println("Thêm người dùng không thành công!");
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
                System.out.println("Sửa người dùng thành công!");
            } else {
                System.out.println("Sửa người dùng không thành công!");
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
                System.out.println("Xóa người dùng thành công!");
            } else {
                System.out.println("Xóa người dùng không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
