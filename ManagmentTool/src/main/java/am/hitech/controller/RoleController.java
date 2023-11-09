package am.hitech.controller;


import am.hitech.model.Role;
import am.hitech.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping("/id")
    public ResponseEntity<Role> findById(@RequestParam int id){

        Role role = roleService.findById(id);

        return ResponseEntity.ok(role);
    }


    @GetMapping("/find-all")
    public ResponseEntity<List<Role>> findAll(){
        List<Role> roles = roleService.findAll();

        return ResponseEntity.ok(roles);
    }
}
