package on2015_01.on2015_01_02_HackerRankProblemset.ClosestNumber;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ClosestNumber {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.readInt();
        long b = in.readInt();
        long x = in.readInt();
        if (b >= 0) {
            out.printLine(closestMultiple(IntegerUtils.power(a, b), x));
        } else if (a == 1) {
            out.printLine(closestMultiple(1, x));
        } else {
            out.printLine(0);
        }
    }

    private long closestMultiple(long a, long x) {
        long d = a / x;
        if (a - d * x <= (d + 1) * x - a) {
            return d * x;
        } else {
            return (d + 1) * x;
        }
    }
}
