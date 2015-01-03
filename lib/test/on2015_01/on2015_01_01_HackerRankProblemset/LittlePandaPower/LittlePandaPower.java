package on2015_01.on2015_01_01_HackerRankProblemset.LittlePandaPower;



import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class LittlePandaPower {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long a = in.readInt();
        long b = in.readInt();
        long x = in.readInt();
        if (b < 0) {
            b = -b;
            a = IntegerUtils.power(a, phi(x) - 1, x);
        }
        out.printLine(IntegerUtils.power(a, b, x));
    }

    private long phi(long x) {
        long res = 1;
        for (Pair<Long, Integer> e : IntegerUtils.factorize(x)) {
            res *= IntegerUtils.power(e.first, e.second - 1);
            res *= e.first - 1;
        }
        return res;
    }

}
