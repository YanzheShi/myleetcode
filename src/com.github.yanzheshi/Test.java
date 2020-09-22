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
        int[] num = {2,5,2,1,2};

        List<List<Integer>> lists = solution.combinationSum2(num, 5);

        for (List<Integer> list : lists) {
            System.out.println(list);
        }


    }
}
