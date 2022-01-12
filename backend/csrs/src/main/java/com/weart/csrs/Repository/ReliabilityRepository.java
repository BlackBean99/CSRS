package com.weart.csrs.Repository;

import com.weart.csrs.domain.reliability.Reliability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReliabilityRepository extends JpaRepository<Reliability, Long> {

    List<Reliability> findBySuccessfulBid(String successfulBid);

    List<Reliability> findByWarningScore(String warningScore);

    Reliability save(Reliability reliability);

}
