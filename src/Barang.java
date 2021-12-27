import java.util.Scanner;
import java.util.InputMismatchException;

import com.mysql.cj.protocol.Resultset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class Barang implements Transaksi {
  String barang;
  int harga;
  String noFakt;
  int jumlah;
  int subTot;
  double disc;
  int total;

  static Connection conn;
  String url = "jdbc:mysql://localhost:3306/pert14";

  Scanner Input = new Scanner(System.in);

  public void displaydatabase() throws SQLException {
    String text1 = "\nPesanan";
    System.out.println(text1.toUpperCase());

    String sql = "SELECT * FROM pesanan";
    conn = DriverManager.getConnection(url, "root", "");
    Statement statement = conn.createStatement();
    ResultSet result = statement.executeQuery(sql);

    while (result.next()) {
      System.out.print("\nNomor Faktur\t  : ");
      System.out.print(result.getInt("no_faktur"));
      System.out.print("\nNama Barang\t  : ");
      System.out.print(result.getString("nama_barang"));
      System.out.print("\nHarga\t\t  : ");
      System.out.print(result.getString("harga_barang"));
      System.out.print("\nJumlah Barang : ");
      System.out.print(result.getInt("jumlah"));
      System.out.print("\nDiskon\t  : ");
      System.out.print(result.getInt("diskon"));
      System.out.print("\n");
      System.out.print("\nTotal\t  : ");
      System.out.print(result.getInt("total"));
      System.out.print("\n");
    }
  }

  public void insertdata() throws SQLException {
    	String text2 = "\nTambah Data Pesanan";
		System.out.println(text2.toUpperCase());
		
    	try {
      //faktur
    System.out.print("\nNomor Faktur : ");
    noFakt = Input.nextLine();
    noFakt = noFakt.toLowerCase();
    noFakt = noFakt.trim();
    } catch (InputMismatchException ex) {
    System.out.println("Salah");
    } finally {
    System.out.println(" ");
     }
    
     //Nama Barang
    System.out.print("\nNama Barang  : ");

    barang = Input.nextLine();
    barang = barang.toUpperCase();
    barang = barang.trim();

    //harga
     System.out.print("\nHarga Barang : ");
    harga = Input.nextInt();

    //jumlah
    System.out.print("\nJumlah Barang : ");
    jumlah = Input.nextInt();

    subTot = harga * jumlah;

    if (subTot >= 10000000) {
      disc = 0.1;
      System.out.println("\nDiskon = " + (subTot * disc));
    } else if (subTot >= 1000000 && subTot < 10000000) {
      disc = 0.05;
      System.out.println("\nDiskon = " + (subTot * disc));
    } else if (subTot >= 500000 && subTot < 1000000) {
      disc = 0.03;
      System.out.println("\nDiskon = " + (subTot * disc));
    } else {
      disc = 0.02;
      System.out.println("\nDiskon = " + (subTot * disc));}

      String sql = "INSERT INTO pesanan (no_faktur, nama_barang, harga_barang, jumlah, diskon, total) VALUES ('"
        + noFakt + "','" + barang + "','" + harga + "','" + jumlah + "','" + disc + "','" + subTot + "')";
    conn = DriverManager.getConnection(url, "root", "");
    Statement statement = conn.createStatement();
    statement.execute(sql);
    System.out.println("Berhasil input data!!");
    }
  
  public void ubahdata() throws SQLException {
    String text3 = "\nUbah Pesanan";
    System.out.println(text3.toUpperCase());

    try {
      displaydatabase();
      System.out.print("\nMasukkan Nomor Faktur dari Pesanan yang akan di ubah : ");
      Integer noFakt = Integer.parseInt(Input.nextLine());

      String sql = "SELECT * FROM pesanan WHERE no_faktur = " + noFakt;
      conn = DriverManager.getConnection(url, "root", "");
      Statement statement = conn.createStatement();
      ResultSet result = statement.executeQuery(sql);

      if (result.next()) {

        System.out.print("barang baru [" + result.getString("nama_barang") + "]\t: ");
        String barang = Input.nextLine();

        sql = "UPDATE pesanan SET nama_barang='" + barang + "' WHERE no_faktur='" + noFakt + "'";
        // System.out.println(sql);

        if (statement.executeUpdate(sql) > 0) {
          System.out.println("Berhasil memperbaharui data pesanan (Nomor " + noFakt + ")");
        }
      }
      statement.close();
    } catch (SQLException e) {
      System.err.println("Terjadi kesalahan dalam mengedit data");
      System.err.println(e.getMessage());
    }
  }
  
  public void delete() {
    String text4 = "\nHapus Data Pesanan";
    System.out.println(text4.toUpperCase());

    try {
      displaydatabase();
      System.out.print("\nMasukan nomor faktur yang akan Anda Hapus : ");
      Integer noFakt = Integer.parseInt(Input.nextLine());

      String sql = "DELETE FROM pesanan WHERE no_faktur = " + noFakt;
      conn = DriverManager.getConnection(url, "root", "");
      Statement statement = conn.createStatement();
      // ResultSet result = statement.executeQuery(sql);

      if (statement.executeUpdate(sql) > 0) {
        System.out.println("Berhasil menghapus data pesanan (Nomor " + noFakt + ")");
      }
    } catch (SQLException e) {
      System.out.println("Terjadi kesalahan dalam menghapus data");
    } catch (Exception e) {
      System.out.println("masukan data yang benar");
    }
  }

  public void searchdata() throws SQLException {
    String text5 = "\nCari Data Pesanan";
    System.out.println(text5.toUpperCase());

    System.out.print("Masukkan Pesanan yang ingin dilihat : ");
    String keyword = Input.nextLine();

    String sql = "SELECT * FROM pesanan WHERE nama_barang LIKE '%" + keyword + "%'";
    conn = DriverManager.getConnection(url, "root", "");
    Statement statement = conn.createStatement();
    ResultSet result = statement.executeQuery(sql);

    while (result.next()) {
      System.out.print("\nNomor Faktur\t  : ");
      System.out.print(result.getInt("no_faktur"));
      System.out.print("\nNama Barang\t  : ");
      System.out.print(result.getString("nama_barang"));
      System.out.print("\nHarga\t\t  : ");
      System.out.print(result.getString("harga_barang"));
      System.out.print("\nJumlah Barang : ");
      System.out.print(result.getInt("jumlah"));
      System.out.print("\nDiskon\t  : ");
      System.out.print(result.getInt("diskon"));
      System.out.print("\n");
      System.out.print("\nTotal\t  : ");
      System.out.print(result.getInt("total"));
      System.out.print("\n");
    }
  }
}
