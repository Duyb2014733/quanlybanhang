package app;

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
                System.out.println("Thêm sản phẩm thành công!");
            } else {
                System.out.println("Thêm sản phẩm không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void suaSanPham(int idSanPham, String tenSanPham, String kichThuoc, String chatLieu, String mauSac, int gia,
            String xuatXu) {
        String sql = "UPDATE san_pham SET ten_sp=?, kich_thuoc=?, chat_lieu=?, mau_sac=?, gia=?, xuat_xu=? WHERE idsan_pham=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, tenSanPham);
            preparedStatement.setString(2, kichThuoc);
            preparedStatement.setString(3, chatLieu);
            preparedStatement.setString(4, mauSac);
            preparedStatement.setInt(5, gia);
            preparedStatement.setString(6, xuatXu);
            preparedStatement.setInt(7, idSanPham);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sửa sản phẩm thành công!");
            } else {
                System.out.println("Sửa sản phẩm không thành công!");
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
                System.out.println("Xóa sản phẩm thành công!");
            } else {
                System.out.println("Xóa sản phẩm không thành công!");
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
            System.out.format("%-5s%-20s%-10s%-20s%-15s%-10s%-15s\n",
                    "ID", "Tên Sản Phẩm", "Kích Thước", "Chất Liệu", "Màu Sắc", "Giá", "Xuất Xứ");
            System.out.println("-------------------------------------------------------------");

            while (resultSet.next()) {
                int idSanPham = resultSet.getInt("idsan_pham");
                String tenSP = resultSet.getString("ten_sp");
                String kichThuoc = resultSet.getString("kich_thuoc");
                String chatLieu = resultSet.getString("chat_lieu");
                String mauSac = resultSet.getString("mau_sac");
                int gia = resultSet.getInt("gia");
                String xuatXu = resultSet.getString("xuat_xu");

                // In thông tin sản phẩm
                System.out.format("%-5d%-20s%-10s%-20s%-15s%-10d%-15s\n",
                        idSanPham, tenSP, kichThuoc, chatLieu, mauSac, gia, xuatXu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
