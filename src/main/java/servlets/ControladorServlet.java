
package servlets;

import datos.ClienteDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Cliente;


@WebServlet(name = "ControladorServlet", urlPatterns = {"/controlador"})
public class ControladorServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cliente> listaClientes;
        listaClientes = new ClienteDao().listar();
       
        System.out.println("Clientes = " + listaClientes);
        request.setAttribute("clientes", listaClientes);
        request.setAttribute("totalClientes", listaClientes.size());
        request.setAttribute("saldoTotal", this.calcularSaldo(listaClientes));
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
        
    }
    
    private double calcularSaldo (List<Cliente> clientes){
        
        int saldoTotal = 0;
        
        for(Cliente c : clientes){
        
            saldoTotal += c.getSaldo();
        }
        return  saldoTotal;
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
