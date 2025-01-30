package dobong.life.repository;

import dobong.life.entity.SubTag;
import dobong.life.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SubTagRepository extends JpaRepository<SubTag, Long> {
    List<SubTag> findByTag(Tag tag);

    List<SubTag> findBySubTagName(String subTagName);
}
