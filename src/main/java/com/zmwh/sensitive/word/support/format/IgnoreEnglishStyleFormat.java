package com.zmwh.sensitive.word.support.format;

import com.zmwh.sensitive.word.api.ICharFormat;
import com.zmwh.sensitive.word.api.IWordContext;
import com.zmwh.sensitive.word.utils.CharUtils;

/**
 * 忽略英文的各种格式
 * @author binbin.hou
 * @since 0.0.6
 */
public class IgnoreEnglishStyleFormat implements ICharFormat {

    @Override
    public char format(char original, IWordContext context) {
        return CharUtils.getMappingChar(original);
    }

}
