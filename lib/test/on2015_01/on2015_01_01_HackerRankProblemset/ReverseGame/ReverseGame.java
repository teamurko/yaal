package on2015_01.on2015_01_01_HackerRankProblemset.ReverseGame;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class ReverseGame {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        testNumber = in.readInt();
        for (int it = 0; it < testNumber; ++it) {
            int n = in.readInt();
            int k = in.readInt();
            for (int startIndex = 0; startIndex < n; ++startIndex) {
                k = posAfterReverse(startIndex, n - 1, k);
            }
            out.printLine(k);
        }
    }

    private int posAfterReverse(int startIndex, int endIndex, int pos) {
        if (pos < startIndex || pos > endIndex) return pos;
        return endIndex - (pos - startIndex);
    }
}
