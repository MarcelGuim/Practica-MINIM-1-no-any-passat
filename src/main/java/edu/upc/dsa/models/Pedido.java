package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    String id;
    List<Comanda> pedidos = new ArrayList<>();
    boolean realizado;
    Usuario usuario;
    static int lastId;

    public Pedido() {
        this.setId(RandomUtils.getId());
    }
    public Pedido(List<Comanda> list, boolean realizado, Usuario usuario) {
        this(null, list, realizado, usuario);
    }

    public Pedido(String id, List<Comanda> list, boolean realizado, Usuario usuario) {
        this();
        if (id != null) this.setId(id);
        this.setPedidos(list);
        this.setRealizado(realizado);
        this.setUsuario(usuario);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public List<Comanda> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Comanda> pedidos) {
        this.pedidos = pedidos;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void addComanda(Comanda comanda)
    {
        this.pedidos.add(comanda);
    }

    @Override
    public String toString() {
        return "Pedido [id="+id+", Usuario=" + usuario + ", lista de pedidos=" + pedidos +" realizado= "+realizado+"]";
    }

}
