package dobong.life.base.review.repository;

import dobong.life.base.review.Middle;
import dobong.life.base.review.Review;
import dobong.life.base.store.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MiddleRepository extends JpaRepository<Middle, Long> {

    @Query("SELECT m.keyword.reviewKwdName FROM Middle m " + "WHERE m.domain = :domain")
    List<String> findByDomain(Domain domain);

    List<String> findByReview(Review review);
}
