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
        int[] num = {8, 7, 4, 3};

        List<List<Integer>> lists = solution.combinationSum(num, 11);

        for (List<Integer> list : lists) {
            System.out.println(list);
        }


    }
}
