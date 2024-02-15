package com.example.gestao.gestao.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "user_entity", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    
    @Email(message = "O email deve ser válido")
    @NotBlank(message = "É necessário cadastrar um Email")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "É necessário cadastrar uma BIO")
    private String bio;

    @JsonIgnore
    @NotNull
    @Transient
    private long genderId; //Em vez de fazer um DTO preferi criar essa propriedade que  não será mapeada para o banco 

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gender_id") // Coluna na tabela User que referencia o Gender
    private Gender gender;
    
    @NotNull(message = "O campo 'confirmed' não pode ser nulo")
    private boolean confirmed;



    public User() {
    }

    public User(Long id, String name, String email, String bio, long genderId, Gender gender, boolean confirmed) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.bio = bio;
        this.genderId = genderId;
        this.gender = gender;
        this.confirmed = confirmed;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public long getGenderId() {
        return this.genderId;
    }

    public void setGenderId(long genderId) {
        this.genderId = genderId;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public boolean getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User bio(String bio) {
        setBio(bio);
        return this;
    }

    public User genderId(long genderId) {
        setGenderId(genderId);
        return this;
    }

    public User gender(Gender gender) {
        setGender(gender);
        return this;
    }

    public User confirmed(boolean confirmed) {
        setConfirmed(confirmed);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(bio, user.bio) && genderId == user.genderId && Objects.equals(gender, user.gender) && confirmed == user.confirmed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, bio, genderId, gender, confirmed);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", bio='" + getBio() + "'" +
            ", genderId='" + getGenderId() + "'" +
            ", gender='" + getGender() + "'" +
            ", confirmed='" + isConfirmed() + "'" +
            "}";
    }
    

}




   