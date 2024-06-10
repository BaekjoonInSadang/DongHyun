package programmers.pccp;

public class P121685 {
    class Solution {

        private static class Bean {
            String gene;
            Bean[] descendants;
            int generation;

            public Bean(String value, int generation) {
                this.gene = value;
                this.generation = generation;
            }

            private void setDescendants() {
                descendants = new Bean[4];
                StringBuilder sb;
                for (int i = 0; i < 4; i++) {
                    sb = new StringBuilder();
                    if (i == 0) {
                        sb.append(gene.charAt(0));
                        sb.append(gene.charAt(0));
                        descendants[i] = new Bean(sb.toString(), this.generation + 1);
                    } else if (i == 3) {
                        sb.append(gene.charAt(1));
                        sb.append(gene.charAt(1));
                        descendants[i] = new Bean(sb.toString(), this.generation + 1);
                    } else {
                        sb.append(gene.charAt(0));
                        sb.append(gene.charAt(1));
                        descendants[i] = new Bean(sb.toString(), this.generation + 1);
                    }
                }

            }


        }

        public String[] solution(int[][] queries) {
            String[] answer = new String[queries.length];
            for (int i = 0; i < queries.length; i++) {
                int generation = queries[i][0];
                int index = queries[i][1];
                answer[i] = getGene(generation, index);
            }
            return answer;
        }

        private String getGene(int generation, int index) {
            Bean root = new Bean("Rr", 1);
            Bean temp = root;
            int[] indexes = getNumberingByDepth(generation, index);
            if (indexes.length == 0) {
                return "Rr";
            }
            for (int i = 0; i < indexes.length; i++) {
                temp.setDescendants();
                temp = temp.descendants[indexes[i]];
            }
            return temp.gene;
        }

        private int[] getNumberingByDepth(int generation, int index) {
            int[] nextIndex = new int[generation - 1];
            for (int i = nextIndex.length - 1; i >= 0; i--) {
                nextIndex[i] = (index - 1) % 4;
                index = (index - 1) / 4 + 1;
            }
            return nextIndex;
        }


    }
}
