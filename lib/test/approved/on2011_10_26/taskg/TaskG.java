package approved.on2011_10_26.taskg;



import net.egork.geometry.GeometryUtils;
import net.egork.io.IOUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class TaskG {
	public void solve(int testNumber, InputReader in, OutputWriter out) {
		in.readInt();
		int count = in.readInt();
		int[] x = new int[count];
		int[] y = new int[count];
		IOUtils.readIntArrays(in, x, y);
		int startIndex = 0;
		for (int i = 1; i < count; i++) {
			if (y[startIndex] < y[i] || y[startIndex] == y[i] && x[startIndex] > x[i])
				startIndex = i;
		}
		long dirX = 1;
		long dirY = 0;
		List<Integer> order = new ArrayList<Integer>();
		int current = startIndex;
		do {
			order.add(current);
			int best = -1;
			double value = Double.NEGATIVE_INFINITY;
			double length = Double.NEGATIVE_INFINITY;
			for (int i = 0; i < count; i++) {
				if (i == current)
					continue;
				double dx = x[i] - x[current];
				double dy = y[i] - y[current];
				double curLength = GeometryUtils.fastHypot(dx, dy);
				double curValue = (dx * dirX + dy * dirY) / curLength;
				if (curValue > value + GeometryUtils.epsilon || Math.abs(curValue - value) < GeometryUtils.epsilon && curLength > length) {
					value = curValue;
					length = curLength;
					best = i;
				}
			}
			dirX = x[best] - x[current];
			dirY = y[best] - y[current];
			current = best;
		} while (current != startIndex);
		out.printLine(testNumber, order.size());
		for (int i : order)
			out.printLine(x[i], y[i]);
	}
}
