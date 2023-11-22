package app;

import java.sql.Connection;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection conn = Connect.getConnect();
        NguoiDungService nguoiDungService = new NguoiDungService(conn);

        // Menu
        while (true) {
            System.out.println("1. Thêm người dùng");
            System.out.println("2. Sửa người dùng");
            System.out.println("3. Xóa người dùng");
            System.out.println("4. Hiển thị danh sách sản phẩm");
            System.out.println("5. Thêm sản phẩm");
            System.out.println("6. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Nhập họ tên: ");
                    String hoTen = sc.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String sdt = sc.nextLine();
                    System.out.print("Nhập email: ");
                    String email = sc.nextLine();
                    System.out.print("Nhập mật khẩu: ");
                    String matKhau = sc.nextLine();
                    System.out.print("Nhập địa chỉ: ");
                    String diaChi = sc.nextLine();

                    nguoiDungService.themNguoiDung(hoTen, sdt, email, matKhau, diaChi);
                    break;

                case 2:
                    System.out.print("Nhập ID người dùng cần sửa: ");
                    int idSua = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    System.out.print("Nhập họ tên mới: ");
                    String hoTenMoi = sc.nextLine();

                    nguoiDungService.suaNguoiDung(idSua, hoTenMoi);
                    break;

                case 3:
                    System.out.print("Nhập ID người dùng cần xóa: ");
                    int idXoa = sc.nextInt();
                    nguoiDungService.xoaNguoiDung(idXoa);
                    break;

                case 4:
                    // Display the list of products
                    // SanPhamService.hienThiDanhSachSanPham();
                    break;

                case 5:
                    // Add a new product
                    System.out.print("Nhập tên sản phẩm: ");
                    String tenSP = sc.nextLine();
                    System.out.print("Nhập kích thước: ");
                    String kichThuoc = sc.nextLine();
                    System.out.print("Nhập chất liệu: ");
                    String chatLieu = sc.nextLine();
                    System.out.print("Nhập màu sắc: ");
                    String mauSac = sc.nextLine();
                    System.out.print("Nhập giá: ");
                    int gia = sc.nextInt();
                    sc.nextLine(); // Consume the newline character
                    System.out.print("Nhập xuất xứ: ");
                    String xuatXu = sc.nextLine();

                    // SanPhamService.themSanPham(tenSP, kichThuoc, chatLieu, mauSac, gia, xuatXu);
                    break;
                case 6:
                    Connect.disconnect();
                    System.out.println("Thoát chương trình.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
