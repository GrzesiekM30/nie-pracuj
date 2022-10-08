package pl.niepracuj.model.dto.advertisement;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.niepracuj.model.dto.SkillCreateDto;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementCreateDto {

    @Size(max = 250)
    @NotBlank(message = "Name is required")
    private String name;

    @Future(message = "Expire date can't be less than current date")
    @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
    private LocalDateTime expireDate;

    @NotNull(message = "SalaryFrom is mandatory")
    @Min(value = 1, message = "SalaryFrom must be 1 or more")
    private Long salaryFrom;

    @NotNull(message = "SalaryTo is mandatory")
    @Min(value = 1, message = "SalaryFrom must be 0 or more")
    private Long salaryTo;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Company is mandatory")
    private Long companyId;

    @NotNull(message = "Technology is mandatory")
    private Long technologyId;

    @NotNull(message = "Seniority is mandatory")
    private Long seniorityId;

    @NotNull(message = "City id is mandatory")
    private Long cityId;

    private Set<SkillCreateDto> skills;
}