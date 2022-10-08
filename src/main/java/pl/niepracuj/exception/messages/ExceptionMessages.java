package pl.niepracuj.exception.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessages {

    ENTITY_FOR_PROVIDED_ID_NOT_FOUND("Encja dla podanego id nie istnieje");

    private String message;
}
