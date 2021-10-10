package com.zmwh.sensitive.word.support.format;

import com.github.houbb.opencc4j.core.impl.ZhConvertBootstrap;
import com.github.houbb.opencc4j.support.segment.impl.CharSegment;
import com.zmwh.sensitive.word.api.ICharFormat;
import com.zmwh.sensitive.word.api.IWordContext;

/**
 * 忽略大小写
 * @author binbin.hou
 * @since 0.0.5
 */
public class IgnoreChineseStyleFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        String string = String.valueOf(original);
        String simple = ZhConvertBootstrap.newInstance(new CharSegment()).toSimple(string);
        return simple.charAt(0);
    }

}
