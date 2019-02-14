package com.example.speed_typing.model;

public class Word {
    private String text;

    protected Word (String txt){
        text = txt;
    }

    public String getText() { return text; }


    @Override
    public String toString() {
        return text;
    }

    /*
    * 2 mots sont identiques s'ils ont le même contenu
     */
    @Override
    public boolean equals(Object o) {
        if(o != null && o.getClass() == this.getClass()) {
            Word w = (Word) o;
            return w.text.equals(this.text);
        }

        return false;
    }
}
