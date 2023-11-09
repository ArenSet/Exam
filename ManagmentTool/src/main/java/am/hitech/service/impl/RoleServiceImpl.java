package am.hitech.service.impl;

import am.hitech.model.Role;
import am.hitech.repository.RoleRepository;
import am.hitech.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findById(int id){
        Role role = roleRepository.findById(id);
        return role;
    }


    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
