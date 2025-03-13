package dobong.life.base.store.repository;

import dobong.life.base.store.Domain;
import dobong.life.base.store.DomainLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    @Query("SELECT d FROM Domain d " + "WHERE d.category.id = :categoryId")
    List<Domain> findByCategoryId(Long categoryId);

    @Query("SELECT d FROM Domain d " +
            "WHERE d.name LIKE %:query% " +
            "AND ( " +
            "   :filter IS EMPTY " +
            "   OR d.category.categoryName IN :filter " +
            "   OR d.category.subCategoryName IN :filter" +
            ")")
    List<Domain> findByQueryAndFilter(String query, List<String> filter);
}
