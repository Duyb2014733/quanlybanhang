package src.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SanPhamService {

    private Connection conn;

    public SanPhamService(Connection conn) {
        this.conn = conn;
    }

    public void themSanPham(String tenSanPham, String kichThuoc, String chatLieu, String mauSac, int gia,
            String xuatXu) {
        String sql = "INSERT INTO san_pham (ten_sp, kich_thuoc, chat_lieu, mau_sac, gia, xuat_xu) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, tenSanPham);
            preparedStatement.setString(2, kichThuoc);
            preparedStatement.setString(3, chatLieu);
            preparedStatement.setString(4, mauSac);
            preparedStatement.setInt(5, gia);
            preparedStatement.setString(6, xuatXu);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Them san pham thanh cong!");
            } else {
                System.out.println("Them san pham khong thanh cong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void suaSanPham(int idSuaSP, String tenSuaSP, String kichThuocSP, String chatLieuSP, String mauSacSP,
            int giaSP, String xuatXuSP) {
        String sql = "UPDATE san_pham SET ten_sp=?, kich_thuoc=?, chat_lieu=?, mau_sac=?, gia=?, xuat_xu=? WHERE idsan_pham=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, tenSuaSP);
            preparedStatement.setString(2, kichThuocSP);
            preparedStatement.setString(3, chatLieuSP);
            preparedStatement.setString(4, mauSacSP);
            preparedStatement.setInt(5, giaSP);
            preparedStatement.setString(6, xuatXuSP);
            preparedStatement.setInt(7, idSuaSP);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sua san pham thanh cong!");
            } else {
                System.out.println("Sua san pham khong thanh cong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoaSanPham(int idSanPham) {
        String sql = "DELETE FROM san_pham WHERE idsan_pham=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idSanPham);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xoa san pham thanh cong!");
            } else {
                System.out.println("Xoa san pham khong thanh cong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void hienThiDanhSachSanPham() {
        String sql = "SELECT * FROM san_pham";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            // In tiêu đề bảng
            System.out.format("%-5s | %-20s | %-10s | %-20s | %-15s | %-10s | %-15s\n",
                    "ID", "Ten San Pham", "Kich Thuoc", "Chat Lieu", "Mau Sac", "Gia", "Xuat Xu");
            System.out.println(
                    "-----------------------------------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                int idSanPham = resultSet.getInt("idsan_pham");
                String tenSP = resultSet.getString("ten_sp");
                String kichThuoc = resultSet.getString("kich_thuoc");
                String chatLieu = resultSet.getString("chat_lieu");
                String mauSac = resultSet.getString("mau_sac");
                int gia = resultSet.getInt("gia");
                String xuatXu = resultSet.getString("xuat_xu");

                // In thông tin sản phẩm
                System.out.format("%-5d | %-20s | %-10s | %-20s | %-15s | %-10d | %-15s\n",
                        idSanPham, tenSP, kichThuoc, chatLieu, mauSac, gia, xuatXu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean kiemTraSanPhamTonTai(int idSanPham) {
        String sql = "SELECT COUNT(*) AS count FROM san_pham WHERE idsan_pham = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idSanPham);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
