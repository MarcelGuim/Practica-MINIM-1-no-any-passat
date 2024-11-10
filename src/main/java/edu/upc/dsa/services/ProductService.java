package edu.upc.dsa.services;

import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.ProductManager;
import edu.upc.dsa.models.Comanda;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Productos;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/products", description = "Endpoint to Track Service")
@Path("/products")
public class ProductService {


    private ProductManager Pm;

    public ProductService() {
        this.Pm = ProductManagerImpl.getInstance();
        if (Pm.sizeUsers()==0) {
            Productos producto1 = new Productos("Bocadillo", 5,0);
            Productos producto2 = new Productos("Cocacola", 2,0);
            Usuario usurio1=new Usuario("Marcel","1234");
            this.Pm.addUsuario(usurio1);
            this.Pm.addProductos(producto1);
            this.Pm.addProductos(producto2);
            Pedido pedido1 = new Pedido();
            this.Pm.addPedido(pedido1);
            this.Pm.HacerComanda(pedido1.getId(), "Bocadillo",1,"Marcel");
            this.Pm.HacerComanda(pedido1.getId(), "Cocacola",2,"Marcel");
        }
    }

    @POST
    @ApiOperation(value = "create a new Producto", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Productos.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/producto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProducto(Productos producto) {
        if(producto.getName() == null || producto.getPrecio() == 0)
            return Response.status(500).entity(producto).build();
        this.Pm.addProductos(producto);
        return Response.status(201).entity(producto).build();
    }

    @POST
    @ApiOperation(value = "create a new User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuario.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/usuario")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUsuario(Usuario user) {
        if(user.getNombre() == null || user.getPswd() == null)
            return Response.status(500).entity(user).build();
        this.Pm.addUsuario(user);
        return Response.status(201).entity(user).build();
    }
    @POST
    @ApiOperation(value = "create a new Order (atención, solo creamos el pedido, no lo hacemos", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Pedido.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/pedido")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPedido(Usuario user) {
        Pedido pedido = new Pedido();
        if(user.getNombre() == null)
            return Response.status(500).entity(pedido).build();
        pedido.setUsuario(this.Pm.getUsuario(user.getNombre()));
        this.Pm.addPedido(pedido);
        return Response.status(201).entity(pedido).build();
    }
    @POST
    @ApiOperation(value = "create a new comanda (atención, ahora llenamos el pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Pedido.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/comanda")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newComanda(Comanda comanda) {
        Pedido pedido = new Pedido();
        if(comanda.getIdPedido() == null || comanda.getNombreProducto() == null || comanda.getCantidad() == 0 || comanda.getNombreUsuario() == null)
            return Response.status(500).entity(pedido).build();
        this.Pm.HacerComanda(comanda.getIdPedido(), comanda.getNombreProducto(), comanda.getCantidad(), comanda.getNombreUsuario());
        Pedido pedido1 = this.Pm.getPedido(comanda.getIdPedido());
        return Response.status(201).entity(pedido1).build();
    }
    @GET
    @ApiOperation(value = "Productos Ordenados por Precio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Productos.class, responseContainer="List"),
    })
    @Path("/productos/precio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosPrecio() {

        List<Productos> productos = this.Pm.DameProductosOrdenadosPrecio();

        GenericEntity<List<Productos>> entity = new GenericEntity<List<Productos>>(productos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "Productos Ordenados por Ventas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Productos.class, responseContainer="List"),
    })
    @Path("/productos/ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosVendidos() {

        List<Productos> productos = this.Pm.DameProductosOrdenadosVentas();

        GenericEntity<List<Productos>> entity = new GenericEntity<List<Productos>>(productos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "Quiero mi pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class, responseContainer="List"),
    })
    @Path("/DamePedido")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidoAhora() {
        Pedido pedido = this.Pm.ServirPedido();
        return Response.status(201).entity(pedido).build()  ;

    }
}

