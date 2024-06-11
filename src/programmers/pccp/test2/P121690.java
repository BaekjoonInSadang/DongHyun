package programmers.pccp.test2;

import java.util.LinkedList;
import java.util.Queue;

public class P121690 {
    int[] dirX = {0, 0, 1, -1};
    int[] dirY = {1, -1, 0, 0};
    int[] jDirX = {0, 0, 2, -2};
    int[] jDirY = {2, -2, 0, 0};
    boolean[][][] visited;
    int[][] map;

    private static class Location {
        int x;
        int y;
        int count;
        int jumpCount;

        public Location(int x, int y, int count, int jumpCount) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.jumpCount = jumpCount;
        }

    }

    public int solution(int n, int m, int[][] hole) {
        map = new int[n + 1][m + 1];
        for (int[] ints : hole) {
            map[ints[0]][ints[1]] = 1;
        }
        int answer = Integer.MAX_VALUE;
        visited = new boolean[2][n + 1][m + 1];
        Queue<Location> queue = new LinkedList<>();
        visited[0][1][1] = true;
        visited[1][1][1] = true;
        queue.add(new Location(1, 1, 0, 0));
        while (!queue.isEmpty()) {
            Location now = queue.poll();
            if (now.x == n && now.y == m) {
                answer = Integer.min(answer, now.count);
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if (nextX >= 1 && nextX <= n && nextY >= 1 && nextY <= m && map[nextX][nextY] != 1
                        && !visited[now.jumpCount][nextX][nextY]) {
                    queue.add(new Location(nextX, nextY, now.count + 1, now.jumpCount));
                    visited[now.jumpCount][nextX][nextY] = true;
                }
            }
            if (now.jumpCount == 0) {
                for (int i = 0; i < 4; i++) {
                    int nextX = now.x + jDirX[i];
                    int nextY = now.y + jDirY[i];
                    if (nextX >= 1 && nextX <= n && nextY >= 1 && nextY <= m && map[nextX][nextY] != 1
                            && !visited[1][nextX][nextY]) {
                        queue.add(new Location(nextX, nextY, now.count + 1, now.jumpCount + 1));
                        visited[1][nextX][nextY] = true;
                    }
                }
            }

        }

        if (answer == Integer.MAX_VALUE) {
            return -1;
        } else {
            return answer;

        }
    }
}
