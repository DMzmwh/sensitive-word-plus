package com.zmwh.sensitive.word.support.iword;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.zmwh.sensitive.word.api.IWord;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public Set<String> allow() {
        List<String> strings = StreamUtil.readAllLines("/sensitive_word_allow.txt");
        Set allow = new HashSet();
        CollectionUtils.addAll(allow, strings);
        return allow;
    }


    /**
     * 追加敏感词语
     * @return
     */
    @Override
    public HashMap<Integer, Set<String>> appendSensitive() {
        List<String> sensitive_word_deny = StreamUtil.readAllLines("/sensitive_word_deny.txt");
        HashMap<Integer, Set<String>> map = new HashMap<>();
        for (String item : sensitive_word_deny) {
            if (item.contains("#")){
                continue;
            }
            String[] split = item.split("\\+");
            Set<String> list = map.getOrDefault(split[0], new HashSet<>());
            list.add(split[1]);
        }

        return map;
    }
}
