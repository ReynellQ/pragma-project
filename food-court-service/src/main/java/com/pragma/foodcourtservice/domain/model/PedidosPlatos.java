package com.pragma.foodcourtservice.domain.model;

public class PedidosPlatos {
    private Long idPedido;
    private Long idPlato;
    private Integer cantidad;
    
    public Long getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
    public Long getIdPlato() {
        return idPlato;
    }
    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }
    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
