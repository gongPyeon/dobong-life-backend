package dobong.life.base.review.repository;

import dobong.life.base.review.Middle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MiddleRepository extends JpaRepository<Middle, Long> {

    @Query("SELECT m.keyword.reviewKwdName FROM Middle m ")
    List<String> findAllBy();
}
