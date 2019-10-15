package com.hzy.leetcode;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 寻找两个有序数组中的中位数 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int num1Total = nums1.length;
        int num2Total = nums2.length;
        int middle1Index = num1Total/2;
        double middle1 = -1;
        if(num1Total%2==0){
            middle1 = (nums1[middle1Index-1]+nums1[middle1Index])/2.0;
        }else{
            middle1 = nums1[middle1Index];
        }
        int middle2Index = num2Total/2;
        double middle2 = -1;
        if(num2Total%2==0){
            middle2 = (nums2[middle2Index-1]+nums1[middle2Index])/2.0;
        }else{
            middle2 = nums2[middle2Index];
        }
        if(middle1==middle2){
            return middle1;
        }
        if(middle1>middle2){//确保nums1中位数小于nums2
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        int max = num1Total>num2Total? num1Total:num2Total;
        int tmp1 = (int)middle1;
        int tmp2 = (int)middle2;
        for (int i = 0; i < max; i++) {
            if((int)middle1Index+i>=num1Total){
                return (tmp1+tmp2)/2;
            }
            tmp1 = nums1[(int)middle1Index+i];
            if(middle2-i<0){
                return nums1[(num1Total+num1Total)/2];
            }
            tmp2 = nums2[(int)middle2Index-i];
            if(tmp1>tmp2){
                return tmp1;
            }else{
                tmp2 = nums2[(int)middle2Index-i-1];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        int[] nums2 = {3,4};
        System.out.println(findMedianSortedArrays(nums1,nums2));
    }
}
