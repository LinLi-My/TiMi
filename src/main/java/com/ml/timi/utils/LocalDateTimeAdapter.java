/**
 * ClassName:
 * Description:
 * Date:           2021 2021/8/17 17:10
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    //序列化
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    //反序列化
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        //下面这个也行，并且当时间带时区时就必须用下面这个
        //return ZonedDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString()).toLocalDateTime();
    }
}
