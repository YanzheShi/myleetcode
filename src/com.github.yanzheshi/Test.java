package com.github.yanzheshi;

import com.github.yanzheshi.Solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shiyanzhe
 */
public class Test {
    public static void main(String[] args) {
        String[] strs = {"a", "b"};
        Solution s = new Solution();


        int[] a = {1, 3};
        int[] b = {2};

        System.out.println(s.findMedianSortedArrays(a, b));
    }
}
