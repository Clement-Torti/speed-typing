package com.example.speed_typing.model;

public class Word {
    String text;

    protected Word (String txt){
        text = txt;
    }

    @Override
    public String toString() {
        return text;
    }
}
