package src.app;

import java.sql.Connection;
import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        showMainLoginMenu();
    }

    private static void dangXuat() {
        Connect.setLoggedInUserId(-1);
        showMainLoginMenu();
    }

    private static void showMainLoginMenu() {
        Scanner sc = new Scanner(System.in);
        Connection conn = Connect.getConnect();
        NguoiDungService nguoiDungService = new NguoiDungService(conn);

        // Display menu
        while (true) {
            System.out.println("\n");
            System.out.println("1. Dang Nhap");
            System.out.println("2. Dang Ky");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Đăng Nhập
                    System.out.println("\n");
                    System.out.print("Nhap Email: ");
                    String loginEmail = scanner.next();
                    System.out.print("Nhap Mat Khau: ");
                    String loginPassword = scanner.next();
                    if (nguoiDungService.dangNhap(loginEmail, loginPassword)) {
                        Connect.setLoggedInUserId(nguoiDungService.getNguoiDungIdByEmail(loginEmail));
                        if (loginEmail.equals("admin@gmail.com")) {
                            showMenuAdmin();
                        } else {
                            showUser();
                        }
                    } else {
                        System.out.println("Dang nhap khong thong cong. Kiem tra lai thong tin!!!");
                    }
                    break;

                case 2:
                    // Đăng Ký
                    System.out.println("\n");
                    scanner.nextLine();
                    System.out.print("Nhap Ho Ten: ");
                    String hoTen = scanner.nextLine();
                    System.out.print("Nhap So Đien Thoai: ");
                    String sdt = scanner.nextLine();
                    System.out.print("Nhap Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nhap Mat Khau: ");
                    String matKhau = scanner.nextLine();
                    System.out.print("Nhap Dia Chi: ");
                    String diaChi = scanner.nextLine();

                    if (nguoiDungService.kiemTraEmailTonTai(email)) {
                        System.out.println("Email da duoc dang ky. Vui long su dung email khac!!!");
                    } else {
                        nguoiDungService.dangKyTaiKhoan(hoTen, sdt, email, matKhau, diaChi);
                        System.out.println("Dang ky thanh cong!");
                    }
                    break;

                case 0:
                    // Thoát
                    System.out.println("\n");
                    System.out.println("Chuong trinh ket thuc!!!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Chon khong hop le. Vui long chon lai!!!");
            }
        }
    }

    private static void showUser() {
        Scanner sc = new Scanner(System.in);
        Connection conn = Connect.getConnect();
        SanPhamService sanPhamService = new SanPhamService(conn);
        DatHangService datHangService = new DatHangService(conn);
        System.out.println("Chao mung nguoi dung den voi shop quan ao nho!!!");

        while (true) {
            System.out.println("\n");
            System.out.println("--------------------USER--------------------");
            System.out.println("1. Xem danh sach san pham");
            System.out.println("2. Them san pham vao gio hang");
            System.out.println("3. Xem gio hang");
            System.out.println("4. Sua san pham trong gio hang");
            System.out.println("5. Xoa san pham trong gio hang");
            System.out.println("6. Dang xuat");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n");
                    sanPhamService.hienThiDanhSachSanPham();
                    break;

                case 2:
                    System.out.println("\n");
                    System.out.print("Nhap ID san pham muon them vao gio hang: ");
                    int idSanPham = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nhap so luong san pham muon them vao gio hang: ");
                    int soLuong = sc.nextInt();
                    sc.nextLine();
                    if (sanPhamService.kiemTraSanPhamTonTai(idSanPham)) {
                        int idNguoiDung = Connect.getLoggedInUserId();
                        datHangService.themDatHang(soLuong, idNguoiDung, idSanPham);
                    }
                    break;

                case 3:
                    System.out.println("\n--------------------GIO HANG--------------------\n");
                    int idNguoiDung = Connect.getLoggedInUserId();
                    datHangService.hienThiGioHang(idNguoiDung);
                    break;
                case 4:
                    // Sua san pham trong gio hang
                    System.out.println("\n");
                    System.out.print("Nhap ID san pham muon sua trong gio hang: ");
                    int idSanPhamSua = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nhap so luong san pham moi: ");
                    int soLuongMoi = sc.nextInt();
                    sc.nextLine();

                    int idNguoiDungSua = Connect.getLoggedInUserId();
                    datHangService.suaSanPhamTrongGioHang(idNguoiDungSua, idSanPhamSua, soLuongMoi);
                    break;

                case 5:
                    // Xoa san pham trong gio hang
                    System.out.println("\n");
                    System.out.print("Nhap ID san pham muon xoa trong gio hang: ");
                    int idSanPhamXoa = sc.nextInt();
                    sc.nextLine();

                    int idNguoiDungXoa = Connect.getLoggedInUserId();
                    datHangService.xoaSanPhamTrongGioHang(idNguoiDungXoa, idSanPhamXoa);
                    break;
                case 6:
                    System.out.println("\n");
                    System.out.println("Dang xuat tai khoan!");
                    dangXuat();
                case 0:
                    System.out.println("\n");
                    Connect.disconnect();
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private static void showMenuAdmin() {
        Scanner sc = new Scanner(System.in);
        Connection conn = Connect.getConnect();
        NguoiDungService nguoiDungService = new NguoiDungService(conn);
        SanPhamService sanPhamService = new SanPhamService(conn);
        System.out.println("Chao mung admin vao he thong ban hang!!!");
        while (true) {
            System.out.println("\n");
            System.out.println("--------------------ADMIN--------------------");
            System.out.println("1. Them nguoi dung");
            System.out.println("2. Sua nguoi dung");
            System.out.println("3. Xoa nguoi dung");
            System.out.println("4. Hien thi danh sach nguoi dung");
            System.out.println("5. Hien thi danh sach san pham");
            System.out.println("6. Them san pham");
            System.out.println("7. Sua san pham");
            System.out.println("8. Xoa san pham");
            System.out.println("9. Dang xuat");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n");
                    System.out.print("Nhap ho va ten: ");
                    String hoTen = sc.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String sdt = sc.nextLine();
                    System.out.print("Nhap email: ");
                    String email = sc.nextLine();
                    System.out.print("Nhap mat khau: ");
                    String matKhau = sc.nextLine();
                    System.out.print("Nhap đia chi: ");
                    String diaChi = sc.nextLine();

                    nguoiDungService.themNguoiDung(hoTen, sdt, email, matKhau, diaChi);
                    break;

                case 2:
                    System.out.println("\n");
                    System.out.print("Nhap ID nguoi dung can sua: ");
                    int idSua = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nhap ho ten moi: ");
                    String hoTenMoi = sc.nextLine();

                    System.out.print("Nhap so dien thoai moi: ");
                    String sdtMoi = sc.nextLine();

                    System.out.print("Nhap email moi: ");
                    String emailMoi = sc.nextLine();

                    System.out.print("Nhap mat khau moi: ");
                    String matKhauMoi = sc.nextLine();

                    System.out.print("Nhap dia chi moi: ");
                    String diaChiMoi = sc.nextLine();

                    nguoiDungService.suaNguoiDung(idSua, hoTenMoi, sdtMoi, emailMoi, matKhauMoi, diaChiMoi);
                    break;

                case 3:
                    System.out.println("\n");
                    System.out.print("Nhap ID nguoi dung can xoa: ");
                    int idXoa = sc.nextInt();
                    nguoiDungService.xoaNguoiDung(idXoa);
                    break;

                case 4:
                    System.out.println("\nDanh sach tat ca nguoi dung:\n");
                    nguoiDungService.hienThiThongTinTatCaNguoiDung();
                    break;
                case 5:
                    System.out.println("\n");
                    sanPhamService.hienThiDanhSachSanPham();
                    break;

                case 6:
                    System.out.println("\n");
                    System.out.print("Nhap ten san pham: ");
                    String tenSP = sc.nextLine();
                    System.out.print("Nhap kich thuoc: ");
                    String kichThuoc = sc.nextLine();
                    System.out.print("Nhap chat lieu: ");
                    String chatLieu = sc.nextLine();
                    System.out.print("Nhap mau sac: ");
                    String mauSac = sc.nextLine();
                    System.out.print("Nhap gia: ");
                    int gia = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhap xuat xu: ");
                    String xuatXu = sc.nextLine();

                    sanPhamService.themSanPham(tenSP, kichThuoc, chatLieu, mauSac, gia, xuatXu);
                    break;
                case 7:
                    System.out.println("\n");
                    System.out.print("Nhap ID san pham can sua: ");
                    int idSuaSP = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhap ten san pham moi: ");
                    String tenSuaSP = sc.nextLine();
                    System.out.print("Nhap kich thuoc san pham moi: ");
                    String kichThuocSP = sc.nextLine();
                    System.out.print("Nhap chat lieu san pham moi: ");
                    String chatLieuSP = sc.nextLine();
                    System.out.print("Nhap mau sac san pham moi: ");
                    String mauSacSP = sc.nextLine();
                    System.out.print("Nhap gia san pham moi: ");
                    int giaSP = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nhap xuat xu san pham moi: ");
                    String xuatXuSP = sc.nextLine();
                    sanPhamService.suaSanPham(idSuaSP, tenSuaSP, kichThuocSP, chatLieuSP, mauSacSP, giaSP, xuatXuSP);
                    break;
                case 8:
                    System.out.println("\n");
                    System.out.print("Nhap ID san pham can xoa: ");
                    int idSPXoa = sc.nextInt();
                    sanPhamService.xoaSanPham(idSPXoa);
                    break;
                case 9:
                    System.out.println("\n");
                    System.out.println("Dang xuat tai khoan!");
                    dangXuat();
                case 0:
                    System.out.println("\n");
                    Connect.disconnect();
                    System.out.println("Thoat chuong trinh.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}
