package com.kekelian.net;

public class Api {
    public static  final String Host="http://192.168.1.200:8081";//正式环境

    /**
     * 展示课课练的状态
     */
    public static String GET_KEKELIAN_LIST=Host+"/app/getKKLTabList.action";

    /**
     * 查看分课时每一个Tab(课时的具体内容)
     */
    public static String GET_KEKELIAN_LESSON_CONTENT=Host+"/app/getKKLLessonContent.action";


    /**
     * 查看单元测试的具体内容
     */
    public static String GET_UNIT_TEST_CONTENT=Host+"/app/getUnitTestContent.action";
}