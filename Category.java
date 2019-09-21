package com.example.jaroga.appempresa;

public class Category {
    private String descripcion;
    private int id;
    private String title;
    private double latidtud;
    private double longitud;
    private String direccion;


    public Category(int idtienda, String nombre, String idireccion, double latiitud, double longitud, String descripcion) {

        super();

    }

    public Category(String title, String descripcion) {
        super();
        this.title = title;
        this.descripcion = descripcion;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatidtud() {
        return latidtud;
    }

    public void setLatidtud(double latidtud) {
        this.latidtud = latidtud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Category() {
    }

    public Category(String descripcion, int id, String title, double latidtud, double longitud, String direccion) {
        this.descripcion = descripcion;
        this.id = id;
        this.title = title;
        this.latidtud = latidtud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public Category(String descripcion, String title, double latidtud, double longitud, String direccion) {
        this.descripcion = descripcion;
        this.title = title;
        this.latidtud = latidtud;
        this.longitud = longitud;
        this.direccion = direccion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


