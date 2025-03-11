package dobong.life.base.store.service.query;

import dobong.life.base.store.Domain;
import dobong.life.base.store.repository.DomainLikeRepository;
import dobong.life.base.store.repository.DomainRepository;
import dobong.life.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DomainQueryService {
    private final DomainRepository domainRepository;
    private final DomainLikeRepository domainLikeRepository;
    private static final int MAX = 3;

    public List<Domain> findByCategoryId(Long categoryId){
        return domainRepository.findByCategoryId(categoryId).stream()
                .limit(MAX)
                .collect(Collectors.toList());
    }

    public boolean getLikeByUser(Domain domain, Long userId) {
        return domainLikeRepository.findByDomainAndUser(domain, userId).isPresent();
    }

    public List<Domain> findByQueryAndFilter(String query, List<String> filter) {
        return domainRepository.findByQueryAndFilter(query, filter).stream()
                .collect(Collectors.toList());
    }
}
