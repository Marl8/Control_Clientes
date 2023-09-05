<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <a href="index.jsp" class="btn btn-light btn-block px-5">
                    <i class="fas fa-arrow-left me-2"></i>Volver al Inicio
                </a>
            </div>
             <div class="col-md-3">
                <button type="submit" class="btn btn-success btn-block px-5">
                    <i class="fas fa-check me-2"></i>Guardar Cliente
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/controlador?accion=eliminar&idCliente=${cliente.idCliente}" 
                   class="btn btn-danger btn-block px-5">
                    <i class="fas fa-trash me-2"></i>Eliminar Cliente
                </a>
            </div>
        </div>
    </div>
</section>
