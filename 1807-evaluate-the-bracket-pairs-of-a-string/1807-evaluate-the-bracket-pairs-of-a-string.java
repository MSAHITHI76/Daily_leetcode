import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Build a hash map for quick key-value lookups
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        int n = s.length();
        int i = 0;

        while (i < n) {
            char ch = s.charAt(i);
            if (ch == '(') {
                // Find the closing bracket
                int j = i + 1;
                while (j < n && s.charAt(j) != ')') {
                    j++;
                }
                // Extract the key inside brackets
                String key = s.substring(i + 1, j);
                // Append the corresponding value or '?' if key is not present
                result.append(map.getOrDefault(key, "?"));
                i = j + 1; // Move past the closing bracket
            } else {
                result.append(ch);
                i++;
            }
        }

        return result.toString();
    }
}