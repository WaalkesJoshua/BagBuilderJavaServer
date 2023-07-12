package com.Bagbuilder.RestAPI.Repositories;

import com.Bagbuilder.RestAPI.Models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll(Sort sort);

    boolean exitsByEmail(String email);

}
