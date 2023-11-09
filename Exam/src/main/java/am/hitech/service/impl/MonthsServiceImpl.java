package am.hitech.service.impl;

import am.hitech.repository.MonthsRepository;
import am.hitech.service.MonthsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthsServiceImpl implements MonthsService {
    @Autowired
    private MonthsRepository monthsRepository;

    @Override
    public int findByMonthAndUserId(int month, int id){
        return monthsRepository.findByMonthAndUserId(month, id);
    }

    @Override
    public Integer checksCount(int month, int id){
        return monthsRepository.checksCount(month, id);
    }

    @Override
    public Integer quarterCount(int month1, int month2, int month3, int id){
        return monthsRepository.quarterCount(month1, month2, month3, id);
    }

    @Override
    public void checksAdd(int month, int id){
        monthsRepository.checkAdd(month, id);
    }
}
