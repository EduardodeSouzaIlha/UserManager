package com.example.gestao.gestao.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.gestao.gestao.entity.Gender;
import com.example.gestao.gestao.entity.User;
import com.example.gestao.gestao.exception.EmailDuplicateException;
import com.example.gestao.gestao.exception.UserNotFoundException;
import com.example.gestao.gestao.repository.GenderRepository;
import com.example.gestao.gestao.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GenderRepository genderRepository;

    public User createUser(User user) {  
        if (user == null) {
            throw new IllegalArgumentException("Usuario é nulo");
        }

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new EmailDuplicateException("Este e-mail já está em uso. Por favor, escolha outro.");
        }

        Gender gender = genderRepository.findById(user.getGenderId()).orElse(null);
        if (gender == null) {
            throw new NoSuchElementException("Gênero não encontrado");
        }

        user.setGender(gender);
        return userRepository.save(user);
    
    }


    public List<User> getUsers(){
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Nenhum usuário encontrado");
        }
        return users;
    }
    
    
    
    public User getUserById(long id) {
        User user = userRepository.findById(id).orElse(null);
    
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("Usuário não encontrado com o ID: " + id);
        }
    }
    
    public User updateUser(User user){
        User userExist = userRepository.findById(user.getId()).orElse(null);
        if (userExist == null) {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    
        Gender gender = genderRepository.findById(user.getGenderId()).orElse(null);
        if (gender == null) {
            throw new NoSuchElementException("Gênero não encontrado");
        }
    
        user.setGender(gender);
        User savedUser = userRepository.save(user);
        return savedUser;
    }
    
    public void deleteUserById(long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("O usuario com o seguinte id não foi encontrado: " + id);
        }
        userRepository.deleteById(id);
    }
    



}
