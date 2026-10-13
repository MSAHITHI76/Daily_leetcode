import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squared = new long[n];
        
        // Take absolute value and compute squares to avoid overflow and handle signs
        for (int i = 0; i < n; i++) {
            long val = Math.abs((long) nums[i]);
            squared[i] = val * val;
        }
        
        // Sort the squared values in ascending order
        Arrays.sort(squared);
        
        long score = 0;
        
        // Subtract the smaller half and add the larger half
        for (int i = 0; i < n; i++) {
            if (i < n / 2) {
                score -= squared[i];
            } else {
                score += squared[i];
            }
        }
        
        return score;
    }
}