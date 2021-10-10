package com.zmwh.sensitive.word.bs;

import com.zmwh.sensitive.word.support.allow.WordAllows;
import com.zmwh.sensitive.word.support.deny.WordDenys;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * <p> project: sensitive-word-SensitiveWordBsTest </p>
 * <p> create on 2020/1/7 23:43 </p>
 *
 * @author Administrator
 * @since 0.0.1
 */
public class SensitiveWordBsTest {

    /**
     * 是否包含
     * @since 0.0.1
     */
    @Test
    public void containsTest() {
        final String text = "操你妈妈";
        boolean contains = SensitiveWordBs.newInstance().contains(text);
        List<String> all = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertTrue(SensitiveWordBs.newInstance().contains(text));
    }

    /**
     * 返回所有敏感词
     * @since 0.0.1
     */
    @Test
    public void findAllTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        List<String> wordList = SensitiveWordBs.newInstance().findAll(text);
        Assert.assertEquals("[五星红旗, 毛主席, 天安门]", wordList.toString());
    }

    /**
     * 返回所有第一个匹配的敏感词
     * @since 0.0.1
     */
    @Test
    public void findFirstTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String word = SensitiveWordBs.newInstance().findFirst(text);
        Assert.assertEquals("五星红旗", word);
    }

    /**
     * 默认的替换策略
     * @since 0.0.2
     */
    @Test
    public void replaceTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String result = SensitiveWordBs.newInstance().replace(text);
        Assert.assertEquals("****迎风飘扬，***的画像屹立在***前。", result);
    }

    /**
     * 自定义字符的替换策略
     * @since 0.0.2
     */
    @Test
    public void replaceCharTest() {
        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";

        String result = SensitiveWordBs.newInstance().replace(text, '0');
        Assert.assertEquals("0000迎风飘扬，000的画像屹立在000前。", result);
    }

    /**
     * 忽略大小写
     * @since 0.0.4
     */
    @Test
    public void ignoreCaseTest() {
        final String text = "fuCK the bad words.";

        String word = SensitiveWordBs.newInstance().findFirst(text);
        Assert.assertEquals("fuCK", word);
    }

    /**
     * 忽略半角圆角
     * @since 0.0.4
     */
    @Test
    public void ignoreWidthTest() {
        final String text = "ｆｕｃｋ the bad words.";

        String word = SensitiveWordBs.newInstance().findFirst(text);
        Assert.assertEquals("ｆｕｃｋ", word);
    }

    @Test
    public void configTest() {
        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .wordDeny(WordDenys.system())
                .wordAllow(WordAllows.system())
                .init();

        final String text = "五星红旗迎风飘扬，毛主席的画像屹立在天安门前。";
        Assert.assertTrue(wordBs.contains(text));
    }

}
