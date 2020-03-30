package com.github.yanzheshi;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

/**
 * 求topK
 * 对比bfprt算法 和 heep的时间效率
 */
public class Bfprt {
    public static int[] getMinKNumsByBFPRT(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            return arr;
        }
        int minKth = getMinKthByBFPRT(arr, k);
        int[] res = new int[k];
        int index = 0;
        for (int i = 0; i != arr.length; i++) {
            if (arr[i] < minKth) {
                res[index++] = arr[i];
            }
        }
        for (; index != res.length; index++) {
            res[index] = minKth;
        }
        return res;
    }

    public static int getMinKthByBFPRT(int[] arr, int K) {
        int[] copyArr = copyArray(arr);
        return select(copyArr, 0, copyArr.length - 1, K - 1);
    }

    public static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i != res.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int select(int[] arr, int begin, int end, int i) {
        if (begin == end) {
            return arr[begin];
        }
        int pivot = medianOfMedians(arr, begin, end);
        int[] pivotRange = partition(arr, begin, end, pivot);
        if (i >= pivotRange[0] && i <= pivotRange[1]) {
            return arr[i];
        } else if (i < pivotRange[0]) {
            return select(arr, begin, pivotRange[0] - 1, i);
        } else {
            return select(arr, pivotRange[1] + 1, end, i);
        }
    }

    public static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        int offset = num % 5 == 0 ? 0 : 1;
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return select(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    public static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivotValue) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivotValue) {
                swap(arr, cur, --big);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    public static int getMedian(int[] arr, int begin, int end) {
        insertionSort(arr, begin, end);
        int sum = end + begin;
        int mid = (sum / 2) + (sum % 2);
        return arr[mid];
    }

    public static void insertionSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i != end + 1; i++) {
            for (int j = i; j != begin; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // -------------使用大顶堆----------------

    public static int[] getMinKNumsByHeep(int[] arr, int k) {
        PriorityQueue<Integer> heep = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int i = 0; i < k && i < arr.length; i++) {
            heep.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < heep.peek()) {
                heep.poll();
                heep.offer(arr[i]);
            }
        }
        return heep.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        int[] arr = Stream.generate(secureRandom::nextInt).limit(8000000L).mapToInt(a->a.intValue()).toArray();

        long startTime = System.currentTimeMillis();
        System.out.println("startTime: " + startTime);
        int[] minKNumsByHeep = getMinKNumsByHeep(arr, 5);
        System.out.println(minKNumsByHeep[1]);
        long currentTime = System.currentTimeMillis();
        System.out.println("heep cost:" + (currentTime - startTime));
        int[] minKNumsByBFPRT = getMinKNumsByBFPRT(arr, 5);
        System.out.println(minKNumsByBFPRT[1]);
        long endTime = System.currentTimeMillis();
        System.out.println("bfprt cost: " + (endTime - currentTime));

        System.out.println("endTime: " +endTime);

        // 运行多次查看结果可以看出
        // bfprt算法局限性非常大， 只有n比较小时才比heep有优势， 而且这个优势可以忽略不计， 几毫秒之间
        // 经过比较n小于10000时

    }
}

