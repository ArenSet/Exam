package am.hitech.service;

import am.hitech.model.User;
import am.hitech.model.dto.UserRequestDto;
import am.hitech.util.exception.DuplicateException;

public interface UserService {
    User getByUserName(String email);

    void create(UserRequestDto requestDto) throws DuplicateException;

    User findById(int id);

    void change(int id);
}
