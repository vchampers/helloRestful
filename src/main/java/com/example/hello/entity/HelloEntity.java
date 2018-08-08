package com.example.hello.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HelloEntity {
    private String name;
    private String currentTime;

    public HelloEntity(String name, String currentTime){
        this.setName(name);
        this.setCurrentTime(currentTime);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "name: '" + name + '\'' +
//                ", currentTime: '" + currentTime + '\'' +
//                '}';
//    }
}
