import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Program {
    // static Scanner scanner;
    static Connection conn;

    public static void main(String[] args) {
        Scanner terimaInput = new Scanner(System.in);
        String pilihanUser;
        boolean isLanjutkan = true;

        String url = "jdbc:mysql://localhost:3306/pert14";
        try {

            String salamSapa = "Selamat Datang, Selamat menggunakan Program Ini";
            String sapa = salamSapa.replace("Selamat Datang", "Silahkan gunakan aplikasi ini dengan benar!");

            System.out.println(sapa.toLowerCase());

            Date datenow = new Date();
            SimpleDateFormat tgl = new SimpleDateFormat("E, dd/MM/yyyy");
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss zzz");

            System.out.println("Tanggal \t: " + tgl.format(datenow));
            System.out.println("Waktu \t\t: " + time.format(datenow));
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("Class Driver ditemukan!!!");
            Barang barang = new Barang();

            while (isLanjutkan) {
                System.out.println("----------------------");
                System.out.println("Database Pesanan");
                System.out.println("----------------------");
                System.out.println("1. Tampil Pesanan");
                System.out.println("2. Tambah Pesanan");
                System.out.println("3. Ubah Pesanan");
                System.out.println("4. Hapus Pesanan");
                System.out.println("5. Cari Data Pesanan");

                System.out.print("\nInputkan Pilihan anda (1/2/3/4/5): ");
                pilihanUser = terimaInput.next();

                switch (pilihanUser) {
                    case "1":
                        barang.displaydatabase();
                        break;
                    case "2":
                        barang.insertdata();
                        break;
                    case "3":
                        barang.ubahdata();
                        break;
                    case "4":
                        barang.delete();
                        break;
                    case "5":
                        barang.searchdata();
                        break;
                    default:
                        System.err.println("\nInput anda tidak ditemukan\nSilakan pilih [1-5]");
                }

                System.out.print("\nApakah Anda ingin melanjutkan [y/n]? ");
                pilihanUser = terimaInput.next();
                isLanjutkan = pilihanUser.equalsIgnoreCase("y");

            }
            System.out.println("Program selesai...");

        } catch (ClassNotFoundException ex) {
            System.err.println("Driver Error");
            System.exit(0);
        } catch (SQLException e) {
            System.err.println("Tidak berhasil koneksi");
        }
    }
}