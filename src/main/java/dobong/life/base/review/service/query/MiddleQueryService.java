package dobong.life.base.review.service.query;

import dobong.life.base.review.Middle;
import dobong.life.base.review.repository.MiddleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiddleQueryService {
    private final MiddleRepository middleRepository;

    public List<String> findAllBy(){
        return middleRepository.findAllBy();
    }

    @Transactional
    public void saveMiddle(List<Middle> middles) {
        middleRepository.saveAll(middles);
    }
}
