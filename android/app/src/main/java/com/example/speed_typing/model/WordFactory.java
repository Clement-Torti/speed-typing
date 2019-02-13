package com.example.speed_typing.model;

import android.content.Context;

public class WordFactory {
    public static WordDatabase createWordDatabase(Context context, WordType wordType) {

        switch(wordType) {
            case Francais: return new WordDatabase("Francais.txt", context);
            case Anglais: return new WordDatabase("Anglais.txt", context);
        }

        return new WordDatabase("Francais.txt", context);
    }


}
