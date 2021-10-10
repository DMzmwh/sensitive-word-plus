package com.zmwh.sensitive.word.api;

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
    HashMap<Integer,Set<String>> sensitive();


    /**
     * 扩展的数据 新兴敏感词
     * 返回的内容被当做是敏感词
     * 默认为空
     * @return 敏感词
     * @since 1.0.0
     */
    HashMap<Integer,Set<String>> appendSensitive();

}
