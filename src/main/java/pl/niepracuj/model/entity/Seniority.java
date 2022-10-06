package pl.niepracuj.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import pl.niepracuj.model.enums.SeniorityEnum;

import javax.persistence.*;

@Table(name = "seniorities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seniority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private SeniorityEnum name;
}