package com.example.alien_letter_service.mapper;

import com.example.alien_letter_service.dto.FormattedAlienLetterDto;
import com.example.alien_letter_service.model.AlienLetter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Маппер для преобразования сущности {@link AlienLetter} в {@link FormattedAlienLetterDto}.
 * Использует {@link LetterContentFormatter} для форматирования данных.
 *  * <p>Настройки маппера:
 *  * <ul>
 *  *   <li>Использует Spring-компоненты</li>
 *  *   <li>Возвращает пустые коллекции вместо null</li>
 *  *   <li>Игнорирует неуказанные поля</li>
 *  * </ul>
 *
 * @author Daniil Molchanov
 * @version 1.0
 */
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = LetterContentFormatter.class
)
public abstract class AlienLetterMapper {

    @Autowired
    protected LetterContentFormatter letterContentConverter;

    /**
     * Преобразует AlienLetter в FormattedAlienLetterDto.
     *
     * @param alienLetter исходное письмо
     * @return форматированное DTO письма
     */
    @Mapping(target = "title", source = "raceCode.value")
    @Mapping(target = "uid", source = "uid.code.value")
    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "address", source = "document.address.value")
    @Mapping(target = "phone", source = "document.telephone.value", qualifiedByName = "formatPhone")
    @Mapping(target = "createdAt", source = "created.dateTime", qualifiedByName = "formatAndIncrementDate")
    @Mapping(target = "listOfParagraphs", source = "document.text", qualifiedByName = "formatParagraphs")
    public abstract FormattedAlienLetterDto toAlienLetterDto(AlienLetter alienLetter);
}