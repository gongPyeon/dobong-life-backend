package dobong.life.base.user.service;

import dobong.life.base.review.Review;
import dobong.life.base.review.service.query.MiddleQueryService;
import dobong.life.base.review.service.query.ReviewQueryService;
import dobong.life.base.store.Category;
import dobong.life.base.store.Domain;
import dobong.life.base.store.dto.ItemDTO;
import dobong.life.base.store.dto.ReviewDTO;
import dobong.life.base.user.Setting;
import dobong.life.base.user.controller.response.*;
import dobong.life.base.user.User;
import dobong.life.base.user.service.query.UserQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MyPageService {

    private final UserQueryService userQueryService;
    private final ReviewQueryService reviewQueryService;
    private final MiddleQueryService middleQueryService;
    private final Setting setting;

    public MyPageResDTO getMyPage(Long userId) {
        User user = userQueryService.getUserById(userId);
        return new MyPageResDTO(user.getNickName(), user.getImgUrl());
    }

    public MyPageReviewResDTO getMyReview(Long userId) {
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        List<Review> reviews = reviewQueryService.findByUserId(userId);

        for(Review review : reviews){
            Long reviewId = review.getId();
            String userName = review.getUser().getNickName();
            LocalDateTime reviewDate = review.getDate();
            String reviewContent = review.getContent();
            List<String> selectedKeywords = middleQueryService.getKeywords(review);
            boolean likeByUser = reviewQueryService.likeByUser(review.getUser(), review);
            int likeCount = review.getLikeCount();
            reviewDTOList.add(new ReviewDTO(reviewId, userName, reviewDate, reviewContent, selectedKeywords, likeByUser, likeCount));
        }
        return new MyPageReviewResDTO(reviewDTOList);
    }

    public ContactUsDTO getContactUsDTO() {
        return new ContactUsDTO(setting.getContactUs());
    }


    public TermsOfServiceDTO getTermsOfServiceDTO() {
        return new TermsOfServiceDTO(setting.getTermsOfService());
    }

    public PrivacyPolicyDTO getPrivacyPolicyDTO() {
        return new PrivacyPolicyDTO(setting.getPrivacyPolicy());
    }

    public OpenSourceLicensesDTO getOpenSourceLicensesDTO() {
        return new OpenSourceLicensesDTO(setting.getOpenSourceLicenses());
    }
}
