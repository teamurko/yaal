package on2015_02.on2015_02_01_SNWS2015.E;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class E {
    class Task {
        final int s;
        final int f;
        final int t;

        Task(int s, int f, int t) {
            this.s = s;
            this.f = f;
            this.t = t;
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        Task[] tasks = new Task[n];
        for (int i = 0; i < n; ++i) {
            int s = in.readInt();
            int f = in.readInt();
            int t = in.readInt();
            tasks[i] = new Task(s, f, t);
        }
        Arrays.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.f + o1.s - o2.f - o2.s;
            }
        });

        boolean ok = true;
        int last = 0;
        for (Task task : tasks) {
            last = Math.max(last, task.s);
            if (last + task.t > task.f) {
                ok = false;
                break;
            }
            last += task.t;
        }
        if (ok) out.printLine(1);
        else out.printLine(0);
    }
}
