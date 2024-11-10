package edu.upc.dsa;

import edu.upc.dsa.exceptions.ComandaNotFoundException;
import edu.upc.dsa.exceptions.EmptyListException;
import edu.upc.dsa.exceptions.PedidoNotFoundException;
import edu.upc.dsa.exceptions.ProductoNotFoundException;
import edu.upc.dsa.exceptions.UsuarioNotFoundException;

import edu.upc.dsa.models.Comanda;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Productos;
import edu.upc.dsa.models.Usuario;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Queue;



public class ProductManagerTest {
    ProductManager Pm;

    @Before
    public void setUp() {
        this.Pm = ProductManagerImpl.getInstance();
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

    @After
    public void tearDown() {
        this.Pm.clear();
    }

    @Test
    public void HacerPedido() {
        Assert.assertEquals(1, Pm.sizePedidos());
        Productos producto2 = new Productos("Cafe", 1.5,0);
        this.Pm.addProductos(producto2);
        Pedido pedido2 = new Pedido();
        this.Pm.addPedido(pedido2);
        Usuario usurio2=new Usuario("Manel","1234");
        this.Pm.addUsuario(usurio2);
        this.Pm.HacerComanda(pedido2.getId(), "Cafe",2,"Marcel");
        Productos producto3 = new Productos("Colacao", 15,200);
        this.Pm.addProductos(producto3);
        List<Pedido> PedidosUsuario = this.Pm.DamePedidosRealizadosUsuario("Marcel");
        int j =124;
        Assert.assertEquals(2, Pm.sizePedidos());
    }
    @Test
    public void ServirPedido(){
        Assert.assertEquals(1, Pm.sizePedidosQueue());
        this.Pm.ServirPedido();
        Assert.assertEquals(0, Pm.sizePedidosQueue());
    }

//    @Test
//    public void getTrackTest() throws Exception {
//        Assert.assertEquals(3, tm.size());
//
//        Track t = this.tm.getTrack("T2");
//        Assert.assertEquals("Despacito", t.getTitle());
//        Assert.assertEquals("Luis Fonsi", t.getSinger());
//
//        t = this.tm.getTrack2("T2");
//        Assert.assertEquals("Despacito", t.getTitle());
//        Assert.assertEquals("Luis Fonsi", t.getSinger());
//
//        Assert.assertThrows(TrackNotFoundException.class, () ->
//                this.tm.getTrack2("XXXXXXX"));
//
//    }
//
//    @Test
//    public void getTracksTest() {
//        Assert.assertEquals(3, tm.size());
//        List<Track> tracks  = tm.findAll();
//
//        Track t = tracks.get(0);
//        Assert.assertEquals("La Barbacoa", t.getTitle());
//        Assert.assertEquals("Georgie Dann", t.getSinger());
//
//        t = tracks.get(1);
//        Assert.assertEquals("Despacito", t.getTitle());
//        Assert.assertEquals("Luis Fonsi", t.getSinger());
//
//        t = tracks.get(2);
//        Assert.assertEquals("Ent3r S4ndm4n", t.getTitle());
//        Assert.assertEquals("Metallica", t.getSinger());
//
//        Assert.assertEquals(3, tm.size());
//
//    }
//
//    @Test
//    public void updateTrackTest() {
//        Assert.assertEquals(3, tm.size());
//        Track t = this.tm.getTrack("T3");
//        Assert.assertEquals("Ent3r S4ndm4n", t.getTitle());
//        Assert.assertEquals("Metallica", t.getSinger());
//
//        t.setTitle("Enter Sandman");
//        this.tm.updateTrack(t);
//
//        t = this.tm.getTrack("T3");
//        Assert.assertEquals("Enter Sandman", t.getTitle());
//        Assert.assertEquals("Metallica", t.getSinger());
//    }
//
//
//    @Test
//    public void deleteTrackTest() {
//        Assert.assertEquals(3, tm.size());
//        this.tm.deleteTrack("T3");
//        Assert.assertEquals(2, tm.size());
//
//        Assert.assertThrows(TrackNotFoundException.class, () ->
//                this.tm.getTrack2("T3"));
//
//    }
}
