package am.hitech.service.impl;

import am.hitech.model.Check;
import am.hitech.repository.CheckRepository;
import am.hitech.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    private CheckRepository checkRepository;

    @Override
    public void check(int id){
        checkRepository.check(id);
    }

    @Override
    public void date(String milliseconds, int id){
        checkRepository.date(milliseconds, id);
    }

    @Override
    public void milliseconds(Long milliseconds, int id){
        checkRepository.milliseconds(milliseconds, id);
    }

    @Override
    public Check find(int userId, String date){
        return checkRepository.find(userId, date);
    }

    @Override
    public Integer count(String date, int id){
        return checkRepository.countByDateAndUserId(date, id);
    }

    @Override
    public boolean existByDateAndUserId(String date, int userId){
        return checkRepository.existsByDateAndUserId(date, userId);
    }

    @Override
    public void create(int userId, String date, Long milis){
        checkRepository.create(userId, date, milis);
    }
}
