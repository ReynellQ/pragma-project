package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "RESTAURANT")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    @Column(name = "id_owner")
    private Long idOwner;
    private String phone;

    @Column(name = "url_logo")
    private String urlLogo;
    private Long nit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }
}
