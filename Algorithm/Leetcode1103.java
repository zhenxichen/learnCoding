public class Leetcode1103 {
    /* Leetcode 1103 Distribute Candies to People */
    /**
     * We distribute some number of candies, to a row of n = num_people people in the following way:
     * We then give 1 candy to the first person, 2 candies to the second person,
     * and so on until we give n candies to the last person.
     * Then, we go back to the start of the row, giving n + 1 candies to the first person,
     * n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.
     * This process repeats (with us giving one more candy each time, and moving to the start of the row after
     * we reach the end) until we run out of candies.
     * The last person will receive all of our remaining candies (not necessarily one more than the previous gift).
     * Return an array (of length num_people and sum candies) that represents the final distribution of candies.*/

    public static void main(String [] args){
        Solution solution = new Solution();
    }
}

class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] distribute = new int[num_people];
        int i = 0;
        int count = 1;
        while(candies > 0){
            if(i == num_people){ i = 0; }
            if(candies > count){        //可以正常发放
                distribute[i] += count;
                candies -= count;
            }
            else{
                distribute[i] += candies;   //剩下的全分
                candies = 0;
            }
            i++;
            count++;
        }
        return distribute;
    }
}
