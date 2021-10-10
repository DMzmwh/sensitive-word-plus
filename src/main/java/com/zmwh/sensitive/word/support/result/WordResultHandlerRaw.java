package com.zmwh.sensitive.word.support.result;

import com.zmwh.sensitive.word.api.IWordResult;
import com.zmwh.sensitive.word.api.IWordResultHandler;

/**
 * 不做任何处理
 * @author binbin.hou
 * @since 0.1.0
 */
public class WordResultHandlerRaw implements IWordResultHandler<IWordResult> {

    @Override
    public IWordResult handle(IWordResult wordResult) {
        return wordResult;
    }

}
