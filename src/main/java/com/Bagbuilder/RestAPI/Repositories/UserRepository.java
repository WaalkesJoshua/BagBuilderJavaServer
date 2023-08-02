package com.Bagbuilder.RestAPI.Repositories;

import com.Bagbuilder.RestAPI.Models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll(Sort sort);

    boolean existsByEmail(String email);

    Optional<User> getByEmail(String email);
}
