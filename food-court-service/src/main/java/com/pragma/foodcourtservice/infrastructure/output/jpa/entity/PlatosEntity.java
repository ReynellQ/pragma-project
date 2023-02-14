package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "PLATOS")
public class PlatosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Column(name = "id_categoria")
    private Long idCategoria;
    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable=false, updatable=false)
    private CategoriaEntity categoria;
    private String descripcion;
    private Long precio;
    @Column(name = "id_restaurante")

    private Long idRestaurante;
    @ManyToOne
    @JoinColumn(name = "id_restaurante", insertable=false, updatable=false)
    private RestaurantesEntity restaurante;
    @Column(name = "url_imagen")
    private String urlImagen;
    @Column(columnDefinition = "boolean default true")
    private Boolean activo;


    public PlatosEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public RestaurantesEntity getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestaurantesEntity restaurante) {
        this.restaurante = restaurante;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
