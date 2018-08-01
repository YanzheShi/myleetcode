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

        ListNode list = new ListNode(1, 2);
        System.out.println(list);

        Solution solution = new Solution();

        System.out.println(solution.reverseKGroup(list,3 ));

    }
}
