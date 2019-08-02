package com.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "onlineshop")
@PrimaryKeyJoinColumn(name = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "code_id")
    private Code code;

    @Column(name = "email", length = 64, nullable = false)
    private String email;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "address", length = 60, nullable = false)
    private String address;

    public Order() {
    }

    public Order(Basket basket, User user, Code code, String email, String phoneNumber, String address) {
        this.basket = basket;
        this.user = user;
        this.code = code;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Order(Long id, Basket basket, User user, Code code, String email, String phoneNumber, String address) {
        this.id = id;
        this.basket = basket;
        this.user = user;
        this.code = code;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(basket, order.basket) &&
                Objects.equals(user, order.user) &&
                Objects.equals(code, order.code) &&
                Objects.equals(email, order.email) &&
                Objects.equals(phoneNumber, order.phoneNumber) &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, basket, user, code, email, phoneNumber, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", basket=" + basket +
                ", user=" + user +
                ", code=" + code +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
