class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        // DP table initialized with Integer.MAX_VALUE
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        // Base conditions for reaching past the destination room
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        
        // Fill DP table backward
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int minNextHealth = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(1, minNextHealth - dungeon[i][j]);
            }
        }
        
        return dp[0][0];
    }
}