package com.ldf.springprak14.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ldf.springprak14.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);

    Optional<User> findByUsername(String username);
}