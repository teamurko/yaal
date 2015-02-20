package net.spak;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Spheres {
	class Point {
		final long x;
		final long y;
		final long z;

		Point(long x, long y, long z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		Point sub(Point o) {
			return new Point(x - o.x, y - o.y, z - o.z);
		}
		long scalar(Point o) {
			return x * o.x + y * o.y + z * o.z;
		}
		Point mul(long c) {
			return new Point(x * c, y * c, z * c);
		}
		Point add(Point o) {
			return new Point(x + o.x, y + o.y, z + o.z);
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		long r1 = in.readInt();
		long r2 = in.readInt();
		Point x1 = readPoint(in);
		Point a1 = readPoint(in);
		Point x2 = readPoint(in);
		Point a2 = readPoint(in);
		Point a = a1.sub(a2);
		Point x = x1.sub(x2);
		// a2 / 4 * t4 + 2 * a/ 2 * t2 * x + c = 0
		// a2 * t3 + 2 * a * t * x = 0
		// a2 * t2 + 2 * a * x = 0
		// t2 = -2 * a * x / a2
		long A = a.scalar(a);
		long B = -2 * a.scalar(x);
		if (B <= 0) {
			out.printLine("NO");
		} else {
			// (a / 2 * B / A + x)^2 <= (r1 + r2) ^ 2
			// (a * B + 2 * A * x)^2 <= (r1 + r2) ^ 2 * 4 * A * A
			Point pos = a.mul(B).add(x.mul(A * 2));
			long dist1 = pos.scalar(pos);
			long dist2 = sqr(r1 + r2) * A * A * 4;
			out.printLine(dist1 <= dist2 ? "YES" : "NO");
		}
    }

	long sqr(long x) {
		return x * x;
	}
	private Point readPoint(InputReader in) {
		long x = in.readInt();
		long y = in.readInt();
		long z = in.readInt();
		return new Point(x, y, z);
	}
}
