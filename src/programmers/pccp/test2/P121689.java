package programmers.pccp.test2;

import java.util.LinkedList;
import java.util.Queue;

public class P121689 {
    class Solution {
        int maxWaitingPeople = Integer.MIN_VALUE;

        private static class Order {
            int arrivalTime;
            int menuIndex;

            public Order(int arrivalTime, int menu) {
                this.arrivalTime = arrivalTime;
                this.menuIndex = menu;
            }
        }

        public int solution(int[] menu, int[] order, int k) {
            int iterationCount = order.length;
            Queue<Order> readyQueue = new LinkedList<>();
            int index = 1;
            int endTime = 0;
            if (menu[order[0]] < k) {
                endTime = k;
            } else {
                endTime = menu[order[0]];
            }
            while (index < iterationCount) {
                if (readyQueue.isEmpty()) {
                    int nextArrival = index * k;
                    if (nextArrival >= endTime) {
                        endTime = nextArrival + menu[order[index]];
                        index++;
                    }
                } else {
                    Order poll = readyQueue.poll();
                    endTime += menu[poll.menuIndex];
                }

                // 최신화 된 endTime 이전의 도착한게 있다면 큐에 넣어주기
                while (index < iterationCount) {
                    int nextArrival = index * k;
                    if (nextArrival < endTime) {
                        readyQueue.add(new Order(nextArrival, order[index]));
                        index++;
                    } else {
                        break;
                    }
                }
                maxWaitingPeople = Integer.max(maxWaitingPeople, readyQueue.size() + 1);
            }
            return maxWaitingPeople;
        }
    }
}
