package am.hitech.service;

public interface MonthsService {
    int findByMonthAndUserId(int month, int id);

    Integer checksCount(int month, int id);

    Integer quarterCount(int month1, int month2, int month3, int id);

    void checksAdd(int month, int id);
}
