package com.jambolao.bgfinancas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jambolao.bgfinancas.model.user.User;
import com.jambolao.bgfinancas.model.user.UserResponseDTO;
import com.jambolao.bgfinancas.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        List<User> users = service.listUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable Long id) {
        User user = service.readUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> readByEmail(@PathVariable String email) {
        User user = service.readUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = service.createUser(user);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = service.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody User user, HttpSession session) {
        // Verifica se o email fornecido corresponde a algum usuário existente
        User existingUser = service.readUserByEmail(user.getEmail());

        if (existingUser == null) {
            // Retorna 404 se o usuário não for encontrado no banco de dados
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Verifica se a senha está correta
        User loggedInUser = service.login(user.getEmail(), user.getSenha());

        if (loggedInUser != null) {
            // Armazena o usuário na sessão
            session.setAttribute("user", loggedInUser);

            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    loggedInUser.getId(), loggedInUser.getNome(),
                    loggedInUser.getUltimoNome(), loggedInUser.getEmail());

            return ResponseEntity.ok(userResponseDTO);
        } else {
            // Retorna 401 se a senha estiver incorreta
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate(); // Invalida a sessão
        return ResponseEntity.ok("Logout realizado com sucesso");
    }

}