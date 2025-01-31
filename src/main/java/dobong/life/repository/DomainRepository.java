package dobong.life.repository;

import dobong.life.entity.Domain;
import dobong.life.entity.SubCategory;
import dobong.life.entity.SubTag;
import dobong.life.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    List<Domain> findBySubTag(SubTag subTag);

    @Query("SELECT d FROM Domain d " + "WHERE d.subTag = :subTag " + "AND d.nameKr LIKE %:query%")
    List<Domain> findBySubTagAndQuery(SubTag subTag, String query);

    @Query("SELECT DISTINCT d FROM Domain d " +
            "WHERE (:categoryNames IS NULL OR d.subCategory.name IN :categoryNames) " +
            "AND (:subTagNames IS NULL OR d.subTag.subTagName IN :subTagNames)")
    List<Domain> findByFilters(List<String> categoryNames, List<String> subTagNames);

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
