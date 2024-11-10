package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    String id;
    String nombre;
    String pswd;
    List<Pedido> pedidos = new ArrayList<>();
    static int lastId;

    public Usuario() {
        this.setId(RandomUtils.getId());
    }
    public Usuario(String nombre, String pswd) {
        this(null,nombre, pswd);
    }

    public Usuario(String id, String nombre, String pswd) {
        this();
        if (id != null) this.setId(id);
        this.setNombre(nombre);
        this.setPswd(pswd);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void addPedido(Pedido pedido){
        this.pedidos.add(pedido);
    }

    @Override
    public String toString() {
        return "Usuario [id="+id+", nombre=" + nombre + ", contraseña=" + pswd +" pedidos="+pedidos+"]";
    }

}
