package com.zmwh.sensitive.word.api;

import com.github.houbb.heaven.util.io.StreamUtil;
import com.zmwh.sensitive.word.constant.enums.WordTypeEnum;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 处理敏感词语结果
 * @author dmzmwh
 * @since 1.0.0
 */
public interface IWord {

    /**
     * 返回的内容不被当做敏感词
     * @return 结果
     * @since 0.0.13
     */
    Set<String> allow();


    /**
     * 返回的内容被当做是敏感词
     * 默认有值
     * @return 结果
     * @since 1.0.0
     */
    default HashMap<Integer,Set<String>> sensitive(){
        /**
         * UNKNOWN(0, "未知的"),
         * POLITICAL(1, "政治"),
         * PORN(2, "色情"),
         * ADVERTISING(3, "广告"),
         * VIOLENCE(4, "暴力");
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
     * 扩展的数据 新兴敏感词
     * 返回的内容被当做是敏感词
     * 默认为空
     * @return 敏感词
     * @since 1.0.0
     */
    HashMap<Integer,Set<String>> appendSensitive();

}
