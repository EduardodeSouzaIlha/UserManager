package com.example.gestao.gestao.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @NotNull
    private String gender;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "gender", fetch = FetchType.EAGER) //Apenas serve para que possamos acessar os usuarios pelo genero 
    private List<User> users;


    public Gender() {
    }

    public Gender(Long id, String gender, List<User> users) {
        this.id = id;
        this.gender = gender;
        this.users = users;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Gender id(Long id) {
        setId(id);
        return this;
    }

    public Gender gender(String gender) {
        setGender(gender);
        return this;
    }

    public Gender users(List<User> users) {
        setUsers(users);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Gender)) {
            return false;
        }
        Gender gender = (Gender) o;
        return Objects.equals(id, gender.id) && Objects.equals(gender, gender.gender) && Objects.equals(users, gender.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, users);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", gender='" + getGender() + "'" +
            ", users='" + getUsers() + "'" +
            "}";
    }
    
}
