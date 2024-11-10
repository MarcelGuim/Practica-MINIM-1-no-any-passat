package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;
import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import javax.swing.plaf.BorderUIResource;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ProductManagerImpl implements ProductManager{
    private static ProductManager instance;
    protected Queue<Pedido> pedidosQueue;
    protected List<Usuario> users;
    protected List<Pedido> pedidos;
    protected List<Productos> productos;
    final static Logger logger = Logger.getLogger(ProductManager.class);

    private ProductManagerImpl() {
        this.pedidosQueue = new LinkedList<>();
        this.users = new LinkedList<>();
        this.pedidos = new LinkedList<>();
        this.productos = new LinkedList<>();

    }

    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerImpl();
        return instance;
    }
    public Productos addProductos(String id, String nombre, double precio, int ventas){
        return this.addProductos(new Productos(id, nombre, precio, ventas));
    };
    public Productos addProductos(String nombre, double precio, int ventas){
        return this.addProductos(null, nombre, precio, ventas);
    };
    public Productos addProductos(Productos producto){
        logger.info("new product " + producto);
        this.productos.add(producto);
        logger.info("new producte added");
        return producto;
    };
    public Pedido addPedido(String id, List<Comanda> pedido, boolean realizado, Usuario usuario){
        return this.addPedido(new Pedido(id, pedido,realizado,usuario));
    };
    public Pedido addPedido(List<Comanda> pedidos, boolean realizado, Usuario usuario){
        return this.addPedido(null, pedidos, realizado, usuario);
    };
    public Pedido addPedido(Pedido pedido){
        logger.info("new pedido " + pedido);
        this.pedidos.add(pedido);
        this.pedidosQueue.add(pedido);
        logger.info("new pedido added");
        return pedido;
    };
    public Usuario addUsuario(String id, String nombre, String pswd){
        return this.addUsuario(new Usuario(id, nombre, pswd));
    };
    public Usuario addUsuario(String nombre, String pswd){
        return this.addUsuario(null, nombre, pswd);
    };
    public Usuario addUsuario(Usuario usuario){
        logger.info("new usuario " + usuario);
        this.users.add(usuario);
        logger.info("new usuario added");
        return usuario;
    };
    public Productos getProducto(String id){
        logger.info("getProducto("+id+")");
        for (Productos p: this.productos) {
            if (p.getId().equals(id)) {
                logger.info("getProducto("+id+"): "+p);
                return p;
            }
        }
        logger.warn("not found " + id);
        return null;
    };
    public Pedido getPedido(String id){
        logger.info("getPedido("+id+")");
        for (Pedido p: this.pedidos) {
            if (p.getId().equals(id)) {
                logger.info("getPedido found("+id+"): "+p);
                return p;
            }
        }
        logger.warn("not found " + id);
        return null;
    };
    public Usuario getUsuario(String name){
        logger.info("getUsuario("+name+")");
        for (Usuario u: this.users) {
            if (u.getNombre().equals(name)) {
                logger.info("getUsuario("+name+"): "+u);
                return u;
            }
        }
        logger.warn("not found " + name);
        return null;
    };
//    public Comanda getComanda(String id){};
    public Productos getProducto2(String id) throws ProductoNotFoundException{
        Productos p = getProducto(id);
        if (p == null) throw new ProductoNotFoundException();
        return p;
    };
    public Pedido getPedido2(String id) throws PedidoNotFoundException{
        Pedido p = getPedido(id);
        if (p == null) throw new PedidoNotFoundException();
        return p;
    };
    public Usuario getUsuario2(String id) throws UsuarioNotFoundException{
        Usuario u = getUsuario(id);
        if (u == null) throw new UsuarioNotFoundException();
        return u;
    };
//    public Comanda getComanda2(String id) throws ComandaNotFoundException{};

    public List<Productos> DameProductosOrdenadosPrecio(){
        List<Productos> listaordenada = productos;
        try{
            if (listaordenada.isEmpty())
            {
                logger.warn("Atención, estas intentando ordenar una lista vacia");
                throw new EmptyListException();
            }
            else {
                listaordenada.sort(Comparator.comparingDouble(Productos::getPrecio));
                logger.info("Lista de Prodcutos ordenada por precio");
                return listaordenada;
            }
        }
        catch(EmptyListException ex)
        {
            return null;
        }
    };
    public List<Productos> DameProductosOrdenadosVentas(){
        List<Productos> listaordenada = productos;
        try{
            if (listaordenada.isEmpty())
            {
                logger.warn("Atención, estas intentando ordenar una lista vacia");
                throw new EmptyListException();
            }
            else {
                listaordenada.sort(Comparator.comparingInt(Productos::getVentas).reversed());
                logger.info("Lista de Prodcutos ordenada por ventas");
                return listaordenada;
            }
        }
        catch(EmptyListException ex)
        {
            return null;
        }
    };
    public Pedido EnviarPedidoACocina(String id){
        Pedido pedido = getPedido(id);
        pedidos.add(pedido);
        pedidosQueue.add(pedido);
        logger.info("Pedido= "+pedido+" enviado a cocina");
        return pedido;
    };
    public Productos GetProductoPorNombre(String nombre)
    {
        for(Productos p: productos){
            if(p.getName().equals(nombre))
            {
                logger.info("Producto con nombre=" +nombre+"encontrado");
                return p;
            }
        }
        logger.warn("Producto con nombre=" +nombre+" no encontrado");
        return null;
    }
    public Pedido HacerComanda(String idPedido, String nombreProducto, int cantidad, String usuario){
        Usuario u = getUsuario(usuario);
        Pedido pedido = getPedido(idPedido);
        Productos producto = GetProductoPorNombre(nombreProducto);
        Comanda comanda = new Comanda(producto, cantidad);
        producto.setVentas(producto.getVentas()+cantidad);
        pedido.addComanda(comanda);
        logger.info("Comanda= "+comanda+" añadida al pedido= "+pedido);
        pedido.setUsuario(u);
        return pedido;
    };
    public Pedido ServirPedido(){
        Pedido pedido = pedidosQueue.poll();
        pedido.setRealizado(true);
        logger.info("Pedido="+pedido+" entregado");
        return pedido;
    };
    public List<Pedido> DamePedidosRealizadosUsuario(String nombre){
        List<Pedido> pedidosDeUsuario = new LinkedList<>();
        try{
            for(Pedido p: pedidos){
                if(p.getUsuario().getNombre().equals(nombre))
                {
                    pedidosDeUsuario.add(p);
                    logger.info("El usuario= "+nombre+" ha hecho el pedido= "+p);
                }
            }
            if(pedidosDeUsuario.isEmpty()){
                logger.warn("atención, no hay pedidos de ese usuario");
                throw new EmptyListException();
            }
            else
                return pedidosDeUsuario;
        }
        catch(EmptyListException ex)
        {
            return null;
        }
    };
    public void clear(){
        pedidos.clear();
        users.clear();
        pedidosQueue.clear();
        productos.clear();
    };
    public int sizeProductos(){
        return productos.size();
    };
    public int sizePedidos(){
      return pedidos.size();
    };
    public int sizeUsers(){
        return users.size();
    };
    public List<Pedido> DamePedidos()
    {
        return pedidos;
    }
    public int sizePedidosQueue(){
        return pedidosQueue.size();
    }
}
