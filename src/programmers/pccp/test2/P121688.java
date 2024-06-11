package programmers.pccp.test2;

import java.util.PriorityQueue;

public class P121688 {

    public int solution(int[] ability, int number) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int ab : ability) {
            queue.add(ab);
        }
        for (int i = 0; i < number; i++) {
            int a = queue.poll();
            int b = queue.poll();
            queue.add(a + b);
            queue.add(a + b);
        }

        int sum = queue.stream().mapToInt(Integer::intValue).sum();
        return sum;
    }
}
