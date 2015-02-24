package on2015_02.on2015_02_23_HackerrankProblemset.FlippingBits;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class FlippingBits {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long n = in.readLong();
		out.printLine(n ^ ((1L << 32) - 1));
    }
}