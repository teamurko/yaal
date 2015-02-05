package on2015_01.on2015_01_23_SNWS2015_r4.A;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        long sum = 0;
        long mx = 0;
        for (int i = 0; i < n; ++i) {
            long x = in.readInt();
            mx = Math.max(mx, x);
            sum += x;
        }
        if (sum - mx < mx) {
            out.printLine(mx);
        } else {
            out.printLine((sum + 1) / 2);
        }
    }
}
