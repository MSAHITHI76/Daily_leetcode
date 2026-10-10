import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        
        // If lengths don't match, bijection is impossible
        if (words.length != pattern.length()) {
            return false;
        }
        
        Map<Character, String> charToWord = new HashMap<>();
        Set<String> usedWords = new HashSet<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String word = words[i];
            
            if (charToWord.containsKey(ch)) {
                // Check if existing mapping matches current word
                if (!charToWord.get(ch).equals(word)) {
                    return false;
                }
            } else {
                // If character is new, ensure the word isn't already assigned to another char
                if (usedWords.contains(word)) {
                    return false;
                }
                charToWord.put(ch, word);
                usedWords.add(word);
            }
        }
        
        return true;
    }
}