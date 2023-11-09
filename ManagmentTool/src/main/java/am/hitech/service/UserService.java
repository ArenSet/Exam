package am.hitech.service;

import am.hitech.model.User;
import am.hitech.model.dto.request.UserRequestDto;
import am.hitech.util.exception.DuplicateException;

import java.util.List;

public interface UserService {
    User findById(int id);

    List<User> findAll();

    void create(UserRequestDto requestDto) throws DuplicateException;

    User getByUserName(String email);

    List<User> search(String name, String surname, String email);

    List<User> name(String name);

    void edit(String name, String surname, String email);

    User findByEmail(String email);

    List<User> getActiveUsers();

    void change(int id);

    void code(int code, String email);

    void verification(String email);

    void token(int token, String email);

    void changePassword(String password, String email);

    void activate(String email);
}
