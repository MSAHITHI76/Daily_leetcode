import java.util.HashMap;
import java.util.Map;

class Solution {
    static final int MOD = 1_000_000_007;

    long[] tree;
    long[] lazy;

    // Push lazy updates down the segment tree
    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(node * 2, l, mid, lazy[node]);
            apply(node * 2 + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        // (x + v)^2 = x^2 + 2*v*x + v^2 * (length)
        long len = (r - l + 1);
        tree[node] = (tree[node] + 2 * val * querySum(node, l, r) + val * val % MOD * len) % MOD;
        lazy[node] = (lazy[node] + val) % MOD;
    }

    // We also need a helper segment tree or structure to keep track of the linear sum (sum of counts)
    // To keep the code clean and self-contained, we can embed both or maintain the linear sum alongside.
    // Below is the standard structure tailored for this problem using dual segment arrays.
    
    long[] sumTree;
    long[] sumLazy;

    private void pushSum(int node, int l, int r) {
        if (sumLazy[node] != 0) {
            int mid = (l + r) / 2;
            applySum(node * 2, l, mid, sumLazy[node]);
            applySum(node * 2 + 1, mid + 1, r, sumLazy[node]);
            sumLazy[node] = 0;
        }
    }

    private void applySum(int node, int l, int r, long val) {
        long len = (r - l + 1);
        sumTree[node] = (sumTree[node] + val * len) % MOD;
        sumLazy[node] = (sumLazy[node] + val) % MOD;
    }

    private long querySum(int node, int l, int r) {
        return sumTree[node];
    }

    private void update(int node, int l, int r, int ql, int qr, long val) {
        if (ql > r || qr < l) return;
        if (ql <= l && r <= qr) {
            apply(node, l, r, val);
            applySum(node, l, r, val);
            return;
        }
        push(node, l, r);
        pushSum(node, l, r);
        int mid = (l + r) / 2;
        update(node * 2, l, mid, ql, qr, val);
        update(node * 2 + 1, mid + 1, r, ql, qr, val);
        tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD;
        sumTree[node] = (sumTree[node * 2] + sumTree[node * 2 + 1]) % MOD;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new long[4 * n];
        lazy = new long[4 * n];
        sumTree = new long[4 * n];
        sumLazy = new long[4 * n];

        Map<Integer, Integer> lastOccurrence = new HashMap<>();
        long totalSumOfSquares = 0;
        long currentSumOfSquares = 0;

        for (int i = 0; i < n; i++) {
            int lastIdx = lastOccurrence.getOrDefault(nums[i], -1);
            // Update range [lastIdx + 1, i] by adding 1
            update(1, 0, n - 1, lastIdx + 1, i, 1);
            lastOccurrence.put(nums[i], i);

            // The root of the segment tree now holds the total sum of squares for subarrays ending at i
            totalSumOfSquares = (totalSumOfSquares + tree[1]) % MOD;
        }

        return (int) ((totalSumOfSquares + MOD) % MOD);
    }
}