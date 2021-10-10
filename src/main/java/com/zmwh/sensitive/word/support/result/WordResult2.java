package com.zmwh.sensitive.word.support.result;

import com.zmwh.sensitive.word.api.IWordResult;

/**
 * @author dmzmwh
 * @since 1.0.1
 */
public class WordResult2 implements IWordResult {

    private String word;
    private Integer type;


    public static WordResult2 newInstance() {
        return new WordResult2();
    }

    @Override
    public String word() {
        return word;
    }


    public WordResult2 word(String word) {
        this.word = word;
        return this;
    }

    public int type() {
        return type;
    }

    public WordResult2 type(int type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return "WordResult{" +
                "word='" + word + '\'' +
                ", type=" + type +
                '}';
    }
}
