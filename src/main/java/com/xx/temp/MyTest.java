package com.xx.temp;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author XuanXiao
 * @CreateDate 2025/8/20
 */
public class MyTest {
    public static void main(String[] args) {
        // 创建一个包含一些元素的列表
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 自定义线程池，指定线程数量
        int parallelism = 4; // 线程数量
        ForkJoinPool customThreadPool = new ForkJoinPool(parallelism);

        try {
            // 使用自定义线程池执行并行流操作
            customThreadPool.submit(() ->
                    numbers.parallelStream()
                            .forEach(num -> {
                                try {
                                    System.out.println(num);
                                    Thread.sleep(1 * 1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            })
            ).get();

            System.out.println("done ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            customThreadPool.shutdown();
        }
    }
}
