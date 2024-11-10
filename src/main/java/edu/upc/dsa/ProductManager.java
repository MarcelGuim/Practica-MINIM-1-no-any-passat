package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;

import java.util.List;

public interface ProductManager {
    public Productos addProductos(String id, String nombre, double precio, int ventas);
    public Productos addProductos(String nombre, double precio, int ventas);
    public Productos addProductos(Productos producto);
    public Pedido addPedido(String id, List<Comanda> pedido, boolean realizado, Usuario usario);
    public Pedido addPedido(List<Comanda> pedidos, boolean realizado, Usuario usuario);
    public Pedido addPedido(Pedido pedido);
    public Usuario addUsuario(String id, String nombre, String pswd);
    public Usuario addUsuario(String nombre, String pswd);
    public Usuario addUsuario(Usuario usuario);
    public Productos getProducto(String id);
    public Pedido getPedido(String id);
    public Usuario getUsuario(String name);
    public Productos getProducto2(String id) throws ProductoNotFoundException;
    public Pedido getPedido2(String id) throws PedidoNotFoundException;
    public Usuario getUsuario2(String id) throws UsuarioNotFoundException;
    public List<Productos> DameProductosOrdenadosPrecio();
    public List<Productos> DameProductosOrdenadosVentas();
    public Pedido EnviarPedidoACocina(String id);
    public Pedido HacerComanda(String idPedido, String nombreProducto, int cantidad, String usuario);
    public Pedido ServirPedido();
    public List<Pedido> DamePedidosRealizadosUsuario(String nombre);
    public void clear();
    public int sizeProductos();
    public int sizePedidos();
    public int sizePedidosQueue();
    public int sizeUsers();
    public List<Pedido> DamePedidos();
}
