class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] mapS = new int[256];
        int[] mapT = new int[256];
        
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            
            // If the characters have been seen before, check if their previous mappings match
            if (mapS[c1] != mapT[c2]) {
                return false;
            }
            
            // Store the 1-based index of the current occurrence
            mapS[c1] = i + 1;
            mapT[c2] = i + 1;
        }
        
        return true;
    }
}