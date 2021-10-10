package com.zmwh.sensitive.word.support.result;

import com.zmwh.sensitive.word.api.IWordResult;
import com.zmwh.sensitive.word.api.IWordResultHandler;

/**
 * 只保留单词
 *
 * @author binbin.hou
 * @since 0.1.0
 */
public class WordResultHandlerWord implements IWordResultHandler<String> {

    @Override
    public String handle(IWordResult wordResult) {
        if(wordResult == null) {
            return null;
        }
        return wordResult.word();
    }
    
}
