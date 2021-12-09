import java.sql.*;

public class Query {

    private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DB_URL = "jdbc:mysql://localhost/jdbcdemo?useSSL=false";
    private String USER = "";
    private String PASS = "";
    private Connection conn = null;
    private Statement stmt = null;

    Query(String user, String pass){
        this.USER = user;
        this.PASS = pass;

        try{
            System.out.println("Trying to connect to the Database.");
            Class.forName(JDBC_DRIVER);
            this.conn = DriverManager.getConnection(this.DB_URL, this.USER, this.PASS);
            this.stmt = conn.createStatement();
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getSQLQueryResult(String query){
        try{
            System.out.println("Executing the query provided.");
            ResultSet rs = this.stmt.executeQuery(query);
            return rs;
        }

        catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void executeUpdateQuery(String query){
        try{
            System.out.println("Executing the query provided.");
            this.stmt.executeUpdate(query);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            this.stmt.close();
            this.conn.close();
        }

        catch(Exception e){
            e.printStackTrace();
        }

        finally{
            try{
                if(this.stmt != null)    this.stmt.close();
                if(this.conn != null)   this.conn.close();
            }

            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

}