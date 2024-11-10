package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Comanda {
    String id;
    Productos producto;
    int cantidad;
    String IdPedido;
    String NombreProducto;
    String NombreUsuario;
    static int lastId;

    public Comanda() {
        this.setId(RandomUtils.getId());
    }
    public Comanda(Productos producto, int cantidad) {
        this(null, producto, cantidad);
    }

    public Comanda(String id, Productos producto, int cantidad) {
        this();
        if (id != null) this.setId(id);
        this.setCantidad(cantidad);
        this.setProducto(producto);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(String idPedido) {
        IdPedido = idPedido;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "Comanda [id="+id+", producto=" + producto + ", cantidad=" + cantidad +"]";
    }

}
