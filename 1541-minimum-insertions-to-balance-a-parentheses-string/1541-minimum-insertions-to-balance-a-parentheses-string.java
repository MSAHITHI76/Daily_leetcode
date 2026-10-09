class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int openNeeded = 0; // Tracks the number of required ')'
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                // If openNeeded is odd, it means the previous '(' received only one ')'
                if (openNeeded % 2 != 0) {
                    insertions++; // Insert one ')' to close it
                    openNeeded--;
                }
                openNeeded += 2; // Each '(' needs two ')'
            } else { // c == ')'
                openNeeded--;
                // Encountered ')' without a matching '('
                if (openNeeded < 0) {
                    insertions++; // Insert one '('
                    openNeeded += 2; // The inserted '(' needs two ')' (one is current 'c')
                }
            }
        }
        
        // Add remaining ')' required to balance any leftover '('
        return insertions + openNeeded;
    }
}