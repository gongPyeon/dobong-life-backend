//package dobong.life.base.store.repository;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//// @Import(TestQueryDslConfig.class)
//class DomainRepositoryTest {
//    @Autowired
//    private DomainRepository domainRepository;
//
//    @Nested
//    @DisplayName("save 메서드 실행 시")
//    class SaveMethod {
//
//        @Test
//        @DisplayName("성공")
//        public void success() {
//            // given
//            String categoryName = "test category";
//            MainCategory mainCategory = new MainCategory(categoryName);
//
//            // when
//            MainCategory savedMainCategory = domainRepository.save(mainCategory);
//
//            // then
//            assertThat(savedMainCategory.getName()).isEqualTo(categoryName);
//        }
//
//        @Test
//        @DisplayName("예외: 이름이 null")
//        public void throwExceptionWhenNameIsBlank() {
//            // given
//            MainCategory mainCategory = new MainCategory(null);
//
//            // when & then
//            assertThatThrownBy(() -> domainRepository.save(mainCategory))
//                    .isInstanceOf(DataIntegrityViolationException.class);
//        }
//    }
//
//}