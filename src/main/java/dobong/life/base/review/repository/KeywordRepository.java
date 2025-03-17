package dobong.life.base.review.repository;

import dobong.life.base.review.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    Optional<Keyword> findByReviewKwdName(String keyword);
}
