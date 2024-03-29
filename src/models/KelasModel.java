// PROGRAM kelas model
// { Sebagai kelas utama dari model dan akan menjadi parent dari model lain }

// PACKAGES
package models;

// PUSTAKA
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

// KELAS
public class KelasModel {

    // ATRIBUTE
    String sql;
    Connection con = null;
    ResultSet rs = null;
    Statement stmt = null;
    Properties prop = null;
    String[][] data = null;
    public String locPathImg;
    public String cookie;
    public int width;
    public int height;
    private String path;

    // METHOD untuk koneksi dari java ke mysql
    public void sqlCon () {

        // Variable
        config();
        locPathImg = System.getProperty("os.name").equals("Linux") ? path + "/img/" : path + "\\img\\";
        cookie = System.getProperty("os.name").equals("Linux") ? path + "/src/config/cookie.properties" : path + "\\src\\config\\cookie.properties";
        width = Integer.parseInt(prop.getProperty("width"));
        height = Integer.parseInt(prop.getProperty("height"));
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String pass = prop.getProperty("pass");
        String driver = prop.getProperty("driver");
        
        // Connect to mysql
        try {
            Class.forName(driver);
            con = DriverManager.getConnection( url, user, pass);
            stmt = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Data tidak dapat diakses!");
            System.out.println("Error = " + ex);

        }

    }

    // METHOD untuk mengakses file config
    public void config () {

        // Access data from config.properties
        try {
            prop = new Properties();
            File file = new File("");
            path = file.getCanonicalPath();
            String pathConfig = System.getProperty("os.name").equals("Linux") ? path + "/src/config/config.properties" : path + "\\src\\config\\config.properties";
            prop.load(new FileInputStream(pathConfig));

        } catch (Exception ex) {
            System.out.println("Configurasi gagal!");
            System.out.println("Error = " + ex);

        }

    }

    // METHOD untuk merubah data
    public void sqlUpdate (String table, String data, String lokasi) {

        // Execute query sql
        try {
            sql = "UPDATE " + table + " SET " + data + " WHERE " + lokasi;
            stmt.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Error =" + ex);

        }

    }

    // METHOD untuk mendapatkan data
    public String[][] sqlGetAll (String table, String[] index) {

        // Variable
        String[][] data = new String[getCount(table)][index.length];
        int i = 0;

        // Execure query sql
        try{
            sql = "SELECT * FROM " + table;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                for (int j = 0; j < index.length; j++) {
                    data[i][j] = rs.getString(index[j]);
                }
                i++;
            }
            return data;

        } catch (Exception ex) {
            System.out.println("Gagal mendapatkan semua data dari table '" + table + "'");
            System.out.println("Error =" + ex);
            return data;

        }

    }

    // METHOD untuk mendapatkan banyaknya data dalam
    public int getCount (String table) {

        // Execute query sql
        try {
            String strSql = "SELECT count(*) FROM " + table;
            ResultSet fakeRs = stmt.executeQuery(strSql);
            fakeRs.next();
            return fakeRs.getInt(1);

        } catch (Exception ex) {
            System.out.println("Gagal mendapatkan count dari table '" + table + "'");
            System.out.println("Error =" + ex);
            return 0;

        }
    }

    // METHOD untuk menambahkan data
    public void sqlInsert (String table, String data) {

        // Execute query sql
        try {
            sql = "INSERT INTO " + table + " VALUES (" + data +")";
            stmt.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Error =" + ex);

        }

    }

    // METHOD untuk menghapus semua data
    public void sqlDeleteAll (String table) {

        // Execute query sql
        try {
            sql = "DELETE FROM " + table;
            stmt.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Error =" + ex);

        }

    }

}
