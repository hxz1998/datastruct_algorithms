package util;

/**
 * 读取数据所用到的路径
 */
public class DataPathTemplate {
    public static final String base = "src/main/java/dataset/";

    public static String build(String filename) {
        return new String(base + filename);
    }
}
