package com.zmwh.sensitive.word.support.format;

import com.github.houbb.heaven.util.lang.CharUtil;
import com.zmwh.sensitive.word.api.ICharFormat;
import com.zmwh.sensitive.word.api.IWordContext;

/**
 * 格式化责任链
 * @author binbin.hou
 * @since 0.0.5
 */
public class IgnoreWidthCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return CharUtil.toHalfWidth(original);
    }

}
