/**
 * ClassName:
 * Description:
 * Date:           2021 2021/7/29 13:50
 * Author:         Lin
 * Copyright:      Lin
 */


package com.ml.timi;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
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

    public static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
        //序列化
        @Override
        public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }

        //反序列化
        @Override
        public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            //下面这个也行，并且当时间带时区时就必须用下面这个
            //return ZonedDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString()).toLocalDateTime();
        }

    }

    public static class Student {
        private String FENSHUT;
        private String OA;

        public Student(String FENSHUT, String OA) {
            super();
            this.FENSHUT = FENSHUT;
            this.OA = OA;
        }


       /* @Override
        public String toString() {
            return "User [FENSHUT=" + FENSHUT + ", OA=" + OA + "]";
        }*/

    }

    public static class User {

        private String id;
        private String name;
        private int age;
        private String address;
        private List<Student> students;
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime localDateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String id, String name, int age, String address, List students, LocalDateTime localDateTime) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
            this.students = students;
            this.localDateTime = localDateTime;
        }

        public User(String id, String name, int age, String address, LocalDateTime localDateTime) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
            // this.students = students;
            this.localDateTime = localDateTime;
        }
        public User(String id, String name, int age, String address,List students) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
            this.students = students;

        }

        public User(String id, String name, int age, String address) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
            // this.students = students;

        }

        @Override
        public String toString() {
            return "User [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", LocalDateTime=" + localDateTime + ", S=" + students + "]";
        }

    }


    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student("99", "oa1");

        Student s2 = new Student("1000", "oa22");
        students.add(s1);
        students.add(s2);

        User user = new User("\'1\'", null, 25, "山西太原",students);
        Gson gson = new GsonBuilder()
                .setPrettyPrinting() //格式化输出的json
                .serializeNulls()    //有NULL值是也进行解析
                .disableHtmlEscaping()  //解析特殊符号
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())   //序列化LocalDateTime的解析
                .registerTypeAdapter(JsonElement.class, new LocalDateTimeAdapter())     //反序列化LocalDateTime(String——>LocalDateTime)的解析
                .create();
        String json = gson.toJson(user);
        System.out.println(json);


        User user1 = gson.fromJson(json, User.class);
        System.out.println("json转为对象：" + user1);


    }
}
