/**
 * Created by Xiaozhong on 2018/12/9.
 * Copyright (c) 2018/12/9 Xiaozhong. All rights reserved.
 */
package cn.happyzhong.util;

import cn.happyzhong.List;

public class ListPrinter {
    public static void print(List list, String space) {
        for (int i = 0; i < list.getLength(); i++) {
            System.out.print(list.get(i) + space);
        }
    }
}
