package com.zmwh.sensitive.word.bs;

import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.support.instance.impl.Instances;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.zmwh.sensitive.word.api.*;
import com.zmwh.sensitive.word.constant.enums.WordTypeEnum;
import com.zmwh.sensitive.word.support.iword.WordSystem;
import com.zmwh.sensitive.word.support.map.SensitiveWordByTypeMap;
import com.zmwh.sensitive.word.support.result.WordResultHandlers;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * 敏感词引导类
 *
 * @author dmzmwh
 * @since 1.0.1
 */
public class SensitiveWordTools {

    /**
     * 私有化构造器
     *
     * @since 0.0.1
     */
    private SensitiveWordTools() {
    }

    /**
     * 敏感词 map
     *
     * @since 0.0.1
     */
    private IWordByTypeMap sensitiveWordByTypeMap;

    /**
     * 默认的执行上下文
     *
     * @since 0.0.4
     */
    private final IWordContext context = buildDefaultContext();

    /**
     * 处理敏感词语结果
     * @since 1.0.1
     */
    private IWord iWord = Instances.singleton(WordSystem.class);

    /**
     * DCL 初始化 wordMap 信息
     *
     * 注意：map 的构建是一个比较耗时的动作
     * @since 0.0.4
     */
    private synchronized void initWordMap() {
        // 加载配置信息
        Set<String> allow = iWord.allow();
        HashMap<Integer, Set<String>> sensitive = iWord.sensitive();
        HashMap<Integer, Set<String>> appendSensitive = iWord.appendSensitive();
        if (appendSensitive != null){
            for (Integer key : appendSensitive.keySet()) {
                //汇总追加的数据
                Set<String> list = sensitive.getOrDefault(key, new HashSet<>());
                Set<String> appendSensitiveV = appendSensitive.get(key);
                list.addAll(appendSensitiveV);

                //处理没有分类的敏感词数据
                Integer key1 = WordTypeEnum.UNKNOWN.getKey();
                Set<String> unknown = sensitive.get(WordTypeEnum.UNKNOWN.getKey());
                if (!key.equals(key1) &&
                        CollectionUtils.isNotEmpty(unknown)
                        && CollectionUtils.isNotEmpty(appendSensitiveV)){
                    //尽可能让敏感词有含义
                    unknown.removeAll(appendSensitiveV);
                }
            }
        }

        Collection<Set<String>> values = sensitive.values();
        HashSet<String> all = new HashSet<>();
        for (Set<String> value : values) {
            if (CollectionUtil.isNotEmpty(allow)){
                value.removeAll(allow);
            }
            all.addAll(value);
        }
        // 初始化 DFA 信息
        if(sensitiveWordByTypeMap == null) {
            sensitiveWordByTypeMap = new SensitiveWordByTypeMap();
        }
        // 便于可以多次初始化
        sensitiveWordByTypeMap.initWordMap(sensitive,all.size());
    }


    /**
     * 新建验证实例
     * <p>
     * double-lock
     *
     * @return this
     * @since 0.0.1
     */
    public static SensitiveWordTools newInstance() {
        return new SensitiveWordTools();
    }

    /**
     * 初始化
     *
     * 1. 根据配置，初始化对应的 map。比较消耗性能。
     * @since 0.0.13
     * @return this
     */
    public SensitiveWordTools init() {
        this.initWordMap();

        return this;
    }


    /**
     * 处理敏感词语结果
     * @param iWord
     * @return
     */
    public SensitiveWordTools iWord(IWord iWord) {
        ArgUtil.notNull(iWord, "IWord");
        this.iWord = iWord;
        return this;
    }

    /**
     * 设置是否启动数字检测
     *
     * @param enableNumCheck 数字检测
     * @since 0.0.11
     * @return this
     */
    public SensitiveWordTools enableNumCheck(boolean enableNumCheck) {
        this.context.sensitiveCheckNum(enableNumCheck);
        return this;
    }

    /**
     * 设置是否启动 email 检测
     *
     * @param enableEmailCheck email 检测
     * @since 0.0.11
     * @return this
     */
    public SensitiveWordTools enableEmailCheck(boolean enableEmailCheck) {
        this.context.sensitiveCheckEmail(enableEmailCheck);
        return this;
    }

    /**
     * 设置是否启动 url 检测
     *
     * @param enableUrlCheck url 检测
     * @since 0.0.12
     * @return this
     */
    public SensitiveWordTools enableUrlCheck(boolean enableUrlCheck) {
        this.context.sensitiveCheckUrl(enableUrlCheck);
        return this;
    }

