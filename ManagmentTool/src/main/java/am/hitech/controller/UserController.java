package am.hitech.controller;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDto;
import am.hitech.service.UserService;
import am.hitech.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/id")
    public ResponseEntity<User> findById(@RequestParam int id, Authentication auth){

        String s = auth.getName();
        User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    //@RolesAllowed("ADMIN")
    @PreAuthorize("hasAuthority('HR') OR hasAuthority('PM')")
    @GetMapping("/find-all")
    public ResponseEntity<List<User>> findAll(){
        List<User> users = userService.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/find-all-active")
    public ResponseEntity<List<User>> getActiveUsers(){
        List<User> users = userService.getActiveUsers();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody @Valid UserRequestDto requestDto) throws DuplicateException {
        userService.create(requestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> search(@RequestParam(required = false) String name,
                                             @RequestParam(required = false) String surname,
                                             @RequestParam(required = false) String email){
        List<User> users = userService.search(name, surname, email);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/name")
    public ResponseEntity<List<User>> name(@RequestParam String name){

        List<User> users = userService.name(name);
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Void> edit(@RequestParam String name,
                                     @RequestParam String surname){
        userService.edit(name, surname, getCurrentUsername());

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('HR')")
    @PatchMapping("/change")
    public ResponseEntity<Void> change(@RequestParam int id){
        userService.change(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/code")
    public ResponseEntity<Void> code(){
        Random random = new Random();

        int code = random.nextInt(150 + 1000000);
        userService.code(code, getCurrentUsername());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/verification")
    public ResponseEntity<Void> verification(@RequestParam int code){
        User user = userService.findByEmail(getCurrentUsername());
        if (code == user.getVerificationCode()){
            userService.verification(getCurrentUsername());
        }
       return ResponseEntity.ok().build();
    }

    @PatchMapping("/token")
    public ResponseEntity<Void> token(){
        Random random = new Random();

        int token = random.nextInt(200 + 100000);
        userService.token(token, getCurrentUsername());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(@RequestParam int token,
                                               @RequestParam String password,
                                               @RequestParam String matchPassword){

        User user = userService.findByEmail(getCurrentUsername());

        if (token == user.getPasswordToken() && password.equals(matchPassword)){
            userService.changePassword(passwordEncoder.encode(password), getCurrentUsername());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/own-info")
    public ResponseEntity<User> getOwnInfo(){
        User user = userService.findByEmail(getCurrentUsername());

        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasAuthority('HR')")
    @PatchMapping("/activate")
    public ResponseEntity<Void> activate(@RequestParam int id){
        User user = userService.findById(id);

        userService.activate(user.getEmail());

        return ResponseEntity.ok().build();
    }


    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        String username = authentication.getName();
        return username;
    }

}
