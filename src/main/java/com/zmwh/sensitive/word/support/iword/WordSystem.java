package com.zmwh.sensitive.word.support.iword;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.zmwh.sensitive.word.api.IWord;
import com.zmwh.sensitive.word.api.IWordAllow;

import java.util.List;

/**
 * 系统默认的信息
 * @author dmzmwh
 * @since 1.0.0
 */
public class WordSystem implements IWord {

    /**
     * 系统默认非敏感词信息
     * @return
     */
    @Override
    public List<String> allow() {
        return StreamUtil.readAllLines("/sensitive_word_allow.txt");
    }

    /**
     * 系统默认敏感词语
     * @return
     */
    @Override
    public List<String> deny() {
        List<String> dict = StreamUtil.readAllLines("/dict.txt");
        List<String> dict_en = StreamUtil.readAllLines("/dict_en.txt");
        List<String> sensitive_word_deny = StreamUtil.readAllLines("/sensitive_word_deny.txt");
        dict.addAll(dict_en);
        dict.addAll(sensitive_word_deny);
        return dict;
    }
}
