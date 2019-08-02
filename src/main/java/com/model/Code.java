package com.model;

import com.util.CodeGeneratorUtil;

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
@Table(name = "code", schema = "onlineshop")
@PrimaryKeyJoinColumn(name = "id")
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "value", length = 10, nullable = false)
    private String value;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Code() {
    }

    public Code(User user) {
        this.user = user;
        this.value = CodeGeneratorUtil.generate();
    }

    public Code(Long id, String value, User user) {
        this.id = id;
        this.user = user;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Code code = (Code) o;
        return Objects.equals(id, code.id) &&
                Objects.equals(value, code.value) &&
                Objects.equals(user, code.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, user);
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", user=" + user +
                '}';
    }
}
