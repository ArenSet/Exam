package am.hitech.repository;

import am.hitech.model.Months;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MonthsRepository extends JpaRepository<Months, Integer> {

    int findByMonthAndUserId(int month, int id);

    @Query(nativeQuery = true, value = "SELECT SUM(`checks`) FROM `months` WHERE `month` = ?1 AND user_id = ?2")
    Integer checksCount(int month, int userId);

    @Query(nativeQuery = true, value = "SELECT SUM(`checks`) FROM `months` WHERE (`month` = ?1 or `month` = ?2 or `month` = ?3) AND user_id = ?4")
    Integer quarterCount(int month1, int month2, int month3, int userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update `months` set `checks` = `checks` + 1 where `month` = ?1 and `user_id` = ?2")
    void checkAdd(int month, int userId);

}
