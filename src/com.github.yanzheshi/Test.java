package com.github.yanzheshi;

import com.github.yanzheshi.Solution;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author shiyanzhe
 */
public class Test {
    public static void main(String[] args) {

        Solution solution = new Solution();
        Solution1000 solution1000 = new Solution1000();


//        System.out.println(solution.isMatch("b", "?"));
//        System.out.println(solution.isMatch10("aab", "c*a*b"));

//        int[] nums = {9,10,7,10,6,1,5,4,9,8};
        int[] nums = {1,2,3};
        // [1, 1, 4, 5, 14, 15, 18, 19, 19, 20]
        solution1000.duplicateZeros(nums);
        System.out.println(Arrays.toString(nums));
//        System.out.println(solution1000.getLessCount(nums, 48));

    }
}
