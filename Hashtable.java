package com.demo;

// [3,2,4] 6
// => [2,3]

import java.util.Hashtable;
import java.util.Scanner;

public class Demo1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        String[] ss = str.split(" ");
        int[] nums = new int[ss.length];
        for (int i = 0; i < ss.length; i++) {
            nums[i] = Integer.parseInt(ss[i]);
        }

        int target = sc.nextInt();
        int[] res = twoSum(nums, target);
        for (int i = 0; i < 2; i++)
            System.out.print(res[i] + " ");
    }

    public static int[] twoSum(int[] nums, int tar) {
        int[] res = new int[2];
        Hashtable<Integer, Integer> dic = new Hashtable<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (dic.containsKey(tar - nums[i])) {
                res[0] = i;
                res[1] = dic.get(tar - nums[i]);
            } else {
                dic.put(nums[i], i);
            }
        }
        if (res[0] > res[1]) {
            int tmp = res[0];
            res[0] = res[1];
            res[1] = tmp;
        }
        return res;
    }
}