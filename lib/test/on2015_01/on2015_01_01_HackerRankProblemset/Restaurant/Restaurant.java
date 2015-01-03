package on2015_01.on2015_01_01_HackerRankProblemset.Restaurant;



import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Restaurant {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        testNumber = in.readInt();
        for (int testId = 0; testId < testNumber; ++testId) {
            int l = in.readInt();
            int w = in.readInt();
            out.print(l * w / IntegerUtils.gcd(l, w) / IntegerUtils.gcd(l, w));
            out.printLine();
        }
    }
}
