package pl.niepracuj.service.advertisement;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import pl.niepracuj.model.dto.advertisement.AdvertisementSearchCriteriaDto;
import pl.niepracuj.model.entity.Advertisement;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

@RequiredArgsConstructor
public class AdvertisementSpecification implements Specification<Advertisement> {

    private final AdvertisementSearchCriteriaDto criteriaDto;

    @Override
    public Predicate toPredicate(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(advertisementNameEquals(root, query, criteriaBuilder),
                seniorityNameEquals(root, query, criteriaBuilder));
    }

    private Predicate advertisementNameEquals(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Objects.nonNull(criteriaDto.getName()) ?
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + criteriaDto.getName().toLowerCase() + "%") :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate seniorityNameEquals(Root<Advertisement> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Objects.nonNull(criteriaDto.getSeniorityName()) ?
                criteriaBuilder.equal(root.get("seniority").get("name") , criteriaDto.getSeniorityName()) :
                alwaysTruePredicate(criteriaBuilder);
    }

    private Predicate alwaysTruePredicate(CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }
}
