
package datos;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Cliente;


public class ClienteDao {
    
    private static final String SQL_SELECT = "SELECT * FROM clientes";
    private static final String SQL_SELECT_ID = "SELECT * FROM clientes WHERE id_cliente = ?";
    private static final String SQL_INSERT = "INSERT INTO clientes (NOMBRE, APELLIDO, EMAIL, TELEFONO, SALDO) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE * FROM clientes WHERE id_cliente = ?";
    
    
    public List<Cliente> listar (){

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> listaclientes = new ArrayList<>();

        try {
            con = Conexion.getConexion();
             ps = con.prepareStatement(SQL_SELECT);
        rs = ps.executeQuery();

            while(rs.next()){

                int idCliente = rs.getInt("id_clientes");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

            cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
            listaclientes.add(cliente);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
       finally{

        Conexion.close(rs);
        Conexion.close(ps);
        Conexion.close(con);
        }
        return listaclientes;
    }

    public Cliente findcliente(Cliente cliente){

       Connection con = null;
       PreparedStatement ps = null;
       ResultSet rs = null;

       try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_SELECT_ID);
            ps.setInt(1, cliente.getIdCliente());
            rs = ps.executeQuery();

            rs.absolute(1); // se posiciona en la primer fila

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");

            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
       finally{

        Conexion.close(ps);
        Conexion.close(con);
        }
       return cliente;
    }

    public int guardarcliente(Cliente cliente){
    
       Connection con = null;
       PreparedStatement ps = null;
       int registrosModificados = 0;

       try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setDouble(5, cliente.getSaldo());

            registrosModificados = ps.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
       finally{

        Conexion.close(ps);
        Conexion.close(con);
        }
       return registrosModificados;
    }
    
    public int modificarcliente(Cliente cliente){
    
       Connection con = null;
       PreparedStatement ps = null;
       int registrosModificados = 0;

       try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_UPDATE);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setDouble(5, cliente.getSaldo());
            ps.setInt(6, cliente.getIdCliente());

            registrosModificados = ps.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
       finally{

        Conexion.close(ps);
        Conexion.close(con);
        }
       return registrosModificados;
    }
    
    public int eliminarcliente(Cliente cliente){
    
       Connection con = null;
       PreparedStatement ps = null;
       int registrosModificados = 0;

       try {
            con = Conexion.getConexion();
            ps = con.prepareStatement(SQL_DELETE);
             ps.setInt(1, cliente.getIdCliente());

            registrosModificados = ps.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
       finally{

        Conexion.close(ps);
        Conexion.close(con);
        }
       return registrosModificados;
    }
}
