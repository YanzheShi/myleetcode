import java.util.*;

/**
 * @author shiyanzhe
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (target == nums[i] * 2) {
                    result[0] = map.get(nums[i]);
                    result[1] = i;
                    return result;
                }
            } else {
                map.put(nums[i], i);
            }
        }

        for (int num : map.keySet()) {
            if (map.containsKey(target - num)) {
                result[0] = map.get(num);
                result[1] = map.get(target - num);
            }
        }
        if (result[0] > result[1]) {
            int tem = result[0];
            result[0] = result[1];
            result[1] = tem;
        }
        return result;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        int add = 0;
        for (;l1 != null && l2 != null; l1 = l1.next, l2 = l2.next) {
            int val = l1.val + l2.val + add;
            add = val > 9 ? 1 : 0;
            p.next = new ListNode(val % 10);
            p = p.next;
        }
        ListNode left = l1 == null ? l2 : l1;
        for (;left != null; left = left.next) {
            int val = left.val + add;
            add = val > 9 ? 1 : 0;
            p.next = new ListNode(val % 10);
            p = p.next;
        }
        if (add == 1) {
            p.next = new ListNode(1);
        }
        return head.next;
    }

    public String convert(String s, int numRows) {
        if(numRows <= 0)
            return s;           //in case of Memory Limit Exceeded
        int sum = 2 * numRows - 2;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if (i == 0 || i == numRows - 1) {
                for (int j = i; j < s.length(); j += sum) {
                    sb.append(s.charAt(j));
                }
            } else {
                int next = sum - i;
                for (int j = i; j < s.length(); j += sum) {
                    sb.append(s.charAt(j));
                    if (next < s.length()) {
                        sb.append(s.charAt(next));
                        next += sum;
                    }
                }
            }
        }
        return sb.toString();
    }

    public int reverse(int x) {
        long result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) result;
    }

    public boolean isPalindrome(int x) {
        StringBuilder s = new StringBuilder(x+"");
        return s.reverse().toString().equals(x+"");
    }
    public boolean isMatch(String s, String p) {
        boolean match = true;
        for (int i = 0, j = 0; i < s.length() && j < p.length();) {
        }
        return match;
    }
    public int maxArea(int[] height) {
        int result = 1;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int s = (j - i) * Math.min(height[i], height[j]);
                result = Math.max(result, s);
            }
        }
        // Time Limit Exceeded
        return result;
    }

    public int maxArea1(int[] height) {
        int result = 0, l = 0, r = height.length - 1;
        while (l < r) {
            result = Math.max(result, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l--;
            } else {
                r--;
            }
        }
        return result;
    }


    public String intToRoman(int num) {
        int[] weight = {1, 5, 10, 50, 100, 500, 1000};
        char[] c = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        StringBuilder result = new StringBuilder();

        for (int i = 6; i >= 0; i -= 2) {
            int len = num / weight[i];
            while (len > 0) {
                if (len == 9) {
                    result.append("" + c[i] + c[i + 2]);
                    break;
                } else if (len == 4) {
                    result.append("" + c[i] + c[i + 1]);
                    break;
                } else if (len >= 5) {
                    result.append("" + c[i + 1]);
                    len -= 5;
                } else if(len >= 1){
                    result.append(c[i]);
                    len--;
                }
            }
            num = num % weight[i];
        }
        return result.toString();
    }

    public String intToRoman1(int num) {
        String[][] c={
                {"","I","II","III","IV","V","VI","VII","VIII","IX"},
                {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
                {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
                {"","M","MM","MMM"}
        };
        StringBuilder roman = new StringBuilder();
        roman.append(c[3][num / 1000 % 10]);
        roman.append(c[2][num / 100 % 10]);
        roman.append(c[1][num / 10 % 10]);
        roman.append(c[0][num % 10]);

        return roman.toString();
    }

    /**
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int[] weight = {1, 5, 10, 50, 100, 500, 1000};
        String cs = "IVXLCDM";
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            int index = cs.indexOf(s.charAt(i));
            if (i + 1 < s.length() && index < cs.indexOf(s.charAt(i + 1))) {
                result -= weight[index];
                continue;
            }
            result += weight[index];
        }
        return result;
    }

    /**
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String tem = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String common = "";
            for (int j = 0; j < Math.min(tem.length(), strs[i].length()); j++) {
                if (tem.charAt(j) == strs[i].charAt(j)) {
                    common += tem.charAt(j);
                } else {
                    break;
                }
            }
            tem = common;
        }
        return tem;
    }

    /**
     * leetcode question 3 Longest Substring Without Repeating Characters
     * Time complexity : O(n*n)
     * accept
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            Set<Character> hashSet = new HashSet<>();
            int j;
            for (j = i; j < chars.length && !hashSet.contains(chars[j]) ; j++) {
                hashSet.add(chars[j]);
            }
            ans = Math.max(ans, j - i);
        }
        return ans;
    }
    /**
     * leetcode question 3 Longest Substring Without Repeating Characters
     * Time complexity : O(n)
     * accept
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        int ans = 0;
        Set<Character> hashSet = new HashSet<>();
        int i = 0, j = 0;
        while(i < s.length() && j < s.length()) {
            if (hashSet.contains(s.charAt(j))) {
                hashSet.remove(s.charAt(i));
                i++;
            } else {
                hashSet.add(s.charAt(j));
                ans = Math.max(ans, j - i);
                j++;
            }
        }
        return ans;
    }

    /**
     * leetcode question 3 Longest Substring Without Repeating Characters
     * official answer
     * Time complexity : O(n)
     * use sliding window
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character, from 1
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    /**
     * leetcode question 4 Median of Two Sorted Arrays
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] tem = nums1;
            nums1 = nums2;
            nums2 = tem;
        }
        int m = nums1.length;
        int n = nums2.length;

        int i;
        int j;

        int left = 0;
        int right = m;
        while(left <= right) {
            i = (left + right) / 2;
            j = (m + n + 1) / 2 - i;

            if (i > 0 && nums1[i - 1] > nums2[j]) {
                right = i - 1;
                continue;
            }
            if (i < m && nums2[j - 1] > nums1[i]) {
                left = i  + 1;
                continue;
            }


            if ((m + n) % 2 == 0) {
                if (m == 0) {
                    if (n == 0) {
                        return 0.0;
                    }
                    if (n == 1) {
                        return nums2[0];
                    }
                    return (nums2[j - 1] + nums2[j]) / 2.0;
                }
                if (i == 0) {
                    if (j != n) {
                        return (nums2[j - 1] + Math.min(nums1[i], nums2[j])) / 2.0;
                    }
                    return (nums2[j - 1] + nums1[i]) / 2.0;
                }
                if (i == m) {
                    if (j == 0) {
                        return (nums1[i - 1] + nums2[j]) / 2.0;
                    }
                    return (Math.max(nums1[i - 1], nums2[j - 1]) + nums2[j]) / 2.0;
                }
                return (Math.max(nums1[i - 1], nums2[j - 1]) + Math.min(nums1[i], nums2[j])) / 2.0;
            }

            if (i == 0) {
                return nums2[j - 1];
            }
            if (j == 0) {
                return nums1[i - 1];
            }
            return Math.max(nums1[i - 1], nums2[j - 1]);
        }
        return 0.0;
    }

    /**
     * leetcode question 17 Letter Combinations of a Phone Number
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        if (digits.equals("")) {
            return result;
        }

        String[] letterMap = {" ", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        result.add("");
        for (int i = 0; i < digits.length(); i++) {
            String s = letterMap[digits.charAt(i) - '0'];
            int size = result.size();
            for (int j = 0; j < size; j++) {
                String prefix = result.get(0);
                result.remove(0);
                for (int k = 0; k < s.length(); k++) {
                    result.add(prefix + s.charAt(k));
                }
            }
        }
        return result;
    }

    /**
     * leetcode question 19. Remove Nth Node From End of List
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        ListNode q = head;
        for (int i = 0; i < n; i++) {
            p = p.next;
        }
        if (p == null) {
            //delete the first node
            return head.next;
        }
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
        return head;
    }

    /**
     * leetcode question 20. Valid Parentheses
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        String leftBracket = "([{";
        String rightBracket = ")]}";
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (rightBracket.indexOf(c) != -1) {
                //right bracket
                if (stack.size() == 0) {
                    return false;
                }
                char top = stack.getLast();
                if (leftBracket.indexOf(top) == rightBracket.indexOf(c)) {
                    stack.removeLast();
                } else {
                    return false;
                }
            } else {
                stack.addLast(c);
            }
        }
        return stack.size() == 0;
    }

    /**
     * leetcode question 20. Valid Parentheses
     * @param s
     * @return
     */
    public boolean isValid1(String s) {
        String leftBracket = "([{";
        String rightBracket = ")]}";
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (rightBracket.indexOf(c) != -1) {
                //right bracket
                if (stack.size() == 0 || leftBracket.indexOf(stack.removeLast()) != rightBracket.indexOf(c)) {
                    return false;
                }
            } else {
                stack.addLast(c);
            }
        }
        return stack.size() == 0;
    }

    /**
     * leetcode question 20. Valid Parentheses
     * a short answer
     * @param s
     * @return
     */
    public boolean isValid3(String s) {
        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    /**
     *
     * question 21. Merge Two Sorted Lists
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p = new ListNode(0);
        ListNode head = p;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 == null) {
            p.next = l2;
        }
        if (l2 == null) {
            p.next = l1;
        }
        return head.next;
    }

    /**
     * question 22. Generate Parentheses
     * 递推思想. 获取n-1的结果, 然后将"(",  ")"选择两个位置顺序插入
     * 试用TreeSet自动对结果进行去重和排序
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        Set<String> result = new TreeSet<>();
        if (n < 1) {
            return new ArrayList<>(result);
        }
        if (n == 1) {
            result.add("()");
            return new ArrayList<>(result);
        }
        List<String> strings = generateParenthesis(n - 1);
        for (String item : strings) {
            int length = item.length();
            for (int i = 0; i < length; i++) {
                StringBuilder sb = new StringBuilder(item);
                sb.insert(i, "(");
                for (int j = i + 1; j < length + 1; j++) {
                    StringBuilder sb2 = new StringBuilder(sb);
                    sb2.insert(j, ")");
                    result.add(sb2.toString());
                }
            }
        }
        return new ArrayList<>(result);
    }
}

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
