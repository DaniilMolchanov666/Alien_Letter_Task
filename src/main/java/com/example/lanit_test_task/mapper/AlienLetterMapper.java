package com.example.lanit_test_task.mapper;

import com.example.lanit_test_task.model.AlienLetter;
import com.example.lanit_test_task.dto.FormattedAlienLetterDto;
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
     * Преобразует AlienLetter в FormattedAlienLetterDto
     *
     * @param alienLetter исходное письмо
     * @return форматированное DTO письма
     */
    @Mapping(target = "title", source = "raceCode.value")
    @Mapping(target = "uid", source = "uid.code.value")
    @Mapping(target = "authors", source = "authors")
    @Mapping(target = "address", source = "document.address.value")
    @Mapping(target = "phone", source = "document.telephone.value")
    @Mapping(target = "createdAt", source = "created.dateTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @Mapping(target = "listOfParagraphs", source = "document.text")
    public abstract FormattedAlienLetterDto toAlienLetterDto(AlienLetter alienLetter);
}