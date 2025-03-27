package dobong.life.base.store.support;

import dobong.life.base.review.Review;
import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.Tag;
import dobong.life.base.store.controller.response.*;
import dobong.life.base.store.dto.*;
import dobong.life.global.auth.support.AuthFixture;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StoreFixture {

    // TODO: fixture 범위, 상수값 접근제한범위 설정 (고민) , BASECODE는 바로 써도 되는지
    public static final int CURRENT_PAGE = 1;
    public static final int PAGE_SIZE = 1;
    public static final String CATEGORY_NAME = "음식";
    public static final String ADDRESS = "도봉동";
    public static final String STORE_NAME = "마선생마약국밥";
    public static final String HASH_TAG = "소소한 한 끼가 필요할 때";
    public static final List<String> CATEGORIES = Arrays.asList("음식", "한식", "국밥");
    public static final List<String> KEYWORDS = Arrays.asList("맛있어요", "좋아요", "근사해요");

    public static final String QUERY = "순대";
    public static final List<String> FILTER = Arrays.asList("한식", "국밥");

    public static StoresResDTO storesResDTO(){
        return StoresResDTO.builder()
                .storesDTOList(storesDTOList())
                .build();
    }

    public static StoresByIdResDTO storesByIdResDTO(){
        return StoresByIdResDTO.builder()
                .currentPage(CURRENT_PAGE)
                .pageSize(PAGE_SIZE)
                .categoryName(CATEGORY_NAME)
                .hashTagDTOList(hashTagDTOList())
                .etcList(itemDTOList())
                .build();
    }

    public static StoresByQueryResDTO storesByQueryResDTO(){
        return StoresByQueryResDTO.builder()
                .query(QUERY)
                .filter(FILTER)
                .itemDTOList(itemDTOList())
                .build();
    }

    public static StoreLikeResDTO storeLikeResDTO(){
        return StoreLikeResDTO.builder()
                .itemDTOList(itemDTOList())
                .build();
    }

    private static List<HashTagDTO> hashTagDTOList() {
        List<HashTagDTO> list = Arrays.asList(new HashTagDTO(HASH_TAG, itemDTOList()));
        return list;
    }

    private static List<StoresDTO> storesDTOList() {
        List<StoresDTO> list = Arrays.asList(new StoresDTO(CATEGORY_NAME, itemDTOList()));
        return list;
    }

    private static List<ItemDTO> itemDTOList() {
        List<ItemDTO> list = Arrays.asList(itemDTO());
        return list;
    }

    private static ItemDTO itemDTO() {
        return ItemDTO.builder()
                .address(ADDRESS)
                .like(false)
                .name(STORE_NAME)
                .categories(CATEGORIES)
                .build();
    }

    public static Category category() {
        return Category.builder()
                .id(1L)
                .categoryName("음식")
                .subCategoryName("한식")
                .name("국밥")
                .build();
    }

    public static List<Domain> domainList() {
        List<Domain> list = Arrays.asList(domain());
        return list;
    }

    public static Domain domain(){
        return new Domain(1L, STORE_NAME, category());
    }

    public static Tag tag() {
        return Tag.builder()
                .id(1L)
                .hashtagName(HASH_TAG)
                .build();
    }

    public static StoreResDTO storeResDTO() {
        return StoreResDTO.builder()
                .itemDetailDTO(itemDetailDTO())
                .reviewsDTO(reviewsDTO())
                .build();
    }

    private static ReviewsDTO reviewsDTO() {
        return ReviewsDTO.builder()
                .keywords(KEYWORDS)
                .reviewCount(5)
                .averageRating(3.84)
                .reviewDTOList(reviewDTOList())
                .build();
    }

    private static List<ReviewDTO> reviewDTOList() {
        List<ReviewDTO> list = Arrays.asList(reviewDTO());
        return list;
    }

    private static ReviewDTO reviewDTO() {
        return ReviewDTO.builder()
                .reviewId(1L)
                .userName("홍길동")
                .build();
    }

    private static ItemDetailDTO itemDetailDTO() {
        return ItemDetailDTO.builder()
                .name(STORE_NAME)
                .address(ADDRESS)
                .categories(CATEGORIES)
                .startTime(LocalTime.parse("09:00:00")) // LocalTime 객체로 변환
                .endTime(LocalTime.parse("18:00:00"))   // LocalTime 객체로 변환
                .day("월")
                .description("")
                .like(false)
                .build();
    }

    public static List<Review> reviewList(){
        List<Review> list = Arrays.asList(review());
        return list;
    }

    public static Review review() {
        return Review.builder()
                .user(AuthFixture.user())
                .domain(domain())
                .build();
    }
}
