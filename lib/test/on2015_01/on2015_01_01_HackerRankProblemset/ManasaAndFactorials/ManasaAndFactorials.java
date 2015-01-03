package on2015_01.on2015_01_01_HackerRankProblemset.ManasaAndFactorials;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ManasaAndFactorials {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        testNumber = in.readInt();
        for (int it = 0; it < testNumber; ++it) {
            long numEndZeros = in.readLong();
            out.printLine(factorialBaseWith(numEndZeros));
        }
    }

    private long factorialBaseWith(long numEndZeros) {
        long l = 0;
        long r = numEndZeros * 5;
        while (l < r) {
            long m = (l + r) >> 1;
            if (numEndZeros(m) < numEndZeros) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    private long numEndZeros(long m) {
        long res = 0;
        for (long divisor = 5; divisor <= m; divisor *= 5) {
            res += m / divisor;
        }
        return res;
    }
}
