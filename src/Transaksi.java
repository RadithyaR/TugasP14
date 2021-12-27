import java.sql.*;

public interface Transaksi {
  void displaydatabase() throws SQLException;

  void insertdata() throws SQLException;

  void ubahdata() throws SQLException;

  void delete();

  void searchdata() throws SQLException;
}
