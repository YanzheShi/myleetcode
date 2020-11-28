package com.github.yanzheshi;

/**
 * @author shiyanzhe
 * @date 2018/8/1
 */
public class ListNode {

    int val;
    ListNode next;
    ListNode random;

    ListNode() {}

    ListNode(int x) { val = x; }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    ListNode(int... a) {
        if (a.length == 0) {
            return;
        }
        val  = a[0];
        if (a.length == 1) {
            return;
        }
        next = new ListNode();
        ListNode p = next;
        for (int i = 1; i < a.length; i++) {
            p.val = a[i];
            if (i != a.length - 1) {
                p.next = new ListNode();
                p = p.next;
            }
        }
    }



    public static void print(ListNode head) {
        System.out.println(head.val);

        head = head.next;
        while (head != null) {
            System.out.println(" -> ");
            System.out.println(head.val);
            head = head.next;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(val);
        ListNode p = next;
        while (p != null) {
            sb.append(" -> ");
            sb.append(p.val);
            p = p.next;
        }
        return sb.toString();
    }


}
