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
}
