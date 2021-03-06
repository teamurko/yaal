package on2014_09.on2014_09_21_September_Cook_Off_2014__ACM_ICPC_Warm_up.Subarray_GCD;



import net.egork.io.IOUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SubarrayGCD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int count = in.readInt();
		int[] array = IOUtils.readIntArray(in, count);
		int g = 0;
		for (int i : array) {
			g = IntegerUtils.gcd(g, i);
		}
		if (g == 1) {
			out.printLine(count);
		} else {
			out.printLine(-1);
		}
    }
}
