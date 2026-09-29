class Solution {
    private Boolean[][][] memo;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if ((m + n - 1) % 2 != 0) return false;
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;

        int maxBal = (m + n) / 2;
        memo = new Boolean[m][n][maxBal + 1];

        return dfs(0, 0, 0, grid, m, n, maxBal);
    }

    private boolean dfs(int r, int c, int bal, char[][] grid, int m, int n, int maxBal) {
        bal += (grid[r][c] == '(' ? 1 : -1);

        if (bal < 0 || bal > maxBal) return false;

        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        if (memo[r][c][bal] != null) {
            return memo[r][c][bal];
        }

        boolean found = false;
        if (r + 1 < m) {
            found = found || dfs(r + 1, c, bal, grid, m, n, maxBal);
        }
        if (c + 1 < n) {
            found = found || dfs(r, c + 1, bal, grid, m, n, maxBal);
        }

        return memo[r][c][bal] = found;
    }
}