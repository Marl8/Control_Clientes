package datos;


import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_cliente_udemyjava?"
            + "useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "4842";
    
    
    public static void ClassForName(){
        
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    
    public static DataSource getDataSource(){
        
        ClassForName();
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        ds.setInitialSize(50); // Numero de conexiones
        
        return ds;
    }
    
    public static Connection getConexion() throws SQLException, ClassNotFoundException{
        
        return getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs){
    
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    } 
    
     public static void close(PreparedStatement ps){
    
        try {
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    } 
     
     public static void close(Connection con){
    
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }  
}
