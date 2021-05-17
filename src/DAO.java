import java.sql.*;

public class DAO {
    Connection conn;
    Statement st;
    public void openConnection()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//acaddbprod-1.uta.edu:1523/pcse1p.data.uta.edu","axv2857","Ajamujaoracle0007");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet executeQuery(String query) {
        ResultSet rset = null;
        try {
            st = conn.createStatement();
            rset = st.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rset;
    }

    public void closeConnection()
    {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
