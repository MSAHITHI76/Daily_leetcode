class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0); // Base level score

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // Start a new nested level
            } else {
                int innerScore = stack.pop();
                int currentLevelScore = stack.pop();
                // If innerScore is 0, it means "()", which gives 1.
                // Otherwise, it's (A), which gives 2 * A.
                stack.push(currentLevelScore + Math.max(2 * innerScore, 1));
            }
        }

        return stack.pop();
    }
}