package com.example.gestao.gestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestao.gestao.entity.Gender;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
    
}
