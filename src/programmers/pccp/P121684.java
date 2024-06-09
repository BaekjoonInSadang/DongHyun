package programmers.pccp;

public class P121684 {
    class Solution {
        int max = Integer.MIN_VALUE;
        int maxDepth;
        boolean[] visited;

        public int solution(int[][] ability) {
            maxDepth = ability[0].length;
            visited = new boolean[ability.length];
            dfs(ability, 0, 0);
            return max;
        }

        private void dfs(int[][] ability, int depth, int sum) {
            if (depth == maxDepth) {
                max = Integer.max(max, sum);
                return;
            }
            for (int i = 0; i < ability.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(ability, depth + 1, sum + ability[i][depth]);
                    visited[i] = false;
                }
            }
        }
    }
}
