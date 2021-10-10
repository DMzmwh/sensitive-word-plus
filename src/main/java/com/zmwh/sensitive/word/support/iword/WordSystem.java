package com.zmwh.sensitive.word.support.iword;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.zmwh.sensitive.word.api.IWord;
import com.zmwh.sensitive.word.constant.enums.WordTypeEnum;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

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
     * 系统默认敏感词语
     * @return
     */
    @Override
    public HashMap<Integer,Set<String>> sensitive() {
        /**
         *     political(1, "政治"),
         *     porn(2, "色情"),
         *     advertising(3, "广告"),
         *     violence(4, "暴力");
         */
        //后期优化工具类，直接返回Set集合
        List<String> dict = StreamUtil.readAllLines("/dict.txt");
        Set dictSet = new HashSet();
        CollectionUtils.addAll(dictSet, dict);
        List<String> political = StreamUtil.readAllLines("/political.txt");
        Set politicalSet = new HashSet();
        CollectionUtils.addAll(politicalSet, political);
        List<String> porn = StreamUtil.readAllLines("/porn.txt");
        Set pornSet = new HashSet();
        CollectionUtils.addAll(pornSet, porn);
        List<String> advertising = StreamUtil.readAllLines("/advertising.txt");
        Set advertisingSet = new HashSet();
        CollectionUtils.addAll(advertisingSet, advertising);
        List<String> violence = StreamUtil.readAllLines("/violence.txt");
        Set violenceSet = new HashSet();
        CollectionUtils.addAll(violenceSet, violence);

        HashMap<Integer, Set<String>> map = new HashMap<>();
        map.put(WordTypeEnum.UNKNOWN.getKey(),dictSet);
        map.put(WordTypeEnum.POLITICAL.getKey(),politicalSet);
        map.put(WordTypeEnum.PORN.getKey(),pornSet);
        map.put(WordTypeEnum.ADVERTISING.getKey(),advertisingSet);
        map.put(WordTypeEnum.VIOLENCE.getKey(),violenceSet);

        return map;
    }

    /**
     * 系统默认敏感词语
     * @return
     */
    @Override
    public HashMap<Integer, Set<String>> appendSensitive() {
        List<String> sensitive_word_deny = StreamUtil.readAllLines("/sensitive_word_deny.txt");
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (String item : sensitive_word_deny) {
            if (item.contains("#")){
                continue;
            }
            String[] split = item.split("\\+");
            List<String> list = map.getOrDefault(split[0], new ArrayList<String>());
            list.add(split[1]);
        }

        return null;
    }
}
