package pl.niepracuj.model.dto.advertisement;

import lombok.*;
import pl.niepracuj.model.dto.SkillCreateDto;
import pl.niepracuj.model.enums.SeniorityEnum;
import pl.niepracuj.model.enums.TechnologyEnum;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementSearchCriteriaDto {


    private String name;

    private Long salaryFrom;

    private Long salaryTo;

    private String companyName;

    private TechnologyEnum technologyName;

    private SeniorityEnum seniorityName;

    private String cityName;

}