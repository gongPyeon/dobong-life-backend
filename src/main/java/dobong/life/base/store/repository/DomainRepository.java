package dobong.life.base.store.repository;

import dobong.life.base.store.Domain;
import dobong.life.base.store.DomainLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    @Query("SELECT d FROM Domain d " + "WHERE d.category.categoryName = :categoryName")
    Optional<List<Domain>> findByCategoryName(String categoryName);

    @Query("SELECT d FROM Domain d " +
            "WHERE d.name LIKE %:query% " +
            "AND ( " +
            "   :filter IS NULL " +
            "   OR d.category.categoryName IN :filter " +
            "   OR d.category.subCategoryName IN :filter" +
            ")")
    Optional<List<Domain>> findByQueryAndFilter(String query, List<String> filter);

    @Query("SELECT d FROM Domain d " + "WHERE d.tag.hashtagName = :hashTag")
    Optional<List<Domain>> findByHashTag(String hashTag);
}
