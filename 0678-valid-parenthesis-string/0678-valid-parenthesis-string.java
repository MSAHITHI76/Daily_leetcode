class Solution {
    public boolean checkValidString(String s) {
        // minOpen represents the minimum possible number of open parentheses needed.
        // maxOpen represents the maximum possible number of open parentheses needed.
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else if (c == '*') {
                minOpen--; // Treat '*' as ')'
                maxOpen++; // Treat '*' as '('
            }

            // We cannot have more closing brackets than opening brackets
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative (we can treat extra '*' as empty string "")
            if (minOpen < 0) {
                minOpen = 0;
            }
        }

        // If minOpen is 0, all open parentheses can be matched properly
        return minOpen == 0;
    }
}