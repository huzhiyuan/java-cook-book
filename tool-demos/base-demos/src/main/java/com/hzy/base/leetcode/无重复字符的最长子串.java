package com.hzy.leetcode;

/**
 * TODO 击败5%的人, 待优化
 */
public class 无重复字符的最长子串 {
    public static int lengthOfLongestSubstring(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        int maxLength = 0;
        for(int i=0;i<s.length();i++){
            for(int j=i;j<s.length();j++){
                char si = s.charAt(j);
                String subStr = s.substring(i,j);
                if(subStr.indexOf(si)> -1){
                    break;
                }else{
                    int len = j+1-i;
                    if(len > maxLength){
                        maxLength = len;
                    }
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcdaer"));
        System.out.println(lengthOfLongestSubstring("abcdefghij"));
        System.out.println(lengthOfLongestSubstring(""));
        System.out.println(lengthOfLongestSubstring(" "));
    }

}
