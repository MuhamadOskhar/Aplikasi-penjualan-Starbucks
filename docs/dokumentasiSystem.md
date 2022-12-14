# DOKUMENTASI SISTEM

## Pendahuluan

Pada program aplikasi ini kami mencoba untuk menerapkan metode pemrograman MVC yang membagi keseluruhan program menjadi 3 komponen yaitu model, view, dan controller guna mempermudah pemeliharaan serta pengembangan aplikasi.

## Konsep Pembuatan

Pembahasan akan dimulai dari pengorganisaian file. Di dalam folder src ada folder config untuk mengkonfigurasi system dengan sangat fleksibel tanpa harus merubah keseluruhan program, ini akan sangat mempermudah pengembangan jangka panjang.

> **Note**: Bahkan saat ingin mengganti jenis database maupun mengganti tema warna itu sangat mungkin untuk dilakukan
> Selain itu lokasi folder pada project tidak selalu berada di posisi yang serupa, bahkan penanda folder linux dengan folder windows sangat jauh berbeda, mengingat saya dengan kawan kawan pengembang tidak menggunakan Oprating System yanng sama

Lalu ada folder models, views, dan controllers yang masing masing menampung bagian bagian tertentu tanpa bercampur satu sama lain. Sehingga memudahkan perbaikan bug dan hanya perlu membuka folder yang diperlukan saat error terjadi. Yang lebih penting lagi setiap folder memiliki kelas parent masing masing contohnya pada Folder models yang memiliki kelas parent berupa "KelasModel.java", yang dimana program ini berisi semua konfigurasi terhadap database serta method method yang diperlukan untuk mengatur isi data pada database. <br/>

`sqlCon`: Menghubungkan program ke database sql sekaligus menetapkan value pada atribut
```java

    public void sqlCon () {

        config();
        locPathImg = prop.getProperty("pathImg");
        cookie = prop.getProperty("cookie");
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
```

`config`: Sebagai konfigurasi awal dengan database sql
```java

    public void config () {

        try {
            prop = new Properties();
            File file = new File("src/config/java.properties");
            String path = file.getCanonicalPath();
            prop.load(new FileInputStream(path));

        } catch (Exception ex) {
            System.out.println("Configurasi gagal!");
            System.out.println("Error = " + ex);

        }

    }
```

`sqlUpdate`: Update atau mengubah data column pada row tertentu
```java
    public void sqlUpdate (String table, String data, String lokasi) {

        try {
            sql = "UPDATE " + table + " SET " + data + " WHERE " + lokasi;
            stmt.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Error =" + ex);

        }

    }
```

`sqlGetAll`: Mengambil semua tabel dalam database sql menjadi array dua dimensi
```java
    public String[][] sqlGetAll (String table, String[] index) {

        String[][] data = new String[getCount(table)][index.length];
        int i = 0;

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
```

`getCount`: Mendapatkan panjang row pada database
```java
    public int getCount (String table) {

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
```

`sqlInsert`: Menambah data baru pada table dalam database
```java
    public void sqlInsert (String table, String data) {

        try {
            sql = "INSERT INTO " + table + " VALUES (" + data +")";
            stmt.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Error =" + ex);

        }

    }
```

`sqlDeleteAll`: Menghapus semua value pada table yang dituju
```java
    public void sqlDeleteAll (String table) {

        try {
            sql = "DELETE FROM " + table;
            stmt.executeUpdate(sql);

        } catch (Exception ex) {
            System.out.println("Error =" + ex);

        }

    }

```

Dengan begini saya hanya perlu memakai method pada file Controller saat saya ingin mengatur database melalui kontroler. 

### Jadi konsepnya seperti ini:

model -> controller -> view -> user
* data dalam database disiapkan oleh model
* dikelolar oleh controller
* dan ditampilkan pada view<br/>

### Lalu akan menjadi seperti ini saat user melakukan aksi:

model <- controller <- view <- user
* user melakukan aksi
* view memberi request pada controller untuk mengelola data
* controller mengelola data yang disediakan oleh model
