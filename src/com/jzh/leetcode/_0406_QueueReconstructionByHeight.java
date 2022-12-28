package com.jzh.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 * 思路：
 *  解1：先排矮的再排高的
 *      由于身高小于自身的对自己的ki无影响，所以先填入身高更矮的，再进行留空，故按身高从小到大排序
 *      因为身高相同时，在people中排队时，k较小的会排到k较大的前面，会对k更大的排队（数空位）造成影响，故身高相同时按k从大到小排列，先把k更大的插入到队伍中。
 *      给定一个end参数，来统计之前的节点已经完全排满了，不剩空位，减少遍历范围
 *
 *  解2：先排高的，再排矮的
 *      排完高的后，通过插入的方式，矮的插入到已排好的队列中，由于矮的对高的队列顺序和ki均无任何影响，故可以直接插入。
 *      由于相同身高时，如果先插入k值更大的，插入k值更小的时候，会排到k值更大的前面，进而会对k值更大的位置产生影响，故需要先插入k值更小的。
 */
public class _0406_QueueReconstructionByHeight {

    public static void main(String[] args) {
        int[][] people = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        reconstructQueue2(people);
    }

    public static int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length < 2) {
            return people;
        }

        int[][] sortedPeople = new int[people.length][];
        int firstEmpty = 0; // 代表从左往右数第一个空位

        // 对people按身高从小到大、前面有k个更高的人从大到小排序
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);

        // 按身高从小到大排时，如果people[i][1] 为 k，则说明前面应该留k个空位给身高相等或更高的人
        for (int[] p : people) {
            int k = p[1];
            int index = firstEmpty;
            while (k > 0) {
                if (sortedPeople[++index] == null) {
                    k--;
                }
            }
            sortedPeople[index] = p;
            // 找新的第一个空位
            while (firstEmpty < sortedPeople.length && sortedPeople[firstEmpty] != null) {
                firstEmpty++;
            }
        }

        return sortedPeople;
    }

    public static int[][] reconstructQueue2(int[][] people) {
        if (people == null || people.length < 2) {
            return people;
        }
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

        List<int[]> que = new ArrayList<>();

        for (int[] p : people) {
            que.add(p[1], p); //按照位置进行插入排序（涉及到元素复制，底层还是为O(n^2)）
        }

        return que.toArray(new int[people.length][]);
    }
}
