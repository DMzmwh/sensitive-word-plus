package com.zmwh.sensitive.word.define;

import com.zmwh.sensitive.word.api.IWordAllow;

import java.util.Arrays;
import java.util.List;

/**
 * @author binbin.hou
 * @since 0.0.14
 */
public class MyWordAllow implements IWordAllow {

    @Override
    public List<String> allow() {
        return Arrays.asList("测试");
    }

}
