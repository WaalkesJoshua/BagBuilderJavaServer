package com.Bagbuilder.RestAPI.Repositories;

import com.Bagbuilder.RestAPI.Models.Bag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {
}
