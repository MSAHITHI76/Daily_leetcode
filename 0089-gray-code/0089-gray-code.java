import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int totalNumbers = 1 << n; // 2^n elements
        
        for (int i = 0; i < totalNumbers; i++) {
            result.add(i ^ (i >> 1)); // Bitwise formula for Gray code
        }
        
        return result;
    }
}