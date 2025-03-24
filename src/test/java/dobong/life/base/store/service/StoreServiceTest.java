package dobong.life.base.store.service;

import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.Tag;
import dobong.life.base.store.controller.response.StoreLikeResDTO;
import dobong.life.base.store.controller.response.StoresByIdResDTO;
import dobong.life.base.store.controller.response.StoresByQueryResDTO;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.dto.StoresDTO;
import dobong.life.base.store.exception.CategoryNotFoundException;
import dobong.life.base.store.exception.DomainNotFoundException;
import dobong.life.base.store.exception.HashTagNotFoundException;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.store.service.query.HashTagQueryService;
import dobong.life.base.store.support.StoreFixture;
import dobong.life.base.user.User;
import dobong.life.base.user.exception.UserNotFoundException;
import dobong.life.base.user.service.query.UserQueryService;
import dobong.life.global.auth.support.AuthFixture;
import dobong.life.global.util.response.status.BaseCode;
import dobong.life.global.util.response.status.BaseErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StoreServiceTest {

    @InjectMocks
    private StoreService storeService;
    @Mock
    private CategoryQueryService categoryQueryService;
    @Mock
    private HashTagQueryService hashTagQueryService;
    @Mock
    private DomainQueryService domainQueryService;
    @Mock
    private UserQueryService userQueryService;
    private List<Domain> testStoreDTOList;
    private Long userId = 1L;
    private Long storeId = 1L;
    private User testUser;
    private String categoryName;
    private String  hashTag;

    @BeforeEach
    void setUp(){
        testStoreDTOList = StoreFixture.domainList();
        categoryName = StoreFixture.CATEGORY_NAME;
        hashTag = StoreFixture.HASH_TAG;
        testUser = AuthFixture.user();
    }

    @Nested
    @DisplayName("홈화면(상점 목록 조회) 서비스 실행 시 ")
    class GetStoreListTest{
        @Test
        @DisplayName(":성공")
        void getStoreList_success(){
            // given
            List<String> categories = Arrays.asList(categoryName);

            given(categoryQueryService.getAllCategory()).willReturn(categories);
            given(domainQueryService.findByCategoryName(categoryName, 3, 0)).willReturn(testStoreDTOList);

            // when
            StoresResDTO result = storeService.getStoreList(userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getStoresDTOList().get(0)
                    .getCategoryName()).isEqualTo(categoryName);

            assertThat(result.getStoresDTOList().get(0).getItems().get(0).getName())
                    .isEqualTo(testStoreDTOList.get(0).getName());
        }

        @Test
        @DisplayName("카테고리가 존재하지 않을 경우:실패")
        void getStoreList_categoryNotFound(){
            // given
            given(categoryQueryService.getAllCategory()).willThrow(new CategoryNotFoundException(BaseErrorCode.CATEGORY_NOT_FOUND,
                    "[ERROR] 저장되어있는 카테고리가 없습니다"));

            // when & then
            assertThrows(CategoryNotFoundException.class,
                    () -> storeService.getStoreList(userId));

        }

        @Test
        @DisplayName("카테고리에 해당하는 상점이 없을 경우:실패")
        void getStoreList_storeNotFound(){
            // given
            List<String> categories = Arrays.asList(categoryName);

            given(categoryQueryService.getAllCategory()).willReturn(categories);
            given(domainQueryService.findByCategoryName(categoryName, 3, 0)).willThrow(new DomainNotFoundException(BaseErrorCode.DOMAIN_NOT_FOUND,
                    "[ERROR] " + categoryName + "에 해당하는 상점을 찾을 수 없습니다"
            ));

            // when & then
            assertThrows(DomainNotFoundException.class,
                    () -> storeService.getStoreList(userId));
        }
    }

    @Nested
    @DisplayName("카테고리에 따른 상점 조회 서비스 실행 시 ")
    class GetStoreListByCategoryTest{
        @Test
        @DisplayName(":성공")
        void getStoreListByCategory_success(){
            // given
            List<String> categories = Arrays.asList(categoryName);
            List<Tag> hashTags = Arrays.asList(StoreFixture.tag());

            given(hashTagQueryService.getAllTag(categoryName)).willReturn(hashTags);
            given(domainQueryService.findByCategoryName(categoryName, 8, 0)).willReturn(testStoreDTOList);
            given(domainQueryService.getPageSize(categoryName)).willReturn(1);
            given(domainQueryService.findByHashTag(hashTag)).willReturn(testStoreDTOList);

            // when
            StoresByIdResDTO result = storeService.getStoreListByCategory(categoryName, userId, 0);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getCategoryName()).isEqualTo(categoryName);

            assertThat(result.getHashTagDTOList().get(0).getHashTagName())
                    .isEqualTo(hashTag);
            assertThat(result.getHashTagDTOList().get(0).getItems().get(0).getName())
                    .isEqualTo(testStoreDTOList.get(0).getName());
        }

        @Test
        @DisplayName("카테고리에 해당하는 해시태그가 없을 경우:실패")
        void getStoreListByCategory_hashTagNotFound(){
            // given
            given(hashTagQueryService.getAllTag(categoryName)).willThrow(new HashTagNotFoundException(BaseErrorCode.HASHTAG_NOT_FOUND,
                    "[ERROR] "+categoryName+"에 해당되는 태그가 없습니다"));

            // when & then
            assertThrows(HashTagNotFoundException.class,
                    () -> storeService.getStoreListByCategory(categoryName, userId, 0));
        }

        @Test
        @DisplayName("해시태그에 해당하는 상점이 없을 경우:실패")
        void getStoreListByCategory_storeNotFound(){
            // given
            List<Tag> hashTags = Arrays.asList(StoreFixture.tag());
            given(hashTagQueryService.getAllTag(categoryName)).willReturn(hashTags);
            given(domainQueryService.findByHashTag(hashTag)).willThrow(new DomainNotFoundException
                    (BaseErrorCode.DOMAIN_NOT_FOUND, "[ERROR] " + hashTag + "에 해당하는 상점을 찾을 수 없습니다"));
            // when & then
            assertThrows(DomainNotFoundException.class,
                    () -> storeService.getStoreListByCategory(categoryName, userId, 0));

        }
    }

    @Nested
    @DisplayName("검색어에 따른 상점 조회 서비스 실행 시 ")
    class GetSearchStoreListTest{
        @Test
        @DisplayName(":성공")
        void getSearchStoreList_success(){
            // given
            String query = StoreFixture.QUERY;
            List<String> filter = StoreFixture.FILTER;
            given(domainQueryService.findByQueryAndFilter(query, filter)).willReturn(testStoreDTOList);

            // when
            StoresByQueryResDTO result = storeService.searchStoreList(userId, query, filter);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getFilter().size()).isEqualTo(filter.size());
            assertThat(result.getQuery()).isEqualTo(query);
            assertThat(result.getItemDTOList().get(0).getName())
                    .isEqualTo(testStoreDTOList.get(0).getName());
        }

        @Test
        @DisplayName("검색어에 해당하는 상점이 없을 경우:실패")
        void getSearchStoreList_storeNotFound(){
            // given
            String query = StoreFixture.QUERY;
            List<String> filter = StoreFixture.FILTER;
            given(domainQueryService.findByQueryAndFilter(query,filter)).willThrow(new DomainNotFoundException(BaseErrorCode.DOMAIN_NOT_FOUND,
                    "[ERROR] 검색한 상점을 찾을 수 없습니다"));

            // when & then
            assertThrows(DomainNotFoundException.class,
                    ()-> storeService.searchStoreList(userId, query, filter));
        }
    }

    @Nested
    @DisplayName("찜목록 서비스 실행 시 ")
    class GetStoreLikeTest{
        @Test
        @DisplayName(":성공")
        void getStoreLike_success(){
            // given
            given(domainQueryService.findByUserId(userId)).willReturn(testStoreDTOList);

            // when
            StoreLikeResDTO result = storeService.getStoreLikeList(userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getItemDTOList().get(0).getName())
                    .isEqualTo(testStoreDTOList.get(0).getName());
        }

        @Test
        @DisplayName("찜목록에 해당하는 상점이 없을 경우:실패")
        void getStoreLike_storeNotFound(){
            // given
            given(domainQueryService.findByUserId(userId)).willThrow(new DomainNotFoundException(BaseErrorCode.DOMAIN_NOT_FOUND,
                    "[ERROR] 사용자가 찜한 목록이 없습니다"));

            // when & then
            assertThrows(DomainNotFoundException.class,
                    ()-> storeService.getStoreLikeList(userId));
        }
    }

}