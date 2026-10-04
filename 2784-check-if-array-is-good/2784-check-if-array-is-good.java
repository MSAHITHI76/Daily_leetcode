import java.util.Arrays;

class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;
        
        // base[n] must have length n + 1, so maximum element must be n
        if (n <= 0) {
            return false;
        }

        // Sort the array to easily check the required permutation
        Arrays.sort(nums);

        // Check if first n - 1 elements are 1, 2, ..., n - 1
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return false;
            }
        }

        // The last element (at index n) must also be equal to n
        return nums[n] == n;
    }
}