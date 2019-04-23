/**
 * Created by Xiaozhong on 2018/12/9.
 * Copyright (c) 2018/12/9 Xiaozhong. All rights reserved.
 */
package cn.happyzhong;

public interface List<T> {

    /**
     * 增加指定元素到 List 中
     * 默认添加到链表尾部
     * @param t 待添加的元素
     */
    void add(T t);

    /**
     * 删除指定的元素
     * @param t 待删除的元素
     */
    void remove(T t);

    /**
     * 根据下标获取指定的元素
     * @param index 要获取元素的下标
     */
    T get(int index);

    int get(T t);

    /**
     * 验证目标对象是否在列表中
     * @param o 要验证的对象
     * @return 验证结果，在->true 不在->false
     */
    boolean contains(Object o);

    /**
     * 是否列表为空
     * @return 判断是否列表为空
     */
    boolean isEmpty();

    /**
     * 返回列表长度
     * @return 列表的长度
     */
    int getLength();

    /**
     * 单例化列表
     */
    void singleCase();
}
