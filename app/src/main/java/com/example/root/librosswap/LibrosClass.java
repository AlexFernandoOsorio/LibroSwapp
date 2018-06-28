package com.example.root.librosswap;

public class LibrosClass {
    private String idLibros;
    private String codUsuario;
    private String idLSBM;
    private String titulo;
    private String autor;
    private String edicion;
    private String anioPublicacion;
    private String editorial;
    private String portada;
    private String descripcio;
    private String estadoLibr;
    private String ubicacion;
    private String Categoria;

    public LibrosClass() {
    }

    public LibrosClass(String idLibros, String codUsuario, String idLSBM, String titulo, String autor, String edicion, String anioPublicacion, String editorial, String portada, String descripcio, String estadoLibr, String ubicacion,
    String Categoria) {
        this.idLibros = idLibros;
        this.codUsuario = codUsuario;
        this.idLSBM = idLSBM;
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
        this.anioPublicacion = anioPublicacion;
        this.editorial = editorial;
        this.portada = portada;
        this.descripcio = descripcio;
        this.estadoLibr = estadoLibr;
        this.ubicacion = ubicacion;
        this.Categoria=Categoria;
    }

    public LibrosClass(String idLibros,String idLSBM, String titulo, String autor, String estadoLibr, String portada) {
        this.idLibros=idLibros;
        this.idLSBM = idLSBM;
        this.titulo = titulo;
        this.autor = autor;
        this.estadoLibr = estadoLibr;
        this.portada=portada;
    }
    public LibrosClass(String idLSBM, String titulo, String autor, String estadoLibr, String portada) {
        this.idLSBM = idLSBM;
        this.titulo = titulo;
        this.autor = autor;
        this.estadoLibr = estadoLibr;
        this.portada=portada;
    }

    public String getIdLibros() {
        return idLibros;
    }

    public void setIdLibros(String idLibros) {
        this.idLibros = idLibros;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getIdLSBM() {
        return idLSBM;
    }

    public void setIdLSBM(String idLSBM) {
        this.idLSBM = idLSBM;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(String anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getEstadoLibr() {
        return estadoLibr;
    }

    public void setEstadoLibr(String estadoLibr) {
        this.estadoLibr = estadoLibr;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
