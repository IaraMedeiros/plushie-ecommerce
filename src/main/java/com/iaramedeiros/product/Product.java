package com.iaramedeiros.product;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    @Column(nullable = false, unique = true, length = 45) // campo nao pode ser nulo e deve ser unico

    private String name;
    @Column(nullable = false, unique = false) // campo nao pode ser nulo e deve ser unico

    private String description;
    @Column(nullable = false, unique = false)

    private Double price;
    @Column(nullable = true, unique = true)

    @Lob
    private byte[] img;
    @Column(nullable = false, unique = false)

    private String color;
    @Column(nullable = false, unique = false)

    private String size;

    public Product() {
    }

    public Product(Integer id, String name, String description, Double price, byte[] img, String color, String size) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.color = color;
        this.size = size;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
