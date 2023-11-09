package am.hitech.service.impl;

import am.hitech.model.User;
import am.hitech.model.dto.UserRequestDto;
import am.hitech.repository.UserRepository;
import am.hitech.service.UserService;
import am.hitech.util.ErrorMessage;
import am.hitech.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getByUserName(String email){

        return userRepository.findByEmail(email);
    }

    @Override
    public void create(UserRequestDto requestDto) throws DuplicateException {
        if (userRepository.existsByEmail(requestDto.getEmail())){
            throw new DuplicateException(ErrorMessage.DUPLICATE_EMAIL);
        }

        User user = convertToUser(requestDto, new User());
        userRepository.save(user);
    }

    @Override
    public User findById(int id){
        return userRepository.findById(id);
    }

    @Override
    public void change(int id){

        userRepository.change(id);
    }

    private User convertToUser(UserRequestDto userRequestDto, User user){

        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setStatus("UNVERIFIED");
        user.setRole(userRequestDto.getRoles());
        user.setSalary(userRequestDto.getSalary());

        return user;
    }
}
