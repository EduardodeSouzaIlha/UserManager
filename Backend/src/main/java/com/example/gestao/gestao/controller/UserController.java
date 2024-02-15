package com.example.gestao.gestao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestao.gestao.entity.Gender;
import com.example.gestao.gestao.entity.User;
import com.example.gestao.gestao.exception.EmailDuplicateException;
import com.example.gestao.gestao.repository.GenderRepository;
import com.example.gestao.gestao.repository.UserRepository;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

     @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository; 

    @PostMapping
    public ResponseEntity<?> createUser(@Validated User user) { //Deixei no padrão de recebimento de solicitações do spring (não aceita JSON)
          try{
            User existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) {
                // E-mail já em uso, envie uma mensagem ao usuário ou lance uma exceção
                throw new EmailDuplicateException("Este e-mail já está em uso. Por favor, escolha outro.");
            }else{
                Gender gender = genderRepository.findById(user.getGenderId()).orElse(null);
                if (gender != null){
                    user.setGender(gender);

                    User savedUser = userRepository.save(user);    
                    
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
                }
            }  
        } catch (EmailDuplicateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + e.getMessage()); 
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsers() { //Deixei no padrão de recebimento de solicitações do spring (não aceita JSON)
        try {
            List<User> users = userRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao recuperar usuários: " + e.getMessage()); 
        }
        
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") long id) {
        try{
            User user = userRepository.findById(id).orElse(null);; 
            if(user != null){
                return ResponseEntity.ok(user);
             }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
             }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + e.getMessage()); 
        }
        
    }
    @PutMapping
    public ResponseEntity<?> updateUser(@Validated User user) {
        try{
            User userExist = userRepository.findById(user.getId()).orElse(null);

            if (userExist != null) {
                Gender gender = genderRepository.findById(user.getGenderId()).orElse(null);
                if (gender != null){
                    user.setGender(gender);

                    User savedUser = userRepository.save(user);    
                    
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
                }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found"); 
            }
            
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + e.getMessage()); 
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") long id) {
        try{
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully!");
       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + e.getMessage()); 
       }
    }
    

}
