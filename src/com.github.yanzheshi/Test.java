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


        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        Solution solution = new Solution();
        System.out.println(solution.swapPairs(listNode));

    }
}
