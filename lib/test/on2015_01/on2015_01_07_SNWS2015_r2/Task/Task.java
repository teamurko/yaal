package on2015_01.on2015_01_07_SNWS2015_r2.Task;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Task {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int a = in.readInt();
        int b;
        if (a <= 180) b = a + 180;
        else b = a - 180;
        if (a > b) {
            int c = a;
            a = b;
            b = c;
        }
        if (a < 5) {
            b = (b + 5) / 10;
            if (b < 10) out.print(0);
            out.printLine(b);
        } else {
            a = (a + 5) / 10;
            if (a < 10) out.print(0);
            out.printLine(a);
        }
    }
}
