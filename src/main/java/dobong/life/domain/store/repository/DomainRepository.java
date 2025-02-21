package dobong.life.domain.store.repository;

import dobong.life.domain.store.Domain;
import dobong.life.domain.store.SubTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    @Query("SELECT d.domain FROM MiddleCategory d " + "WHERE d.subTag = :subTag")
    List<Domain> findBySubTag(SubTag subTag);

    @Query("SELECT d.domain FROM MiddleCategory d " + "WHERE d.subTag = :subTag " + "AND d.domain.name LIKE %:query%")
    List<Domain> findBySubTagAndQuery(SubTag subTag, String query);

    @Query("SELECT DISTINCT d.domain FROM MiddleCategory d " +
            "WHERE (:categoryNames IS NULL OR d.subCategory.subCategoryName IN :categoryNames) " +
            "AND (:subTagIds IS NULL OR d.subTag.id IN :subTagIds)")
    List<Domain> findByFilters(List<String> categoryNames, List<Long> subTagIds);

    /**
     * query DSL
     *
     * public List<Domain> findDomainsByFilters(List<String> categoryNames, List<String> subTagNames) {
     *     BooleanBuilder builder = new BooleanBuilder();
     *
     *     if (!categoryNames.isEmpty()) {
     *         builder.and(QDomain.domain.subCategory.name.in(categoryNames));
     *     }
     *
     *     if (!subTagNames.isEmpty()) {
     *         builder.and(QDomain.domain.subTag.subTagName.in(subTagNames));
     *     }
     *
     *     return queryFactory
     *         .selectFrom(QDomain.domain)
     *         .where(builder)
     *         .fetch();
     * }
     */
}
