package dobong.life.base.store.service;

import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.controller.response.StoresResDTO;
import dobong.life.base.store.dto.StoresDTO;
import dobong.life.base.store.exception.CategoryNotFoundException;
import dobong.life.base.store.exception.DomainNotFoundException;
import dobong.life.base.store.service.query.CategoryQueryService;
import dobong.life.base.store.service.query.DomainQueryService;
import dobong.life.base.store.service.query.HashTagQueryService;
import dobong.life.base.store.support.StoreFixture;
import dobong.life.base.user.service.query.UserQueryService;
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

    @BeforeEach
    void setUp(){
        testStoreDTOList = StoreFixture.domainList();

    }

    @Nested
    @DisplayName("홈화면(상정 목록 조회) 서비스 실행 시 ")
    class GetStoreListTest{
        @Test
        @DisplayName(":성공")
        void getStoreList_success(){
            // given
            Long userId = 1L;
            String category = StoreFixture.CATEGORY_NAME;
            List<String> categories = Arrays.asList(category);

            given(categoryQueryService.getAllCategory()).willReturn(categories);
            given(domainQueryService.findByCategoryName(category, 3, 0)).willReturn(testStoreDTOList);

            // when
            StoresResDTO result = storeService.getStoreList(userId);

            // then
            assertThat(result).isNotNull();
            assertThat(result.getStoresDTOList().get(0)
                    .getCategoryName()).isEqualTo(category);

            assertThat(result.getStoresDTOList().get(0).getItems().get(0).getName())
                    .isEqualTo(testStoreDTOList.get(0).getName());
        }

        @Test
        @DisplayName("카테고리가 존재하지 않을 경우:실패")
        void getStoreList_categoryNotFound(){
            // given
            Long userId = 1L;
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
            Long userId = 1L;
            String category = StoreFixture.CATEGORY_NAME;
            List<String> categories = Arrays.asList(category);

            given(categoryQueryService.getAllCategory()).willReturn(categories);
            given(domainQueryService.findByCategoryName(category, 3, 0)).willThrow(new DomainNotFoundException(BaseErrorCode.DOMAIN_NOT_FOUND,
                    "[ERROR] " + category + "에 해당하는 상점을 찾을 수 없습니다"
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

        }

        @Test
        @DisplayName("카테고리에 해당하는 해시태그가 없을 경우:실패")
        void getStoreListByCategory_hashTagNotFound(){

        }

        @Test
        @DisplayName("해시태그에 해당하는 상점이 없을 경우:실패")
        void getStoreListByCategory_storeNotFound(){

        }
    }

    @Nested
    @DisplayName("검색어에 따른 상점 조회 서비스 실행 시 ")
    class GetSearchStoreListTest{
        @Test
        @DisplayName(":성공")
        void getSearchStoreList_success(){

        }

        @Test
        @DisplayName("검색어에 해당하는 상점이 없을 경우:실패")
        void getSearchStoreList_storeNotFound(){

        }
    }

    @Nested
    @DisplayName("찜목록 서비스 실행 시 ")
    class GetStoreLikeTest{
        @Test
        @DisplayName(":성공")
        void getStoreLike_success(){

        }

        @Test
        @DisplayName("찜목록에 해당하는 상점이 없을 경우:실패")
        void getStoreLike_storeNotFound(){

        }
    }

    @Nested
    @DisplayName("상점 좋아요(찜) 서비스 실행 시 ")
    class UpdateStoreLikeTest{
        @Test
        @DisplayName(":성공")
        void updateStoreLike_success(){

        }

        @Test
        @DisplayName("좋아요를 요청한 사용자가 없을 경우:실패")
        void updateStoreLike_userNotFound(){

        }

        @Test
        @DisplayName("좋아요를 요청한 상점이 없을 경우:실패")
        void updateStoreLike_storeNotFound(){

        }
    }



}