package com.zmwh.sensitive.word.support.result;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.zmwh.sensitive.word.api.IWordResult;
import com.zmwh.sensitive.word.api.IWordResultHandler;

/**
 * 不做任何处理
 * @author binbin.hou
 * @since 0.1.0
 */
@ThreadSafe
public class WordResultHandlerRaw implements IWordResultHandler<IWordResult> {

    @Override
    public IWordResult handle(IWordResult wordResult) {
        return wordResult;
    }

}
