import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String url="jdbc:mysql://localhost:3306/skillbridge";
    private static final String password="Omkuthe@08";
    private static final String user="root";

    public static Connection getconnection() throws  SQLException{
    try{
        return DriverManager.getConnection(url,user,password);
    }catch(SQLException e){
        System.out.println("Error"+e.getMessage());
        return null;
    }
    }

}
