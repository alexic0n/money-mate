package com.alexic0n.moneymate.userapp.definition.user;

import com.alexic0n.moneymate.userapp.definition.user.model.User;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user){
        return ResponseEntity.created(
                URI.create(format("/money-mate/user-app/users/%s", userService.createEntity(user).getId().toString()))
        ).build();
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pageable){
        return ResponseEntity.ok(userService.getPageOfAllEntities(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getEntityById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable String id, @RequestBody @Valid User user){
        userService.updateEntity(id, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.deleteEntity(id);
        return ResponseEntity.ok().build();
    }
}
