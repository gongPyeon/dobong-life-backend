package dobong.life.base.user.controller.response;

import dobong.life.base.store.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Getter
public class MyPageReviewResDTO {
    List<ReviewDTO> reviewDTOList;
}
