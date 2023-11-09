package am.hitech.repository;

import am.hitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    boolean existsByEmail(String email);

    User findById(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update `user` set status = if(status = 'UNVERIFIED','VERIFIED','UNVERIFIED') where id = ?1")
    void change(int id);


}
