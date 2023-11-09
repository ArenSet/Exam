package am.hitech.service;

import am.hitech.model.Check;

import java.util.List;

public interface CheckService {

    void check(int id);

    void date(String milliseconds, int id);

    void milliseconds(Long milliseconds, int id);

    Check find(int userId, String date);

    Integer count(String date, int id);

    boolean existByDateAndUserId(String date, int userId);

    void create(int userId, String date, Long milis);
}
