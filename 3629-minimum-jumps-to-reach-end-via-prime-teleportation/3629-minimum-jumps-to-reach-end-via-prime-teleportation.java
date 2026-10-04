import java.util.*;

class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // 1. Find the maximum value in nums to build the Sieve of Eratosthenes
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // 2. Precompute smallest prime factor (SPF) for prime factorization up to maxVal
        int[] spf = new int[maxVal + 1];
        for (int i = 2; i <= maxVal; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        // 3. Map each prime factor to all indices j where nums[j] is divisible by prime p
        Map<Integer, List<Integer>> primeToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int temp = nums[i];
            while (temp > 1) {
                int p = spf[temp];
                primeToIndices.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
                while (temp % p == 0) {
                    temp /= p;
                }
            }
        }

        // 4. BFS to find the shortest path from index 0 to n - 1
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedIndex = new boolean[n];
        Set<Integer> visitedPrimes = new HashSet<>();

        queue.offer(0);
        visitedIndex[0] = true;
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();

                if (curr == n - 1) {
                    return jumps;
                }

                // Option 1: Adjacent steps (curr - 1, curr + 1)
                int[] nextIndices = {curr - 1, curr + 1};
                for (int next : nextIndices) {
                    if (next >= 0 && next < n && !visitedIndex[next]) {
                        visitedIndex[next] = true;
                        queue.offer(next);
                    }
                }

                // Option 2: Prime Teleportation if nums[curr] is prime
                int val = nums[curr];
                if (val >= 2 && spf[val] == val) { // val is prime
                    int prime = val;
                    if (!visitedPrimes.contains(prime)) {
                        visitedPrimes.add(prime);
                        List<Integer> targetIndices = primeToIndices.getOrDefault(prime, Collections.emptyList());
                        for (int target : targetIndices) {
                            if (!visitedIndex[target]) {
                                visitedIndex[target] = true;
                                queue.offer(target);
                            }
                        }
                    }
                }
            }
            jumps++;
        }

        return -1;
    }
}