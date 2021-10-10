package com.zmwh.sensitive.word.support.format;

import com.zmwh.sensitive.word.api.ICharFormat;
import com.zmwh.sensitive.word.api.IWordContext;

/**
 * 忽略大小写
 * @author binbin.hou
 * @since 0.0.5
 */
public class IgnoreCaseCharFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return Character.toLowerCase(original);
    }

}
