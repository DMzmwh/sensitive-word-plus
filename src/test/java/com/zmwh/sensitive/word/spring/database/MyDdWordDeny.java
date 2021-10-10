package com.zmwh.sensitive.word.spring.database;

import com.zmwh.sensitive.word.api.IWordDeny;
import com.zmwh.sensitive.word.spring.annotation.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 1.0.0
 */
@Component
public class MyDdWordDeny implements IWordDeny {

    @Override
    public List<String> deny() {
        // 数据库返回的各种信息
        return Arrays.asList("广告");
    }

}
