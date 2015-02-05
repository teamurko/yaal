package on2015_01.on2015_01_15_HackerRankProblemset.SherlockAndPermutations;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndPermutations {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        out.printLine(IntegerUtils.binomialCoefficient(n + m - 1, m - 1, 1000000007));
    }
}
