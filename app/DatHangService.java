package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatHangService {

    private Connection conn;

    public DatHangService(Connection conn) {
        this.conn = conn;
    }

    public void themDatHang(int tongChiPhi, int soLuong, int idNguoiDung, int idSanPham) {
        String sql = "INSERT INTO ds_dat_hang (tong_chi_phi, so_luong, idnguoi_dung, idsan_pham) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, tongChiPhi);
            preparedStatement.setInt(2, soLuong);
            preparedStatement.setInt(3, idNguoiDung);
            preparedStatement.setInt(4, idSanPham);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Thêm đặt hàng thành công!");
            } else {
                System.out.println("Thêm đặt hàng không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void suaDatHang(int idDatHang, int tongChiPhi, int soLuong, int idNguoiDung, int idSanPham) {
        String sql = "UPDATE ds_dat_hang SET tong_chi_phi=?, so_luong=?, idnguoi_dung=?, idsan_pham=? WHERE id_dat_hang=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, tongChiPhi);
            preparedStatement.setInt(2, soLuong);
            preparedStatement.setInt(3, idNguoiDung);
            preparedStatement.setInt(4, idSanPham);
            preparedStatement.setInt(5, idDatHang);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Sửa đặt hàng thành công!");
            } else {
                System.out.println("Sửa đặt hàng không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoaDatHang(int idDatHang) {
        String sql = "DELETE FROM ds_dat_hang WHERE id_dat_hang=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idDatHang);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Xóa đặt hàng thành công!");
            } else {
                System.out.println("Xóa đặt hàng không thành công!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> hienThiDanhSachDatHang() {
        List<String> danhSachDatHang = new ArrayList<>();

        try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM ds_dat_hang");
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String thongTinDatHang = "ID: " + resultSet.getInt("id_dat_hang") +
                        ", Tổng chi phí: " + resultSet.getInt("tong_chi_phi") +
                        ", Số lượng: " + resultSet.getInt("so_luong") +
                        ", Ngày: " + resultSet.getTimestamp("ngay") +
                        ", ID Người dùng: " + resultSet.getInt("idnguoi_dung") +
                        ", ID Sản phẩm: " + resultSet.getInt("idsan_pham");

                danhSachDatHang.add(thongTinDatHang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachDatHang;
    }
}
