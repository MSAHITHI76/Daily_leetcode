class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the maximum possible sum with 'num' digits is less than 'sum', 
        // or if 0 sum requires positive digits, it's impossible.
        if ((long) num * 9 < sum) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int digit = Math.min(9, sum);
            sb.append(digit);
            sum -= digit;
        }
        
        // If sum is left over, we couldn't fit it into 'num' digits (though the first check handles most of this)
        if (sum > 0) {
            return "";
        }
        
        return sb.toString();
    }
}