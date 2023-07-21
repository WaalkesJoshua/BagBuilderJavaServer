package com.Bagbuilder.RestAPI.Repositories;

import com.Bagbuilder.RestAPI.Models.Bag;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface BagRepository extends JpaRepository<Bag, Long> {

    @Query(value="SELECT id\n" +
            "    FROM bags\n" +
            "    WHERE user_id = :userId", nativeQuery = true)
    List<Long> getAllBagIdByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value="DELETE FROM bags_discs\n" +
            "  WHERE bags_id IN :bagIds", nativeQuery = true)
    int deleteDiscRelationsForBagIds(@Param("bagIds") List<Long> bagIds);

    @Modifying
    @Query(value="DELETE FROM bags\n" +
            "    WHERE user_id = :userId", nativeQuery = true)
    int deleteAllBagsByUserId(@Param("userId") Long userId);

}
