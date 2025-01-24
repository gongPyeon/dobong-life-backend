package dobong.life.repository;

import dobong.life.entity.Domain;
import dobong.life.entity.SubCategory;
import dobong.life.entity.SubTag;
import dobong.life.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    List<Domain> findBySubTag(SubTag subTag);

    Domain findBySubCategory(SubCategory subCategory);
}
