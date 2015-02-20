package net.spak;

import net.egork.geometry.GeometryUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class SherlockAndGeometry {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		double x0 = in.readDouble();
		double y0 = in.readDouble();
		double r = in.readDouble();
		double[] x = new double[3];
		double[] y = new double[3];
		for (int i = 0; i < 3; ++i) {
			x[i] = in.readDouble() - x0;
			y[i] = in.readDouble() - y0;
		}
		boolean intersects = false;
		for (int i = 0; i < 3; ++i) {
			if (doesIntersect(r, x[i], y[i], x[(i + 1) % 3], y[(i + 1) % 3])) {
				intersects = true;
				break;
			}
		}
		out.printLine(intersects ? "YES" : "NO");
    }

	private boolean doesIntersect(double radius, double x1, double y1, double x2, double y2) {
		double maxR = Math.max(GeometryUtils.fastHypot(x1, y1), GeometryUtils.fastHypot(x2, y2));
		double l = 0, r = 1;
		for (int it = 0; it < 100; ++it) {
			double tl = l + (r - l) / 3;
			double tr = r - (r - l) / 3;
			double distL = GeometryUtils.fastHypot(x1 + (x2 - x1) * tl, y1 + (y2 - y1) * tl);
			double distR = GeometryUtils.fastHypot(x1 + (x2 - x1) * tr, y1 + (y2 - y1) * tr);
			if (distL < distR) {
				r = tr;
			} else {
				l = tl;
			}
		}
		double minR = GeometryUtils.fastHypot(x1 + (x2 - x1) * l, y1 + (y2 - y1) * l);
		return minR <= radius + GeometryUtils.epsilon && radius - GeometryUtils.epsilon <= maxR;
	}
}
