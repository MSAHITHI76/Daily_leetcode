class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int sum = 0;
        
        // Since the array is 1-indexed for the problem logic, 
        // we loop from i = 1 to n.
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                // Map 1-indexed i to 0-indexed array (i - 1)
                sum += nums[i - 1] * nums[i - 1];
            }
        }
        
        return sum;
    }
}