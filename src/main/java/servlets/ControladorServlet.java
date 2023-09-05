
package servlets;

import datos.ClienteDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Cliente;


@WebServlet(name = "ControladorServlet", urlPatterns = {"/controlador"})
public class ControladorServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         String accion = request.getParameter("accion");
        
        if(accion != null){
        
            switch (accion) {
                case "editar":
                    this.obtenerCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        }else{
            this.accionDefault(request, response);
        }      
    }
    
    
    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        List<Cliente> listaClientes;
        listaClientes = new ClienteDao().listar();
       
        System.out.println("Clientes = " + listaClientes);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", listaClientes);
        sesion.setAttribute("totalClientes", listaClientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldo(listaClientes));
        
        //request.setAttribute("clientes", listaClientes);
        //request.setAttribute("totalClientes", listaClientes.size());
        //request.setAttribute("saldoTotal", this.calcularSaldo(listaClientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }
    
    
    private double calcularSaldo (List<Cliente> clientes){
        
        int saldoTotal = 0;
        
        for(Cliente c : clientes){
        
            saldoTotal += c.getSaldo();
        }
        return  saldoTotal;
    }

    
    private void obtenerCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    
        // Obtenemos el id el Cliente
        
            int id = Integer.parseInt(request.getParameter("idCliente"));
            
        // Obtenemos el cliente
        
            Cliente cliente = new ClienteDao().findcliente(new Cliente(id));
            request.setAttribute("cliente", cliente);
            String jspEditar = "/WEB-INF/clientes/editarCliente.jsp";
            request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    
        int id = Integer.parseInt(request.getParameter("idCliente"));
        int registrosEliminados = new ClienteDao().eliminarcliente(new Cliente(id));
        System.out.println("Registros eliminados: " + registrosEliminados);
        
        this.accionDefault(request, response);
    }
    
    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if(accion != null){
        
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "editar":
                    this.editarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        }else{
            this.accionDefault(request, response);
        }
    }
    
    
    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    
        // Recuperar los datos del Formulario
        
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");

            double saldo = 0;
            String saldoString = request.getParameter("saldo");

            //Si el String de saldo NO es null y NO es una cadena vacia entonces convierto a double
            if(saldoString != null && !"".equals(saldoString)){

                saldo = Double.parseDouble(saldoString);
            }
        
        // Se crea el objeto Cliente
        
            Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
        
        // Guardamos el nuevo registro en la base de datos
            
            int registrosModificados = new ClienteDao().guardarcliente(cliente);
            System.out.println("Registros modiicados: " + registrosModificados);
            
        // Redirigimos nuevamente al index 
            this.accionDefault(request, response);
    }
    
    
    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    
        // Recuperar los datos del Formulario
        
            int id = Integer.parseInt(request.getParameter("idCliente"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");

            double saldo = 0;
            String saldoString = request.getParameter("saldo");

            //Si el String de saldo NO es null y NO es una cadena vacia entonces convierto a double
            if(saldoString != null && !"".equals(saldoString)){

                saldo = Double.parseDouble(saldoString);
            }
        
        // Se crea el objeto Cliente
        
            Cliente cliente = new Cliente(id, nombre, apellido, email, telefono, saldo);
        
        // Guardamos el nuevo registro en la base de datos
            
            int registrosModificados = new ClienteDao().modificarcliente(cliente);
            System.out.println("Registros modificados: " + registrosModificados);
            
        // Redirigimos nuevamente al index 
            this.accionDefault(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
