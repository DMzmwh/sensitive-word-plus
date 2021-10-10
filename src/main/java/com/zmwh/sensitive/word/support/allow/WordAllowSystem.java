package com.zmwh.sensitive.word.support.allow;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.zmwh.sensitive.word.api.IWordAllow;

import java.util.List;

/**
 * 系统默认的信息
 * @author binbin.hou
 * @since 0.0.13
 */
public class WordAllowSystem implements IWordAllow {

    @Override
    public List<String> allow() {
        return StreamUtil.readAllLines("/sensitive_word_allow.txt");
    }

}
