/**
 * Copyright (c) 2021 myons Inc. All rights reserved.
 */
package com.zmwh.sensitive.word.spring.database;

import com.zmwh.sensitive.word.api.IWord;
import com.zmwh.sensitive.word.constant.enums.WordTypeEnum;
import com.zmwh.sensitive.word.spring.annotation.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @describe: 自定义的信息 、
 * @author: dmzmwh 、
 * @time: 2021-10-11 11:47 、
 */
@Component
public class MyWord  implements IWord {

    /**
     * 系统默认非敏感词信息
     *
     * @return
     */
    @Override
    public Set<String> allow() {
        Set allow = new HashSet();
        allow.add("排除掉当前词语");
        allow.add("测试");
        return allow;
    }

    /**
     * 系统默认敏感词语 可不重写 有默认实现
     *
     * @return
     */
    @Override
    public HashMap<Integer, Set<String>> sensitive() {
        /**
         * UNKNOWN(0, "未知的"),
         * POLITICAL(1, "政治"),
         * PORN(2, "色情"),
         * ADVERTISING(3, "广告"),
         * VIOLENCE(4, "暴力");
         */

        //后期优化工具类，直接返回Set集合
        Set dictSet = new HashSet();
        dictSet.add("我的自定义敏感词");
        Set advertisingSet = new HashSet();
        advertisingSet.add("买一赠一");
        advertisingSet.add("新年过节不收礼");
        Set violenceSet = new HashSet();
        violenceSet.add("杀头");
        violenceSet.add("杀头直播");
        HashMap<Integer, Set<String>> map = new HashMap<>();
        map.put(WordTypeEnum.UNKNOWN.getKey(), dictSet);
        map.put(WordTypeEnum.ADVERTISING.getKey(), advertisingSet);
        map.put(WordTypeEnum.VIOLENCE.getKey(), violenceSet);

        return map;
    }

    /**
     * 追加敏感词语
     *
     * @return
     */
    @Override
    public HashMap<Integer, Set<String>> appendSensitive() {
        HashMap<Integer, Set<String>> map = new HashMap<>();
        Set set = new HashSet();
        set.add("买一赠一");
        set.add("新年过节不收礼");
        map.put(WordTypeEnum.ADVERTISING.getKey(), set);
        return map;
    }
}
