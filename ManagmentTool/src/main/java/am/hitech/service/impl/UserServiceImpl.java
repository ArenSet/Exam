package am.hitech.service.impl;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDto;
import am.hitech.repository.UserRepository;
import am.hitech.service.UserService;
import am.hitech.util.ErrorMessage;
import am.hitech.util.exception.DuplicateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int id){
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email){
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public List<User> getActiveUsers(){
        return userRepository.getActiveUsers();
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
    public User getByUserName(String email){
        User user = userRepository.findByEmail(email);

        return user;
    }

    @Override
    public List<User> search(String name, String surname, String email){
        List<User> users = userRepository.search(name, surname, email);

        return users;
    }

    @Override
    public List<User> name(String name){
        return userRepository.name(name);
    }

    @Override
    public void edit(String name, String surname, String email){
        userRepository.edit(name, surname, email);
    }

    @Override
    public void change(int id){
        userRepository.change(id);
    }

    @Override
    public void code(int code, String email){
        userRepository.code(code, email);
    }

    @Override
    public void verification(String email){
        userRepository.verification(email);
    }

    @Override
    public void token(int token, String email){
        userRepository.token(token, email);
    }

    @Override
    public void changePassword(String password, String email){
        userRepository.changePassword(password, email);
    }

    @Override
    public void activate(String email){
        userRepository.activate(email);
    }

    private User convertToUser(UserRequestDto userRequestDto, User user){

        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setStatus(userRequestDto.getStatus());
        user.setRole(userRequestDto.getRoles());

        return user;
    }

    /*private UserResponseDto convertToDto(User user){
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setEmail(user.getEmail());
        responseDto.setAge(user.getAge());
        responseDto.setId(user.getId());

        return responseDto;
    }

    private UserResponseDto requestToResponse(UserRequestDto requestDto){
        UserResponseDto responseDto = new UserResponseDto();

        responseDto.setFirstName(requestDto.getFirstName());
        responseDto.setLastName(requestDto.getLastName());
        responseDto.setAge(requestDto.getAge());
        responseDto.setEmail(requestDto.getEmail());

        return responseDto;
    }*/

}
