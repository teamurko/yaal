package on2015_02.on2015_02_20_AdInfinitum10.SherlockAndMovingTiles;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndMovingTiles {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long L = in.readLong();
		long s1 = in.readLong();
		long s2 = in.readLong();
		int numQueries = in.readInt();
		for (int i = 0; i < numQueries; ++i) {
			double q = in.readLong();
			double t = (L - Math.sqrt(q)) * Math.sqrt(2.0) / Math.abs(s1 - s2);
			out.printLine(t);
		}
    }
}
