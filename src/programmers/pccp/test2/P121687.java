package programmers.pccp.test2;

public class P121687 {

    // 상하좌우
    int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private static class Robot {
        int x;
        int y;
        int[] dir;

        public Robot() {
            x = 0;
            y = 0;
            dir = new int[]{0, 1};
        }

        private void changeDirection(char ch) {
            if (ch == 'R') {
                // 오른쪽으로 90도 회전
                int temp = dir[0];
                dir[0] = dir[1];
                dir[1] = -temp;
            } else {
                // 왼쪽으로 90도 회전
                int temp = dir[0];
                dir[0] = -dir[1];
                dir[1] = temp;
            }
        }

        private void move(char ch) {
            if (ch == 'G') {
                x += dir[0];
                y += dir[1];
            } else {
                x -= dir[0];
                y -= dir[1];
            }

        }
    }


    public int[] solution(String command) {
        Robot robot = new Robot();
        for (int i = 0; i < command.length(); i++) {
            char order = command.charAt(i);
            if (order == 'G' || order == 'B') {
                robot.move(order);
            } else {
                robot.changeDirection(order);
            }
        }
        return new int[]{robot.x, robot.y};
    }
}
