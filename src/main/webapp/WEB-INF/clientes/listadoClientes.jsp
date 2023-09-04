<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="es_AR"/>

<section class="id_cliente mb-5">
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="card">
                        <div class="card-header">
                            <h4>Listado de Clientes</h4>
                        </div>
                        <table class="table table-striped" >
                            <thead class="table-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Nombre</th>
                                    <th>Saldo</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cliente" items="${clientes}">
                                    <tr>
                                        <td>${cliente.idCliente}</td>
                                        <td>${cliente.nombre} ${cliente.apellido}</td>
                                        <td><fmt:formatNumber value="${cliente.saldo}" type="currency"/></td>  
                                        <td>
                                            <a href="${pageContext.request.contextPath}/controladoraccion=editar&idCliente=${cliente.idCliente}"
                                               class="btn btn-secondary">
                                                <i class="fas fa-angle-double-right pe-2"></i>Editar
                                            </a>
                                        </td>  
                                    </tr>
                                </c:forEach>
                            </tbody>    
                        </table>
                    </div>
                </div>
                
                <div class="col-md-3">
                   <div class="card text-center bg-danger text-white mb-3">
                       <div class="card-body">
                           <h3>Saldo Total</h3>
                            <h5 class="display-5">
                                <fmt:formatNumber value="${saldoTotal}" type="currency" />
                            </h5>
                       </div> 
                   </div>
                   <div class="card text-center bg-success text-white mb-3">
                       <div class="card-body">
                            <h3>Total Cliente</h3>
                            <h4 class="display-4">
                                <i class="fas fa-users"></i> ${totalClientes}
                            </h4>
                       </div> 
                    </div>         
                </div>
            </div>
        </div>
    </section>
