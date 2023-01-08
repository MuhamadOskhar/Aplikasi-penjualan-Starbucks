package models;

// KELAS
public class BeliModel extends KelasModel{
    
    // ATRIBUTE
    public String[][] database;
    
    // CONSTRUCTUR
    public BeliModel () {
        
        // Connect to sql server
        sqlCon();

        // Set database as array
        String[] isiData = {"img", "name", "rate", "harga"};
        database = sqlGetAll("minuman", isiData);
        
    }
    
}
