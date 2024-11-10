package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Productos {
    String id;
    String name;
    double precio;
    int ventas;
    static int lastId;

    public Productos() {
        this.setId(RandomUtils.getId());
    }
    public Productos(String name, double precio, int ventas) {
        this(null, name, precio, ventas);
    }

    public Productos(String id, String name, double precio, int ventas) {
        this();
        if (id != null) this.setId(id);
        this.setName(name);
        this.setPrecio(precio);
        this.setVentas(ventas);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        return "Producto [id="+id+", nombre=" + name + ", precio=" + precio +" ventas=" + ventas +"]";
    }

}
