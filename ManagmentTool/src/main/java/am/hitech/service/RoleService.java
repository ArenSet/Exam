package am.hitech.service;

import am.hitech.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService{


    Role findById(int id);

    List<Role> findAll();
}
