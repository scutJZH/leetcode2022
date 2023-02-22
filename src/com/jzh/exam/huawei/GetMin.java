package com.jzh.exam.huawei;

import java.util.*;

/**
 * 给一个二维数组nums，对于每一个元素nums[i]，找出距离最近且值相等的元素，输出横纵坐标差值的绝对值之和，没有则输出-1
 */
public class GetMin {

    public static void main(String[] args) {
        int[][] nums = new int[][]{{0,3,4,5,2},{2,5,7,8,3},{2,5,4,2,4}};
        int[][] result = getMin(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for (int[] num : result) {
            List<Integer> list = new ArrayList<>();
            for (int n : num) {
                list.add(n);
            }
            resultList.add(list);
        }
        System.out.println(resultList);
    }

    public static int[][] getMin(int[][] nums) {
        int[][] result = new int[nums.length][nums[0].length];

        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (map.containsKey(nums[i][j])) {
                    List<int[]> list = map.get(nums[i][j]);

                    int length = Integer.MAX_VALUE;
                    for (int[] arr : list) {
                        int d = Math.abs(i - arr[0]) + Math.abs(j - arr[1]);
                        if (result[arr[0]][arr[1]] == -1 || d < result[arr[0]][arr[1]]) {
                            result[arr[0]][arr[1]] = d;
                        }
                        length = Math.min(length, d);
                    }
                    list.add(new int[] {i, j});
                    result[i][j] = length;
                } else {
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[] {i, j});
                    map.put(nums[i][j], list);
                    result[i][j] = -1;
                }
            }
        }

        return result;
    }

}
