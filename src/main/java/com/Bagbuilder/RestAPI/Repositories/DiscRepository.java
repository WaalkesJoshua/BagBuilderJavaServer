package com.Bagbuilder.RestAPI.Repositories;

import com.Bagbuilder.RestAPI.Models.Disc;
import com.Bagbuilder.RestAPI.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscRepository extends JpaRepository<Disc, Integer> {
}
