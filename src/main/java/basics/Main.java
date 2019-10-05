/**
 * Created by Xiaozhong on 2018/12/9.
 * Copyright (c) 2018/12/9 Xiaozhong. All rights reserved.
 */
package basics;

import util.DataReader;
import util.ListPrinter;
import edu.princeton.cs.algs4.StdDraw;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        test1();
        System.out.println("\n-----------------------");
//        test2();
        System.out.println("\n-----------------------");
        test3();
        System.out.println("\n-----------------------");
        test4();
        System.out.println("\n-----------------------");
//        test5();
        System.out.println("\n-----------------------");
        test6(8, 1, 4);
        long end = System.currentTimeMillis();
        System.out.println("\n上述程序程序运行时间大约为：" + (end - start) + "ms :)");
    }

    /**
     * ArrayList删除重复元素
     */
    private static void test1() {
        ArrayList<String> list = new ArrayList<>();
        String content = DataReader.read("src/main/java/data.cvs", 1);
        System.out.println("读进来的数据为：" + content + "\n");
        String[] words = content.split(",");
        for (String word : words) {
            list.add(word);
        }
        System.out.println("开始容器共有：" + list.getLength() + "个元素，分别是：");
        ListPrinter.print(list, " ");

        //删除重复元素
        list.singleCase();
        System.out.println("\n删除重复元素后容器共有：" + list.getLength() + "个元素，分别是：");
        ListPrinter.print(list, " ");

    }

    /**
     * ArrayList实现数据分割
     */
    private static void test2() {
        String srcData = DataReader.read("src/main/java/data.cvs", 2);
        System.out.println("\n\n读进来的数据为：" + srcData + "\n");
        String[] data = srcData.split(",");
        ArrayList<String> list = new ArrayList<>();
        for (String datum : data) {
            list.add(datum);
        }
        ArrayList[] result = list.split(0);
        System.out.println("ArrayList实现数据分割：\nx > 0\t");
        ListPrinter.print(result[0], " ");
        System.out.println("\nx < 0\t");
        ListPrinter.print(result[1], " ");
    }

    /**
     * LinkedList实现删除重复元素
     */
    private static void test3() {
        LinkedList<String> linkedList = new LinkedList<>();
        String srcData = DataReader.read("src/main/java/data.cvs", 1);
        String[] data = srcData.split(",");
        for (int i = 0; i < data.length; i++) {
            linkedList.add(data[i]);
        }
        //删除重复元素
        linkedList.singleCase();
        System.out.print("LinkedList实现删除重复元素：");
        ListPrinter.print(linkedList, " ");
    }

    /**
     * 计算表达式
     */
    private static void test4() {
        String srcData = DataReader.read("src/main/java/data.cvs", 3);
        String[] express1 = srcData.split(",");
        srcData = DataReader.read("src/main/java/data.cvs", 4);
        String[] express2 = srcData.split(",");
        LinkedList<Multinomial> express11 = new LinkedList<>();
        LinkedList<Multinomial> express22 = new LinkedList<>();
        for (int i = 0; i < express1.length; i++) {
            int coef1 = Integer.parseInt(express1[i]);
            int coef2 = Integer.parseInt(express2[i]);
            express11.add(new Multinomial(coef1, i));
            express22.add(new Multinomial(coef2, i));
        }
        LinkedList<Multinomial> result = new LinkedList<>();
        for (int i = 0; i < express11.getLength(); i++) {
            int coef = express11.get(i).getCoef() + express22.get(i).getCoef();
            result.add(new Multinomial(coef, i));
        }
        for (int i = 0; i < result.getLength() - 1; i++) {
            System.out.print(result.get(i) + " + ");
        }
        System.out.println(result.get(result.getLength() - 1));
    }

    /**
     * 运行时间分析
     */
    private static void test5() {
        long start = System.nanoTime();
        int sum1 = 0, N = 1000;
        for (int i = 0; i < N; i++) {
            sum1++;
        }
        long end = System.nanoTime();
        System.out.println("\n\n运行结果1需要：" + (end - start) + "ns. :)");
        start = System.nanoTime();
        int sum2 = 0;
        for (int i = 0; i < N; i++) {
            sum2 += i;
        }
        end = System.nanoTime();
        System.out.println("\n运行结果2需要：" + (end - start) + "ns. :)");
        start = System.nanoTime();
        int sum3 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum3++;
            }
        }
        end = System.nanoTime();
        System.out.println("\n运行结果3需要：" + (end - start) + "ns. :)");
    }

    /**
     * 约瑟夫环问题
     * !没能解决
     * :(
     */
    public static void test6(int n, int s, int m) {
        LinkedList<String> list = new LinkedList<>();
//        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add((i + 1) + "n");
        }
        int index = s - 1;
        while (list.getLength() != 1) {
            for (int i = 0; i < m; i++) {
                if (index > list.getLength()) {
                    index = 0;
                }
                index++;
            }
            System.out.print(list.get(index) + " ");
            list.remove(list.get(index));
        }
        StdDraw.arc(10, 10, 10, 10, 10);
        System.out.println(list.get(0));
    }
}
