package recursionquiz;


public class RecursionQuiz {

    /* Write a RECURSIVE solution that returns the count 
     * how many ODD digits are in the number
     *
     * Examples:
     * countOdds(0) -> 0
     * countOdds(443) -> 1
     * countOdds(1234567) -> 4
     * countOdds(531) -> 3
     * countOdds(246) -> 0
     *
     * For part marks, you can find how many 
     * digits are in the number
    */
    public int countOdds(int n) {
       return n == 0 ? 0 : ((n % 2 == 1 ? 1 : 0) + countOdds(n / 10));
    }
    
    
    public static void main(String[] args) {
        RecursionQuiz rq = new RecursionQuiz();
        System.out.println(rq.countOdds(0));
        System.out.println(rq.countOdds(443));
        System.out.println(rq.countOdds(1234567));
        System.out.println(rq.countOdds(531));
        System.out.println(rq.countOdds(246));
    }   
    
}
