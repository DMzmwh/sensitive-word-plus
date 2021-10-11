package com.zmwh.sensitive.word.constant.enums;

/**
 * 系统敏感词类型
 * 自行扩展的话选一个较大的数 从100往后扩展，以免框架定义的和自行扩展的产生冲突
 * @author dmzmwh
 */
public enum WordTypeEnum {

    UNKNOWN(0, "未知的"),
    POLITICAL(1, "政治"),
    PORN(2, "色情"),
    ADVERTISING(3, "广告"),
    VIOLENCE(4, "暴力");

    private Integer key;
    private String value;

    WordTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }


    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
