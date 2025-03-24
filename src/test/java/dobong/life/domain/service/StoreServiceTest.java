//package dobong.life.domain.service;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("StoreService를_테스트_한다")
//class StoreServiceTest {
//
//    @InjectMocks
//    private StoreService storeService;
//
//    @Mock
//    private StoreQueryService storeQueryService;
//
//    @Mock
//    private CategoryQueryService categoryQueryService;
//
//    @Mock
//    private UserQueryService userQueryService;
//
//    @Mock
//    private TagQueryService tagQueryService;
//
//    Category testCategory;
//    User testUser;
//
//    Domain testDomain;
//    StoreBasicInfo testStoreBasicInfo;
//    StoreDetailInfo testStoreDetailInfo;
//    List<ItemInfo> testItems;
//    List<StoreBasicInfo> testFilterItems;
//    List<String> testSubTagNames;
//
//    @BeforeEach
//    void setUp() { // domain에서 이렇게 임시로 주입시키는게 맞을까?
//        testCategory = Category.create(1L);
//        testUser = User.create(1L);
//        testItems = Collections.singletonList(
//                ItemInfo.create(1L)
//        );
//        testSubTagNames = List.of("분식");
//        testFilterItems = List.of(StoreBasicInfo.create(1L));
//
//        testDomain = Domain.create(1L);
//        testStoreBasicInfo = StoreBasicInfo.create(1L);
//        testStoreDetailInfo = StoreDetailInfo.create();
//    }
//
//    @Nested
//    @DisplayName("상점 목록 조회 Service 실행 시")
//    class GetStoreListTest {
//
//        @Test
//        @DisplayName("성공")
//        void getStoreList_success() {
//            // given
//            Long categoryId = 1L;
//            Long userId = 1L;
//
//            given(categoryQueryService.getCategory(anyLong())).willReturn(testCategory);
//            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
//            given(tagQueryService.getItemInfos(any(), any())).willReturn(testItems);
//
//            // when
//            StoresResDto result = storeService.getStoreList(categoryId, userId);
//
//            // then
//            assertThat(result).isNotNull();
//            assertThat(result.getCategoryId()).isEqualTo(categoryId);
//            Assertions.assertThat(result.getItems()).hasSize(1);
//
//            then(categoryQueryService).should().getCategory(categoryId);
//            then(userQueryService).should().getUserById(userId);
//            then(tagQueryService).should().getItemInfos(testCategory, testUser);
//        }
//
//        @Test
//        @DisplayName("카테고리가 존재하지 않을 경우 실패")
//        void getStoreList_categoryNotFound() {
//            // given
//            Long categoryId = 999L;
//            Long userId = 1L;
//
//            given(categoryQueryService.getCategory(categoryId)).willThrow(new CategoryNotFoundException(categoryId));
//
//            // when & then
//            assertThrows(CategoryNotFoundException.class,
//                    () -> storeService.getStoreList(categoryId, userId));
//        }
//
//        @Test
//        @DisplayName("사용자가 존재하지 않을 경우 실패")
//        void getStoreList_userNotFound() {
//            // given
//            Long categoryId = 1L;
//            Long userId = 999L;
//
//            given(categoryQueryService.getCategory(any())).willReturn(testCategory);
//            given(userQueryService.getUserById(userId)).willThrow(new UserNotFoundException(userId));
//
//            // when & then
//            assertThrows(UserNotFoundException.class,
//                    () -> storeService.getStoreList(categoryId, userId));
//        }
//    }
//
//    @Nested
//    @DisplayName("검색어로 상점 목록 조회 Service 실행 시")
//    class GetStoreListByQueryTest {
//
//        @Test
//        @DisplayName("성공")
//        void getStoreListByQuery_success() {
//            // given
//            Long categoryId = 1L;
//            Long userId = 1L;
//            String query = "순대";
//
//            given(categoryQueryService.getCategory(anyLong())).willReturn(testCategory);
//            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
//            given(tagQueryService.getItemInfosByQuery(any(), any(), anyString())).willReturn(testItems);
//
//            // when
//            StoresResDto result = storeService.getStoreListByQuery(categoryId, userId, query);
//
//            // then
//            assertThat(result).isNotNull();
//            assertThat(result.getCategoryId()).isEqualTo(categoryId);
//            Assertions.assertThat(result.getItems()).hasSize(1);
//
//            then(categoryQueryService).should().getCategory(categoryId);
//            then(userQueryService).should().getUserById(userId);
//            then(tagQueryService).should().getItemInfosByQuery(testCategory, testUser, query);
//        }
//    }
//
//    @Nested
//    @DisplayName("필터로 상점 목록 조회 Service 실행 시")
//    class GetStoreListByFilterTest {
//
//        @Test
//        @DisplayName("성공")
//        void getStoreListByFilter_success() {
//            // given
//            Long categoryId = 1L;
//            Long userId = 1L;
//            List<String> categoryNames = List.of("분식");
//            List<Long> subTagIds = List.of(1L);
//
//            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
//            // getUserById 메소드가 특정 long 값에 관계없이 항상 동일한 결과를 반환하도록 하고 싶을 때
//            given(tagQueryService.mapToStoreInfosByFilter(any(), any(), any())).willReturn(testFilterItems);
//            given(tagQueryService.getSubTagNames(any())).willReturn(testSubTagNames);
//
//            // when
//            StoresFilterResDto result = storeService.getStoreListByFilter(categoryId, userId, categoryNames, subTagIds);
//
//            // then
//            assertThat(result).isNotNull();
//            assertThat(result.getCategoryId()).isEqualTo(categoryId);
//            // categoryNames & subTagNames 사이즈 확인
//            Assertions.assertThat(result.getItems()).hasSize(1);
//
//            then(userQueryService).should().getUserById(userId);
//            then(tagQueryService).should().mapToStoreInfosByFilter(testUser, categoryNames, subTagIds);
//            then(tagQueryService).should().getSubTagNames(subTagIds);
//        }
//    }
//
//    @Nested
//    @DisplayName("모든 상점 목록 조회 Service 실행 시")
//    class GetStoreListAllTest {
//
//        @Test
//        @DisplayName("성공")
//        void getStoreListAll_success() {
//            // given
//            Long categoryId = 1L;
//            Long userId = 1L;
//            Long tagId = 1L;
//            Long subTagId = 1L;
//
//            given(categoryQueryService.getCategory(anyLong())).willReturn(testCategory);
//            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
//            given(tagQueryService.getItemInfosMore(any(), any(), anyLong(), anyLong())).willReturn(testItems);
//
//            // when
//            StoresResDto result = storeService.getStoreListAll(categoryId, userId, tagId, subTagId);
//
//            // then
//            assertThat(result).isNotNull();
//            assertThat(result.getCategoryId()).isEqualTo(categoryId);
//            Assertions.assertThat(result.getItems()).hasSize(1);
//
//            then(categoryQueryService).should().getCategory(categoryId);
//            then(userQueryService).should().getUserById(userId);
//            then(tagQueryService).should().getItemInfosMore(testCategory, testUser, tagId, subTagId);
//        }
//    }
//
//    @Nested
//    @DisplayName("특정 상점 목록 조회 Service 실행 시")
//    class GetStoreTest {
//
//        // TODO: 수정이 가능하면 좀 더 깔끔하게 리팩토링
//        @Test
//        @DisplayName("성공")
//        void getStore_success() {
//            // given
//            Long categoryId = 1L;
//            Long userId = 1L;
//            Long storeId = 1L;
//
//            given(userQueryService.getUserById(anyLong())).willReturn(testUser);
//            given(storeQueryService.getDomain(anyLong())).willReturn(testDomain);
////            given(storeService).buildStoreBasicInfo(any(), any()).willReturn(testStoreBasicInfo);
////            given(storeService).buildStoreDetailInfo(any()).willReturn(testStoreDetailInfo);
//            // private 메소드들이 호출하는 함수들을 직접 모킹해야한다
//            given(tagQueryService.mapToStoreInfo(testDomain, testUser)).willReturn(testStoreBasicInfo);
//            given(storeQueryService.getSubCategory(testDomain)).willReturn("분식");
//            given(storeQueryService.getMenus(testDomain)).willReturn(List.of("순대1", "순대2"));
//            given(storeQueryService.getHashTags(testDomain)).willReturn(List.of("행복1", "행복2"));
//            given(tagQueryService.mapToStoreDetailInfo(
//                    "분식",
//                    testDomain.getAddressDetail(),
//                    List.of("순대1", "순대2"),
//                    List.of("행복1", "행복2")
//            )).willReturn(testStoreDetailInfo);
//
//            // when
//            StoreItemResDto result = storeService.getStore(categoryId, userId, storeId);
//
//            // then
//            assertThat(result).isNotNull();
//            assertThat(result.getCategoryId()).isEqualTo(categoryId);
//            assertThat(result.getStoreBasicInfo()).isEqualTo(testStoreBasicInfo);
//            assertThat(result.getStoreDetailInfo()).isEqualTo(testStoreDetailInfo);
//
//            then(userQueryService).should().getUserById(userId);
//            then(storeQueryService).should().getDomain(storeId);
//            then(tagQueryService).should().mapToStoreInfo(testDomain, testUser);
//            then(storeQueryService).should().getSubCategory(testDomain);
//            then(storeQueryService).should().getMenus(testDomain);
//            then(storeQueryService).should().getHashTags(testDomain);
//            then(tagQueryService).should().mapToStoreDetailInfo(
//                    "분식",
//                    testDomain.getAddressDetail(),
//                    List.of("순대1", "순대2"),
//                    List.of("행복1", "행복2")
//            );
//            // buildStoreBasicInfo(dobong.life.domain.store.Domain, dobong.life.domain.user.User)' has private access in 'dobong.life.domain.store.service.StoreService'
//        }
//
//        @Test
//        @DisplayName("상점이 존재하지 않을 경우 실패")
//        void getStore_storeNotFound() {
//            // given
//            Long storeId = 2L;
//
//            given(storeQueryService.getDomain(storeId)).willThrow(new DomainNotFoundException(storeId));
//
//            // when & then
//            assertThrows(DomainNotFoundException.class,
//                    () -> storeQueryService.getDomain(storeId));
//        }
//    }
//}