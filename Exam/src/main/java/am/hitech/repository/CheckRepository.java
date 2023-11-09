package am.hitech.repository;

import am.hitech.model.Check;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CheckRepository extends JpaRepository<Check, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `checks` set `check` = `check` + 1 where `id` = ?1")
    void check(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `checks` set `date` = ?1 where `id` = ?2")
    void date(String milliseconds, int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `checks` set `milis` = ?1 where `id` = ?2")
    void milliseconds(Long milliseconds, int id);

    @Query(nativeQuery = true, value = "select * from `checks` where `user_id` = ?1 and date = ?2")
    Check find(int id, String date);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM `checks` WHERE date = ?1 AND user_id = ?2")
    int countByDateAndUserId(String date, int id);

    boolean existsByDateAndUserId(String date, int userId);


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into `checks` (id, user_id, date, milis, `check`) values (0, ?1, ?2, ?3, 1)")
    void create(int userId, String date, Long milis);



}
