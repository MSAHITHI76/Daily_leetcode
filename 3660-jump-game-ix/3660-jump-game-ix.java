class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        // Store suffix minimums
        int[] sufMin = new int[n];
        sufMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMin[i] = Math.min(sufMin[i + 1], nums[i]);
        }

        int preMax = 0;
        int currentCompMax = 0;
        int start = 0;

        for (int i = 0; i < n; i++) {
            preMax = Math.max(preMax, nums[i]);
            currentCompMax = Math.max(currentCompMax, nums[i]);

            // Split into components when prefix max <= suffix min of remaining
            if (i == n - 1 || preMax <= sufMin[i + 1]) {
                for (int j = start; j <= i; j++) {
                    ans[j] = currentCompMax;
                }
                start = i + 1;
                currentCompMax = 0;
            }
        }

        return ans;
    }
}