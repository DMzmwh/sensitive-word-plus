package com.zmwh.sensitive.word.support.format;

import com.zmwh.sensitive.word.api.ICharFormat;
import com.zmwh.sensitive.word.api.IWordContext;
import com.zmwh.sensitive.word.utils.NumUtils;

/**
 * 忽略数字的样式
 * @author binbin.hou
 * @since 0.0.5
 */
public class IgnoreNumStyleCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return NumUtils.getMappingChar(original);
    }

}
