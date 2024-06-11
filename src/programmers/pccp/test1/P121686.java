package programmers.pccp.test1;

import java.util.Arrays;
import java.util.PriorityQueue;

public class P121686 {


    private static class Program implements Comparable<Program> {
        int score;
        int runningTime;
        int arrivalTime;

        public Program(int score, int arrivalTime, int runningTime) {
            this.score = score;
            this.arrivalTime = arrivalTime;
            this.runningTime = runningTime;
        }

        private long getEndTime(long startTime) {
            return (long) startTime + runningTime;
        }

        @Override
        public int compareTo(Program p) {
            if (score == p.score) {
                return Integer.compare(this.arrivalTime, p.arrivalTime);
            } else {
                return Integer.compare(this.score, p.score);
            }
        }
    }

    public long[] solution(int[][] program) {
        long[] answer = new long[11];
        Arrays.sort(program, (a1, a2) -> {
            if (a1[1] == a2[1]) {
                return Integer.compare(a1[0], a2[0]);
            } else {
                return Integer.compare(a1[1], a2[1]);
            }
        });
        PriorityQueue<Program> readyQueue = new PriorityQueue<>();
        long endTime = program[0][1];
        int count = 0;
        int idx = 0;

        while (count < program.length) {
            // 큐가 비어있다면 시간 순 다음거 처리
            if (readyQueue.isEmpty() && idx < program.length) {
                Program process = new Program(program[idx][0], program[idx][1], program[idx][2]);
                if (program[idx][1] != endTime) {
                    endTime = program[idx][1];
                }
                idx++;
                count++;
                endTime = process.getEndTime(endTime);
            }
            // 레디 큐에 존재 하면 꺼내면서 카운트 증가 및 로직 처리
            else {
                Program process = readyQueue.poll();
                answer[process.score] += (endTime - process.arrivalTime);
                count++;
                endTime += process.runningTime;
            }
            // 현재 프로세스 끝나기전에 도착한 프로그램 readyQueue에 넣어줌.
            while (true) {
                if (idx < program.length && program[idx][1] <= endTime) {
                    readyQueue.add(new Program(program[idx][0], program[idx][1], program[idx][2]));
                    idx++;
                } else {
                    break;
                }
            }
        }
        answer[0] = endTime;
        return answer;
    }

}

