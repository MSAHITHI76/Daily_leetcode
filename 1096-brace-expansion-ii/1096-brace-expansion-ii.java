import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(expression);
        Set<String> resultSet = new HashSet<>();

        while (!queue.isEmpty()) {
            String expr = queue.poll();

            // If there are no closing braces, it's a plain string or comma-separated tokens
            if (!expr.contains("}")) {
                for (String str : expr.split(",")) {
                    resultSet.add(str);
                }
                continue;
            }

            // Find the first closing brace '}' and its matching opening brace '{'
            int right = expr.indexOf("}");
            int left = expr.lastIndexOf("{", right);

            String before = expr.substring(0, left);
            String inside = expr.substring(left + 1, right);
            String after = expr.substring(right + 1);

            // Expand the innermost brace content separated by commas
            for (String sub : inside.split(",")) {
                queue.offer(before + sub + after);
            }
        }

        List<String> sortedList = new ArrayList<>(resultSet);
        Collections.sort(sortedList);
        return sortedList;
    }
}