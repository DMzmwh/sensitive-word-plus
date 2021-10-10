package com.zmwh.sensitive.word.support.result;

import com.zmwh.sensitive.word.api.IWordResult;

/**
 * @author binbin.hou
 * @since 0.1.0
 */
public class WordResult implements IWordResult {

    private String word;
    /**
     * 开始下标
     * @return 开始下标
     * @since 0.1.0
     */
    private int startIndex;
    /**
     * 结束下标
     * @return 结束下标
     * @since 0.1.0
     */
    private int endIndex;

    public static WordResult newInstance() {
        return new WordResult();
    }

    @Override
    public String word() {
        return word;
    }

    public WordResult word(String word) {
        this.word = word;
        return this;
    }

    public int startIndex() {
        return startIndex;
    }

    public WordResult startIndex(int startIndex) {
        this.startIndex = startIndex;
        return this;
    }

    public int endIndex() {
        return endIndex;
    }

    public WordResult endIndex(int endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    @Override
    public String toString() {
        return "WordResult{" +
                "word='" + word + '\'' +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                '}';
    }

}
