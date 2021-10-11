package com.zmwh.sensitive.word.spring.service;

import com.zmwh.sensitive.word.bs.SensitiveWordTools;
import com.zmwh.sensitive.word.spring.annotation.Autowired;
import com.zmwh.sensitive.word.spring.annotation.Component;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Component
public class SensitiveWordService {

    @Autowired
    private SensitiveWordTools sensitiveWordTools;

    /**
     * 更新词库
     *
     * 每次数据库的信息发生变化之后，首先调用更新数据库敏感词库的方法。
     * 如果需要生效，则调用这个方法。
     *
     * 说明：重新初始化不影响旧的方法使用。初始化完成后，会以新的为准。
     */
    public void refresh() {
        // 每次数据库的信息发生变化之后，首先调用更新数据库敏感词库的方法，然后调用这个方法。
        sensitiveWordTools.init();
    }

    /**
     * 是否包含
     *
     * 可以重新封装，也可以直接使用 sensitiveWordTools
     * @param word 单词
     * @return 结果
     */
    public boolean contains(String word){
        return sensitiveWordTools.contains(word);
    }

}
