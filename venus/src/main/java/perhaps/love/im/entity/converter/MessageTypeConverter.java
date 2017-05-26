package perhaps.love.im.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import perhaps.love.im.entity.MessageType;

@Converter(autoApply = true)
public class MessageTypeConverter implements AttributeConverter<MessageType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MessageType attribute) {
        return attribute.getCode();
    }

    @Override
    public MessageType convertToEntityAttribute(Integer dbData) {
        return MessageType.valueOfCode(dbData);
    }

}
