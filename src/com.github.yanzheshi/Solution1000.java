package com.github.yanzheshi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution1000 {
    /**
     * leetcode 926. 将字符串翻转到单调递增
     * 答案错误，72 / 93
     * @return
     */
    public int minFlipsMonoIncr(String s) {

        char[] chars = s.toCharArray();

        int count = 0;
        // 正向遍历
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < chars[i - 1]) {
                // 前一位是1
                chars[i] = '1';
                count++;
            }
        }
        int leftCount = count;

//        System.out.println("lr" + leftCount + "rs:" + Arrays.toString(chars));


        // 恢复
        chars = s.toCharArray();
        count = 0;
        // 反向遍历
        for (int i = chars.length-2; i >= 0; i--) {
            if (chars[i] > chars[i + 1]) {
                // 后一位是0
                chars[i] = '0';
                count++;
            }
        }
        int rightCount = count;

//        System.out.println("rr" + rightCount + "rs:" + Arrays.toString(chars));

        return Math.min(leftCount, rightCount);
    }

    /**
     * leetcode 926. 将字符串翻转到单调递增
     * 正确
     * @return
     */
    public int minFlipsMonoIncr1(String s) {
        char[] array = s.toCharArray();

        int[] low = new int[s.length()];
        int[] high = new int[s.length()];

        if (array[0] == '0') {
            low[0] = 0;
            high[0] = 1;
        } else {
            low[0] = 1;
            high[0] = 0;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i] == '0') {
                low[i] = low[i - 1];
                high[i] = 1 + Math.min(low[i - 1], high[i - 1]);
            } else {
                low[i] = 1 + low[i - 1];
                high[i] = Math.min(low[i - 1], high[i - 1]);
            }
        }

        return Math.min(low[array.length -1 ], high[array.length-1]);
    }

    /**
     * leetcode 926. 将字符串翻转到单调递增
     * 通过
     * @return
     */
    public int minFlipsMonoIncr2(String s) {
        int low,high;

        if (s.charAt(0) == '0') {
            low = 0;
            high = 1;
        } else {
            low = 1;
            high = 0;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                high = 1 + Math.min(low, high);
            } else {
                high = Math.min(low, high);
                low = 1 + low;
            }
        }
        return Math.min(low, high);
    }

    /**
     * leetcode 969 煎饼排序
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {

        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);

        List<Integer> res = new ArrayList<>();
        List<Integer> origin = new ArrayList<>();

        for (int i : arr) {
            origin.add(i);
        }

        for (int i = sorted.length-1; i > 0; i--) {
            int k = origin.indexOf(sorted[i]);
            if (k != i) {
                if (k != 0) {
                    res.add(k + 1);
                    // 翻转
                    for (int j = 0; j <=  k / 2; j++) {
                        int tem = origin.get(j);
                        origin.set(j, origin.get(k-j));
                        origin.set(k - j, tem);
                    }
                }
                k = i;
                res.add(k + 1);
                for (int j = 0; j <=  k / 2; j++) {
                    int tem = origin.get(j);
                    origin.set(j, origin.get(k-j));
                    origin.set(k - j, tem);
                }
            }
        }
        return res;
    }

    /**
     * 890. 查找和替换模式
     * @param words
     * @param pattern
     * @return
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {

        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (judge(word, pattern)) {
                res.add(word);
            }
        }
        return res;
    }

    public boolean judge(String word, String pattern) {
        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) != pattern.charAt(i)) {
                    return false
                            ;
                }

            } else {
                map.put(c, pattern.charAt(i));
            }
        }
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (Map.Entry<Character, Character> characterCharacterEntry : map.entrySet()) {
            set1.add(characterCharacterEntry.getValue());
            set2.add(characterCharacterEntry.getKey());
        }
        if (set1.size() != set2.size()) {
            return false;
        }
        return true;
    }

    /**
     * 2564
     * @param first
     * @param second
     * @return
     */
    public boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        if (first.length() - second.length() == 0) {
            int count = 0;
            for (int i = 0; i < first.length(); i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    count++;
                }
            }
            if (count < 2) {
                return true;
            }
        }

        if (first.length() - second.length() == 1) {
            int count = 0;
            for (int i = 0, j = 0; i < first.length() && j < second.length(); i++, j++) {
                if (first.charAt(i) != second.charAt(j)) {
                    count++;
                    j--;
                }
            }
            if (count < 2) {
                return true;
            }
        }


        if (first.length() - second.length() == -1) {
            int count = 0;
            for (int i = 0, j = 0; i < first.length() && j < second.length(); i++, j++) {
                if (first.charAt(i) != second.charAt(j)) {
                    count++;
                    i--;
                }
            }
            if (count < 2) {
                return true;
            }
        }

        return false;
    }


    /**
     * leetcode 1051
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {

        int[] arrays = new int[heights.length];
        System.arraycopy(heights, 0, arrays, 0, heights.length);
        Arrays.sort(arrays);

        int count = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != arrays[i]) {
                count++;
            }

        }
        return count;
    }

    /**
     * leetcode 719
     * 答案错误
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {

        Arrays.sort(nums);
        int r = (nums.length * (nums.length - 1)) / 2 + 1 - k;

        int[] res = new int[r+k];
        int index = 0;
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {

            for (int l = nums.length-1; l >= j ; l--) {
                res[index++] = nums[j] - nums[i];
            }
            for (int m = 0; m <= i ; m++) {
                res[index++] = nums[j] - nums[i];
            }
            res[index++] = nums[j] - nums[i];
            if (index >= r-1) {
                break;
            }
        }
        Arrays.sort(res);
        System.out.println(Arrays.toString(res));
        return res[res.length+1-r];
    }

    /**
     * leetcode 719
     * 内存超限
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair2(int[] nums, int k) {

        Arrays.sort(nums);

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int r = (nums.length * (nums.length - 1)) / 2 + 1 - k;

        int[] res = new int[(nums.length * (nums.length - 1)) / 2];
        int index = 0;
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                res[index++] = nums[j] - nums[i];
            }
        }
        Arrays.sort(res);
        System.out.println(Arrays.toString(res));
        return res[k-1];
    }

    /**
     * leetcode 719
     * 使用堆
     * 内存超限
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair3(int[] nums, int k) {

//        Arrays.sort(nums);

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int r = (nums.length * (nums.length - 1)) / 2 + 1 - k;

//        int[] res = new int[(nums.length * (nums.length - 1)) / 2];
//        int index = 0;
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int dis = Math.abs(nums[j] - nums[i]);
                if (queue.size() < r) {
                    queue.offer(dis);
                } else if (dis > queue.peek()) {
                    queue.poll();
                    queue.offer(dis);
                }
            }
        }
        return queue.peek();
    }

    public int smallestDistancePair4(int[] nums, int k) {

        Arrays.sort(nums);
        int r = (nums.length * (nums.length - 1)) / 2 + 1 - k;

        int f;
        int tem = 0;
        int seq;
        for (f = 0; ; f++) {
            tem = tem + f + 1;
            if (tem >= r) {
                seq = r - (f  + 1) ;
                break;
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < f; i++) {
            for (int j = 0; j < f; j++) {
                int dis = Math.abs(nums[nums.length-1-i] - nums[j]);
                if (queue.size() < seq) {
                    queue.offer(dis);
                } else if (dis > queue.peek()) {
                    queue.poll();
                    queue.offer(dis);
                }
            }
        }
        return queue.peek();
    }

    /**
     * leetcode 719
     * 二分搜索k值
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair5(int[] nums, int k) {

        Arrays.sort(nums);

        int left = 0, right = nums[nums.length - 1] - nums[0];

        while (left <= right) {
            int mid = (left + right) / 2;
            if (getLessCount(nums, mid) >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 通过二分查找比value小的个数
     * @param nums
     * @param value
     * @return
     */
    public int getLessCount(int[] nums, int value) {

        int count = 0;
        for (int i = nums.length-1; i > 0; i--) {
            int left = 0, right = i;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[i] - nums[mid] > value) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            count = count + i - left;
        }

        return count;
    }

    /**
     * 通过双指针查找小于value的个数
     * @param nums
     * @param value
     * @return
     */
    public int getLessCount1(int[] nums, int value) {


        int i, j;
        int count =0 ;
        for (i = 0, j = 0; j < nums.length; j++) {
            while (nums[j] - nums[i] > value) {
                i++;
            }
            count+=j-i;
        }
        return count;
    }

    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair6(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, left = 0, right = nums[n - 1] - nums[0];
        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                int i = binarySearch(nums, j, nums[j] - mid);
                cnt += j - i;
            }
            if (cnt >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int binarySearch(int[] nums, int end, int target) {
        int left = 0, right = end;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public int smallestDistancePair7(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0, right = nums[nums.length - 1] - nums[0];

        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            int i, j;
            for (i = 0, j = 0; j < nums.length; j++) {
                while (nums[j] - nums[i] > mid) {
                    i++;
                }
                count+=j-i;
            }
            if (count >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * leetcode 1089
     * ac
     * @param arr
     */
    public void duplicateZeros(int[] arr) {

        int zeroCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroCount++;
            }
        }

        for (int i = arr.length-1; i >= 0  ; i--) {
            if (i + zeroCount < arr.length) {
                arr[i + zeroCount] = arr[i];
            }
            if (arr[i] == 0) {
                zeroCount--;
                if (i + zeroCount < arr.length) {
                    arr[i + zeroCount] = 0;
                }
            }
        }
    }

    /**
     * leetcode 593
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {

        int[] dis = new int[6];
        dis[0] = getDisPow(p1, p2);
        dis[1] = getDisPow(p1, p3);
        dis[2] = getDisPow(p1, p4);
        dis[3] = getDisPow(p2, p3);
        dis[4] = getDisPow(p2, p4);
        dis[5] = getDisPow(p3, p4);

        Arrays.sort(dis);

        return (dis[0] == dis[1] && dis[2] == dis[3] && dis[0] == dis[2]
                && dis[4] == dis[5] && dis[0] != 0);
    }

    public int getDisPow(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }


    public String replaceSpace(String s) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                sb.append(c);
            } else {
                sb.append("%20");
            }
        }
        return sb.toString();
    }


    /**
     * LeetCode
     * 剑指offer II 029 排序的循环链表
     * 本来一次循环可以解决，但是无法解决链表元素都相等的情况，所以一开始又特殊判断了此情况。
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        Node p = head, q = head.next;
        Node min = head, max = head;
        do {
            if (p.val < min.val) {
                min = p;
            }
            if (p.val > max.val) {
                max = p;
            }
            p = p.next;
        } while ((p!= head));
        if (min == max) {
            // 链表元素都相同
            Node n = new Node(insertVal, q);
            p.next = n;
            return head;
        }

        while (true) {
            // 正常插入中间
            if (p.val <= q.val && p.val <= insertVal && insertVal <= q.val
                    // 插入两端
            || (p.val > q.val && (p.val <= insertVal && insertVal >= q.val
                    || p.val >= insertVal && insertVal <= q.val))) {
                Node n = new Node(insertVal, q);
                p.next = n;
                break;
            }
            p = p.next;
            q = q.next;
        }
        // 1 2 4 4    4  ok
        // 1 2 4 4    3  ok
        // 1 2 4 4    8  ok
        // 1 2 4 4    0  ok
        // 4 4 4 4    0  fail
        // 4 4 4 4 1  0 ok
        // 4 4 4 4 3 5  ok
        return head;
    }

    public Node insert1(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal);
            head.next = head;
            return head;
        }

        Node p = head, q = head.next;
        do {
            if (p.val <= q.val && p.val <= insertVal && insertVal <= q.val
                    // 插入两端
                    || (p.val > q.val && (p.val <= insertVal && insertVal >= q.val
                    || p.val >= insertVal && insertVal <= q.val))) {
                break;
            }
            p = p.next;
            q = q.next;
        } while ((p!= head));
        Node n = new Node(insertVal, q);
        p.next = n;

        return head;
    }

    static class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    };

    public static void main(String[] args) {
        Node p1 = new Node(3);
        Node p2 = new Node(4);
        p1.next = p2;
        Node p3 = new Node(1);
        p2.next = p3;
        p3.next = p1;

        Solution1000 solution1000 = new Solution1000();
        solution1000.insert1(p1, 2);
    }



}
