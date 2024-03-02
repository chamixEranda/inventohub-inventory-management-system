package com.example.productcatalog.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "category_id")
    private Integer category_id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "alert_quantity")
    private Integer alert_quantity;

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private boolean is_active = true;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;

    public Product() {
    }

    public Product(Integer id, String name, String code, Integer category_id, BigDecimal price, BigDecimal cost, Integer qty, Integer alert_quantity, String image, String description, boolean is_active, Date created_at, Date updated_at) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.category_id = category_id;
        this.price = price;
        this.cost = cost;
        this.qty = qty;
        this.alert_quantity = alert_quantity;
        this.image = image;
        this.description = description;
        this.is_active = is_active;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getAlert_quantity() {
        return alert_quantity;
    }

    public void setAlert_quantity(Integer alert_quantity) {
        this.alert_quantity = alert_quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