    /**
     * 是否忽略大小写
     * @param ignoreCase 大小写
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordTools ignoreCase(boolean ignoreCase) {
        this.context.ignoreCase(ignoreCase);
        return this;
    }

    /**
     * 是否忽略半角全角
     * @param ignoreWidth 半角全角
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordTools ignoreWidth(boolean ignoreWidth) {
        this.context.ignoreWidth(ignoreWidth);
        return this;
    }

    /**
     * 是否忽略数字格式
     * @param ignoreNumStyle 数字格式
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordTools ignoreNumStyle(boolean ignoreNumStyle) {
        this.context.ignoreNumStyle(ignoreNumStyle);
        return this;
    }

    /**
     * 是否忽略中文样式
     * @param ignoreChineseStyle 中文样式
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordTools ignoreChineseStyle(boolean ignoreChineseStyle) {
        this.context.ignoreChineseStyle(ignoreChineseStyle);
        return this;
    }

    /**
     * 是否忽略英文样式
     * @param ignoreEnglishStyle 英文样式
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordTools ignoreEnglishStyle(boolean ignoreEnglishStyle) {
        this.context.ignoreEnglishStyle(ignoreEnglishStyle);
        return this;
    }

    /**
     * 是否忽略重复
     * @param ignoreRepeat 忽略重复
     * @return this
     * @since 0.0.14
     */
    public SensitiveWordTools ignoreRepeat(boolean ignoreRepeat) {
        this.context.ignoreRepeat(ignoreRepeat);
        return this;
    }

    /**
     * 构建默认的上下文
     *
     * @return 结果
     * @since 0.0.4
     */
    private IWordContext buildDefaultContext() {
        IWordContext wordContext = SensitiveWordContext.newInstance();
        // 格式统一化
        wordContext.ignoreCase(true);
        wordContext.ignoreWidth(true);
        wordContext.ignoreNumStyle(true);
        wordContext.ignoreChineseStyle(true);
        wordContext.ignoreEnglishStyle(true);
        wordContext.ignoreRepeat(false);

        // 开启校验
        wordContext.sensitiveCheckNum(true);
        wordContext.sensitiveCheckEmail(true);
        wordContext.sensitiveCheckUrl(true);

        return wordContext;
    }

    /**
     * 是否包含敏感词
     *
     * @param target 目标字符串
     * @return 是否
     * @since 0.0.1
     */
    public boolean contains(final String target) {
        statusCheck();

        return sensitiveWordByTypeMap.contains(target, context);
    }
    /**
     * 是否包含敏感词
     *
     * @param target 目标字符串
     * @return 是否
     * @since 异常内容
     */
    public IWordResult containsP(final String target) {
        statusCheck();

        return sensitiveWordByTypeMap.containsP(target, context);
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     *
     * @param target 目标字符串
     * @return 敏感词列表
     * @since 0.0.1
     */
    public List<String> findAll(final String target) {
        return findAll(target, WordResultHandlers.word());
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target 目标字符串
     * @return 敏感词
     * @since 0.0.1
     */
    public String findFirst(final String target) {
        return findFirst(target, WordResultHandlers.word());
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     *
     * @param target 目标字符串
     * @return 敏感词列表
     * @since 0.0.1
     */
    public <R> List<R> findAll(final String target, final IWordResultHandler<R> handler) {
        ArgUtil.notNull(handler, "handler");
        statusCheck();

        List<IWordResult> wordResults = sensitiveWordByTypeMap.findAll(target, context);
        return CollectionUtil.toList(wordResults, new IHandler<IWordResult, R>() {
            @Override
            public R handle(IWordResult wordResult) {
                return handler.handle(wordResult);
            }
        });
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target 目标字符串
     * @param handler 处理类
     * @param <R> 泛型
     * @return 敏感词
     * @since 0.0.1
     */
    public <R> R findFirst(final String target, final IWordResultHandler<R> handler) {
        ArgUtil.notNull(handler, "handler");
        statusCheck();

        IWordResult wordResult = sensitiveWordByTypeMap.findFirst(target, context);
        return handler.handle(wordResult);
    }


    /**
     * 替换所有内容
     *
     * @param target      目标字符串
     * @param replaceChar 替换为的 char
     * @return 替换后结果
     * @since 0.0.2
     */
    public String replace(final String target, final char replaceChar) {
        statusCheck();

        return sensitiveWordByTypeMap.replace(target, replaceChar, context);
    }

    /**
     * 替换所有内容
     * 1. 默认使用空格替换，避免星号改变 md 的格式。
     *
     * @param target 目标字符串
     * @return 替换后结果
     * @since 0.0.2
     */
    public String replace(final String target) {
        return this.replace(target, CharConst.STAR);
    }


    /**
     * 状态校验
     * @since 0.0.13
     */
    private void statusCheck(){
        //DLC
        if(sensitiveWordByTypeMap == null) {
            synchronized (this) {
                if(sensitiveWordByTypeMap == null) {
                    this.init();
                }
            }
        }
    }

}
