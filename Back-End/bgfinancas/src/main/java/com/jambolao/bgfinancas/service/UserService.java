package com.jambolao.bgfinancas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jambolao.bgfinancas.model.user.User;
import com.jambolao.bgfinancas.model.user.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;  

    @Transactional
    public User createUser(User user) {
        // Criptografa a senha antes de salvar
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return repository.save(user);
    }

    @Transactional
    public List<User> listUsers() {
        return (List<User>) repository.findAll();
    }

    @Transactional
    public User updateUser(Long id, User user) {
        user.setId(id);
        // Criptografan a senha ao atualizar
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return repository.save(user);
    }

    @Transactional
    public boolean deleteUser(Long id) {
        Optional<User> existingUser = repository.findById(id);
        if (existingUser.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public User readUser(Long id) {
        if (repository.existsById(id)) {
            return repository.findById(id).get();
        }
        return null;
    }

    @Transactional
    public User readUserByEmail(String email) {
        if (repository.findByEmail(email) != null) {
            return repository.findByEmail(email).get();
        }
        return null;
    }

    // Verifica se o email já existe no banco de dados e se a senha é válida
    @Transactional
    public User login(String email, String senha) {
        User user = repository.findByEmail(email);
        if (user != null && passwordEncoder.matches(senha, user.getSenha())) {
            return user;
        }
        return null;
    }

}
