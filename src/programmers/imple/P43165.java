package programmers.imple;

public class P43165 {
    int count = 0;
    private static int[] number;
    private static int targetNumber;
    public int solution(int[] numbers, int target) {
        targetNumber = target;
        number = numbers;
        dfs(0,0);
        return count;
    }
    private void dfs(int value, int depth){
        if(depth == number.length){
            if(value == targetNumber){
                count++;
            }
            return;
        }
        dfs(value + number[depth], depth+1);
        dfs(value - number[depth], depth+1);
    }
}
