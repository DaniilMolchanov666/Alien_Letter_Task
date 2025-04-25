package com.example.alien_letter_service.thymeleaf.dialect;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Кастомный процессор для преобразования Thymeleaf-атрибутов в кастомные XML-атрибуты.
 * <p>
 * Процессор выполняет следующие преобразования:
 * <ul>
 *   <li>Принимает теги с Thymeleaf-атрибутами (например, {@code <theme th:text="${data.theme}"/>})</li>
 *   <li>Вычисляет значение выражения Thymeleaf</li>
 *   <li>Формирует новый XML-тег с вычисленным значением в указанном атрибуте (например, {@code <theme text="Озеленение пустыни"/>})</li>
 * </ul>
 *
 * @author Daniil Molchanov
 * @version 1.0
 *
 * @see AbstractAttributeTagProcessor
 */
public class AlienAttributeProcessor extends AbstractAttributeTagProcessor {

    /**
     * Создает новый процессор для указанного тега и атрибута.
     *
     * @param dialectPrefix префикс диалекта (например, "th")
     * @param tagName имя XML-тега для обработки (например, "theme")
     * @param attributeName имя Thymeleaf-атрибута для обработки (например, "text")
     * @throws IllegalArgumentException если имя атрибута null или пустое
     */
    public AlienAttributeProcessor(String dialectPrefix, String tagName, String attributeName) {
        super(TemplateMode.XML, dialectPrefix, tagName, false, attributeName, true, 1000, false);
    }

    /**
     * Обрабатывает XML-тег, заменяя Thymeleaf-атрибут на обычный XML-атрибут.
     *
     * @param context контекст выполнения Thymeleaf
     * @param tag обрабатываемый XML-тег
     * @param attributeName имя обрабатываемого атрибута
     * @param attributeValue значение атрибута (выражение Thymeleaf)
     * @param structureHandler обработчик структуры документа
     */
    @Override
    protected void doProcess(
            ITemplateContext context,
            IProcessableElementTag tag,
            AttributeName attributeName,
            String attributeValue,
            IElementTagStructureHandler structureHandler) {

        IEngineConfiguration configuration = context.getConfiguration();
        IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        IStandardExpression expression = parser.parseExpression(context, attributeValue);

        String attributeValueText = (String) expression.execute(context);
        String formattedAttributeValue = getFormatAttributeValue(tag, attributeName, attributeValueText);

        structureHandler.replaceWith(formattedAttributeValue, false);
    }


    /**
     * Форматирует значение атрибута в валидный XML-тег.
     *
     * @param tag Тэг
     * @param attribute Thymeleaf-атрибут
     * @param attributeValue значение атрибута
     * @return отформатированная строка XML-тега
     * @throws NullPointerException если любой из параметров null
     */
    private String getFormatAttributeValue(IProcessableElementTag tag, AttributeName attribute, String attributeValue) {
        String tagName = tag.getElementCompleteName();
        String attributeName = attribute.getAttributeName();
        return String.format("<%s %s=\"%s\"/>", tagName, attributeName, attributeValue);
    }
}