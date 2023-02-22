package com.jzh.exam.huawei;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

/**
 * 找出长度至少为L的集合平均值最大子数组
 */
public class FindMax {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNext()) { // 注意 while 处理多个 case
            int length = in.nextInt();
            int l = in.nextInt();
            double[] nums = new double[length];
            for (int i = 0; i < length; i++) {
                nums[i] = in.nextDouble();
            }
            findMax(nums, l);
        }
    }

    public static void findMax(double[] nums, int L) {
        BigDecimal[] arr = new BigDecimal[nums.length + 1];
        arr[0] = BigDecimal.valueOf(1d);

        // nums前N项乘积
        for (int i = 1; i < arr.length; i++) {
            BigDecimal b = BigDecimal.valueOf(nums[i - 1]);
            arr[i] = arr[i - 1].multiply(b);
        }

        int maxStart = 0;
        int maxLength = L;
        double max = Double.MIN_VALUE;

        for (int length = L; length <= nums.length; length++) {
            for (int start = 0; start + length < arr.length; start++) {
                // nums[start] 到 nums[start + length - 1]的乘积为：
                BigDecimal temp = arr[start + length].divide(arr[start]);
                double t = Math.pow(Double.parseDouble(temp.toString()), 1d / length);
//                System.out.println(start + " " + length + " " + t);
                if (t > max) {
                    maxStart = start;
                    maxLength = length;
                    max = t;
                }
            }
        }

        System.out.println(maxStart + " " + maxLength);
    }
}
