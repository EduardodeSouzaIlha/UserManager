package com.example.gestao.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestao.gestao.entity.Gender;
import com.example.gestao.gestao.repository.GenderRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/gender")
public class GenderController {
    
    @Autowired
    private GenderRepository genderRepository;

    @GetMapping
    public ResponseEntity<?> getGender() { //Deixei no padrão de recebimento de solicitações do spring (não aceita JSON)
        try {
            List<Gender> genders = genderRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(genders);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao recuperar usuários: " + e.getMessage()); 
        }
        
    }
}
