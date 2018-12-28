package solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by U6035183 on 2018/12/11.
 */
public class LongestIncreasingSubsequence300 {
  /**
   *Given an unsorted array of integers, find the length of longest increasing subsequence.

   Example:

   Input: [10,9,2,5,3,7,101,18]
   Output: 4
   Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
   */
  public int longestSubsequece(int[] array) {
    if (array.length == 0) return 0;
    int max = 0;
    for( int i = 0; i < array.length; i++) {
      List<Integer> list = new ArrayList();
      int front = array[i];
      for(int j = i+1; j < array.length; j++) {
        if (array[j] > array[i]) list.add(array[j]);
      }
      int[] tempArray = new int[list.size()];
      for(int k = 0; k< list.size(); k++) {
        tempArray[k] = list.get(k);
      }
      max = Math.max(max, 1 + longestSubsequece(tempArray));

    }
    return max;

  }
}
