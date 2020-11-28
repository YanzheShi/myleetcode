package com.github.yanzheshi;

import java.util.Deque;
import java.util.LinkedList;

public class NowCoder {
    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public int upper_bound_ (int n, int v, int[] a) {
        // write code here
        int mid = n - 1;
        int low = 0;
        int high = n - 1;

        while(low < high)
        {
            mid = (low + high) / 2;
            if(a[mid] >= v){
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        if (a[low] >= v) {
            return low+1;
        }
        return n+1;
    }

    public int calculateMax(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;

        for (int curPrice : prices) {
            firstBuy = Math.max(firstBuy, -curPrice);
            firstSell = Math.max(firstSell, firstBuy + curPrice);
            secondBuy = Math.max(secondBuy,firstSell - curPrice);
            secondSell = Math.max(secondSell, secondBuy + curPrice);
        }
        return secondSell;
    }

    public static int getMaxArea(int[] height) {

        int length = height.length;

        int[] leftmargin = new int[length];
        int[] rightmargin = new int[length];

        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < length; i++){
            while(!stack.isEmpty() && height[i] < height[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                leftmargin[i] = 0;
            } else{
                leftmargin[i] = stack.peek() + 1;

            }
            stack.push(i);

        }
        
        stack.clear();
        for(int i = length - 1; i >= 0; i--){
            while(!stack.isEmpty() && height[i] < height[stack.peek()]){
                stack.pop();
            }
            if(stack.isEmpty()){
                rightmargin[i] = length - 1;
            } else{
                rightmargin[i] = stack.peek() - 1;

            }
            stack.push(i);
        }

        int res = 0;
        for(int i = 0; i < length ; i++){
            res = Math.max(res, height[i] * (rightmargin[i] - leftmargin[i] + 1));

        }
        return res;

    }

    public int[] LIS (int[] arr) {
        // write code here
        int[][] dp = new int[arr.length][arr.length];
        int []maxLen = new int[arr.length];
        dp[0][0] = arr[0];
        maxLen[0] = 1;
        int resLen = 1;
        for(int i = 1; i < arr.length; i++){

            int j;
            for(j = 0 ;j < maxLen[i - 1] && arr[i] > dp[i-1][j]  ; j++){
                dp[i][j] = dp[i - 1][j];
            }
            dp[i][j] = arr[i];
            maxLen[i] = j+1;
            resLen = Math.max(resLen, maxLen[i]);
        }
        int[] res = new int[resLen];


        int minEnd = Integer.MAX_VALUE;
        int resIndex = 0;
        for(int i = 0; i < maxLen.length; i++){
            if(maxLen[i] == resLen && arr[i] < minEnd){
                resIndex = i;
            }
        }
        for(int j = 0; j < resLen; j++){
            res[j] = dp[resIndex][j];
        }

        return res;

    }

    public int[] LIS1 (int[] arr) {
        // write code here
        int[] dp = new int[arr.length];
        int []maxLen = new int[arr.length];
        int [] res = null;
        dp[0] = arr[0];
        maxLen[0] = 1;
        int resLen = 1;
        int tail = arr[0];
        for(int i = 1; i < arr.length; i++){

            int j;
            for(j = 0 ;j < maxLen[i - 1] && arr[i] > dp[j]  ; j++){
            }
            dp[j] = arr[i];
            maxLen[i] = j + 1;
            if(resLen < maxLen[i] || (resLen == maxLen[i] && arr[i] < tail)){
                resLen = maxLen[i];
                tail = arr[i];
                res = new int[resLen];
                System.arraycopy(dp, 0, res, 0, resLen);
            }
            resLen = Math.max(resLen, maxLen[i]);

        }
        return res;

    }





    




}
