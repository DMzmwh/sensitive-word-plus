package com.zmwh.sensitive.word.spring.database;

import com.zmwh.sensitive.word.api.IWordAllow;
import com.zmwh.sensitive.word.spring.annotation.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Component
public class MyDdWordAllow implements IWordAllow {

    @Override
    public List<String> allow() {
        // 数据库查询
        return Arrays.asList("学习");
    }

}
