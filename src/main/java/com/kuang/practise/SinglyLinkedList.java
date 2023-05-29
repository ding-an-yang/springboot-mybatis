package com.kuang.practise;


import org.apache.poi.ss.formula.functions.Roman;
import org.w3c.dom.Node;

import java.util.Random;

/**
 * @author ：yangan
 * @date ：2023/3/3 下午5:06
 * @description：
 * @version:
 */
public class SinglyLinkedList {
    public static void main(String[] args) {
        int[] array = new Random().ints(0, 100).limit(50).toArray();
        int[] ints = countSort(array);
        for (int a : ints) {
            System.out.print(a + "\t");
        }
    }

    public static int[] countSort(int[] A) {
        // 找出数组A中的最大值
        int max = Integer.MIN_VALUE;
        for (int num : A) {
            max = Math.max(max, num);
        }
        // 初始化计数数组count
        int[] count = new int[max+1];
        // 对计数数组各元素赋值
        for (int num : A) {
            count[num]++;
        }
        // 创建结果数组
        int[] result = new int[A.length];
        // 创建结果数组的起始索引
        int index = 0;
        // 遍历计数数组，将计数数组的索引填充到结果数组中
        for (int i=0; i<count.length; i++) {
            while (count[i]>0) {
                result[index++] = i;
                count[i]--;
            }
        }
        // 返回结果数组
        return result;
    }

}


