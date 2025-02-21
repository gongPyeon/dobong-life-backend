package dobong.life.domain.store.repository;

import dobong.life.domain.store.SubTag;
import dobong.life.domain.store.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTagRepository extends JpaRepository<SubTag, Long> {
    List<SubTag> findByTag(Tag tag);

    List<SubTag> findBySubTagName(String subTagName);
}
