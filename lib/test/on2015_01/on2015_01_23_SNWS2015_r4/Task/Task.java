package on2015_01.on2015_01_23_SNWS2015_r4.Task;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Task {
    class Node {
        public final int income;
        public final int fine;
        public final int id;

        Node(int income, int fine, int id) {
            this.income = income;
            this.fine = fine;
            this.id = id;
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int s = in.readInt() - 1;
        Node[] nodes = new Node[n];
        int count = 1;
        int cost = 0;
        for (int i = 0; i < n; ++i) {
            int a = in.readInt();
            int b = in.readInt();
            nodes[i] = new Node(a, b, i);
            if (i == s) {
                cost += a;
            }
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.fine - o2.fine;
            }
        });
        for (Node node : nodes) {
            if (node.id == s) continue;
            if (node.income >= node.fine) {
                if (node.fine <= cost) {
                    cost += node.income - node.fine;
                    ++count;
                }
            }
        }
        out.printLine(cost);
        out.printLine(count);
    }
}
