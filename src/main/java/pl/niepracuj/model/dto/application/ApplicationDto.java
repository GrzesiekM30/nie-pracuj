package pl.niepracuj.model.dto.application;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDto {

    private Long id;

    private String name;

    private String surname;

    private String email;

    private String introduction;

    private String path;

}