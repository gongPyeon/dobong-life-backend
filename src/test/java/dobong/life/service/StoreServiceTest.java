package dobong.life.service;

import dobong.life.dto.StoresResDto;
import dobong.life.dto.info.ItemInfo;
import dobong.life.entity.Category;
import dobong.life.entity.User;
import dobong.life.enums.ParentCategoryType;
import dobong.life.service.query.CategoryQueryService;
import dobong.life.service.query.StoreQueryService;
import dobong.life.service.query.TagQueryService;
import dobong.life.service.query.UserQueryService;
import dobong.life.util.exception.CategoryNotFoundException;
import dobong.life.util.exception.UserNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
@DisplayName(("StoreService를_테스트_한다"))
class StoreServiceTest {

    @InjectMocks
    private StoreService storeService;

    @Mock
    private StoreQueryService storeQueryService;

    @Mock
    private CategoryQueryService categoryQueryService;

    @Mock
    private UserQueryService userQueryService;

    @Mock
    private TagQueryService tagQueryService;

     Category testCategory;
    User testUser;
    List<ItemInfo> testItems;

    @BeforeEach
    void setUp() { // domain에서 이렇게 임시로 주입시키는게 맞을까?
        testCategory = Category.create(1L);
        testUser = User.create(1L);
        testItems = Collections.singletonList(
                ItemInfo.create(1L)
        );
    }
    @Nested
    @DisplayName("상점 목록 조회 Service 실행 시")
    class GetStoreListTest {

        @Test
        @DisplayName("성공")
        void getStoreList_success() {
            // given
            Long categoryId = 1L;
            Long userId = 1L;

            given(categoryQueryService.getCategory(anyLong())).willReturn(testCategory);
            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(tagQueryService.getItemInfos(any(), any())).willReturn(testItems);

            // when
            StoresResDto result = storeService.getStoreList(categoryId, userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getCategoryId()).isEqualTo(categoryId);
            Assertions.assertThat(result.getItems()).hasSize(1);

            then(categoryQueryService).should().getCategory(categoryId);
            then(userQueryService).should().getUserById(userId);
            then(tagQueryService).should().getItemInfos(testCategory, testUser);
        }

        @Test
        @DisplayName("카테고리가 존재하지 않을 경우 실패")
        void getStoreList_categoryNotFound() {
            // given
            Long categoryId = 999L;
            Long userId = 1L;

            given(categoryQueryService.getCategory(categoryId)).willThrow(new CategoryNotFoundException(categoryId));

            // when & then
            assertThrows(CategoryNotFoundException.class,
                    () -> storeService.getStoreList(categoryId, userId));
        }

        @Test
        @DisplayName("사용자가 존재하지 않을 경우 실패")
        void getStoreList_userNotFound() {
            // given
            Long categoryId = 1L;
            Long userId = 999L;

            given(categoryQueryService.getCategory(any())).willReturn(testCategory);
            given(userQueryService.getUserById(userId)).willThrow(new UserNotFoundException(userId));

            // when & then
            assertThrows(UserNotFoundException.class,
                    () -> storeService.getStoreList(categoryId, userId));
        }
    }

    @Nested
    @DisplayName("검색어로 상점 목록 조회 Service 실행 시")
    class GetStoreListByQueryTest {

        @Test
        @DisplayName("성공")
        void getStoreListByQuery_success() {
            // given
            Long categoryId = 1L;
            Long userId = 1L;
            String query = "순대";

            given(categoryQueryService.getCategory(anyLong())).willReturn(testCategory);
            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
            given(tagQueryService.getItemInfosByQuery(any(), any(), anyString())).willReturn(testItems);

            // when
            StoresResDto result = storeService.getStoreListByQuery(categoryId, userId, query);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getCategoryId()).isEqualTo(categoryId);
            Assertions.assertThat(result.getItems()).hasSize(1);

            then(categoryQueryService).should().getCategory(categoryId);
            then(userQueryService).should().getUserById(userId);
            then(tagQueryService).should().getItemInfosByQuery(testCategory, testUser, query);
        }

        @Test
        @DisplayName("카테고리가 존재하지 않을 경우 실패")
        void getStoreListByQuery_categoryNotFound() {
            // given
            Long categoryId = 999L;
            Long userId = 1L;

            given(categoryQueryService.getCategory(categoryId)).willThrow(new CategoryNotFoundException(categoryId));

            // when & then
            assertThrows(CategoryNotFoundException.class,
                    () -> storeService.getStoreList(categoryId, userId));
        }

        @Test
        @DisplayName("사용자가 존재하지 않을 경우 실패")
        void getStoreListByQuery_userNotFound() {
            // given
            Long categoryId = 1L;
            Long userId = 999L;

            given(categoryQueryService.getCategory(any())).willReturn(testCategory);
            given(userQueryService.getUserById(userId)).willThrow(new UserNotFoundException(userId));

            // when & then
            assertThrows(UserNotFoundException.class,
                    () -> storeService.getStoreList(categoryId, userId));
        }
    }
}