package perhaps.love.im.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import perhaps.love.im.entity.SessionType;

@Converter(autoApply = true)
public class SessionTypeConverter implements AttributeConverter<SessionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SessionType attribute) {
        return attribute.getCode();
    }

    @Override
    public SessionType convertToEntityAttribute(Integer dbData) {
        return SessionType.valueOfCode(dbData);
    }

}
