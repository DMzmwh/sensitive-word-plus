package com.zmwh.sensitive.word.api;

import java.util.List;

/**
 * 处理敏感词语结果
 * @author dmzmwh
 * @since 1.0.0
 */
public interface IWord {

    /**
     * 允许的内容-返回的内容不被当做敏感词
     * @return 结果
     * @since 0.0.13
     */
    List<String> allow();


    /**
     * 拒绝出现的数据-返回的内容被当做是敏感词
     * @return 结果
     * @since 0.0.13
     */
    List<String> deny();

}
