package com.zmwh.sensitive.word.api;

import com.zmwh.sensitive.word.constant.enums.WordTypeEnum;
import com.zmwh.sensitive.word.utils.StreamLineUtil;

import java.util.HashMap;
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
        Set<String> dict = StreamLineUtil.readAllLines("/dict.txt");
        Set<String> political = StreamLineUtil.readAllLines("/political.txt");
        dict.removeAll(political);
        Set<String> porn = StreamLineUtil.readAllLines("/porn.txt");
        dict.removeAll(porn);
        Set<String> advertising = StreamLineUtil.readAllLines("/advertising.txt");
        dict.removeAll(advertising);
        Set<String> violence = StreamLineUtil.readAllLines("/violence.txt");
        dict.removeAll(violence);

        HashMap<Integer, Set<String>> map = new HashMap<>();
        map.put(WordTypeEnum.UNKNOWN.getKey(),dict);
        map.put(WordTypeEnum.POLITICAL.getKey(),political);
        map.put(WordTypeEnum.PORN.getKey(),porn);
        map.put(WordTypeEnum.ADVERTISING.getKey(),advertising);
        map.put(WordTypeEnum.VIOLENCE.getKey(),violence);

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
