package dobong.life.base.store.repository;

import dobong.life.base.store.Domain;
import dobong.life.base.store.support.StoreFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
// @Import(TestQueryDslConfig.class)
class DomainRepositoryTest {
    @Autowired
    private DomainRepository domainRepository;

    @Nested
    @DisplayName("findByCategoryName 메서드 실행 시")
    class findByCategoryNameMethod {

        @Test
        @DisplayName("성공")
        public void success() {
            // given
            String categoryName = StoreFixture.CATEGORY_NAME;
            Domain testDomain = StoreFixture.domain();
            domainRepository.save(testDomain);

            // when
            Optional<List<Domain>> result = domainRepository.findByCategoryName(categoryName);

            // then
            assertThat(result.get().size()).isEqualTo(1);
            assertThat(result.get().getFirst()).isEqualTo(testDomain);
        }
    }

}