package am.hitech.controller;


import am.hitech.model.User;
import am.hitech.model.dto.UserRequestDto;
import am.hitech.service.UserService;
import am.hitech.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid UserRequestDto requestDto) throws DuplicateException {
        userService.create(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public ResponseEntity<User> findById(@RequestParam int id){
        User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/change")
    public ResponseEntity<Void> change(@RequestParam int id){
        userService.change(id);

        return ResponseEntity.ok().build();
    }
}
