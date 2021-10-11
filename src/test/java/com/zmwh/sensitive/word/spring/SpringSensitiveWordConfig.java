package com.zmwh.sensitive.word.spring;

import com.zmwh.sensitive.word.bs.SensitiveWordTools;
import com.zmwh.sensitive.word.spring.annotation.Autowired;
import com.zmwh.sensitive.word.spring.annotation.Bean;
import com.zmwh.sensitive.word.spring.annotation.Configuration;
import com.zmwh.sensitive.word.spring.database.MyWord;

/**
 * @author dmzmwh
 * @since 1.0.0
 */
@Configuration
public class SpringSensitiveWordConfig {

    @Autowired
    private MyWord myWord;

    /**
     * 初始化引导类
     * @return 初始化引导类
     * @since 1.0.0
     */
    @Bean
    public SensitiveWordTools sensitiveWordBs() {
        SensitiveWordTools sensitiveWordTools = SensitiveWordTools.newInstance()
                .iWord(myWord)
                // 各种其他配置
                .init();

        return sensitiveWordTools;
    }

}
