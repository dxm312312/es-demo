package com.fanxb.esdemo.util;

/**
 * ES 字段分析器的枚举类
 *
 * 关于 IK 分词，文章 https://blog.csdn.net/xsdxs/article/details/72853288 不错。

 */
public class FieldAnalyzer {

    /**
     * IK 最大化分词
     *
     * 会将文本做最细粒度的拆分
     */
    public static final String IK_MAX_WORD = "ik_max_word";

    /**
     * IK 智能分词
     *
     * 会做最粗粒度的拆分
     */
    public static final String IK_SMART = "ik_smart";

}
