package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskD {
	static class Point {
		final long x;
		final long y;
		final long z;

		Point(long x, long y, long z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		static Point read(InputReader in) {
			int x = in.readInt();
			int y = in.readInt();
			int z = in.readInt();
			return new Point(x, y, z);
		}
	}

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point p = Point.read(in);
		Point c = Point.read(in);
		int ans = 0;
		int a = in.readInt();
		if (p.y < 0) {
			ans += a;
		}
		a = in.readInt();
		if (p.y > c.y) {
			ans += a;
		}
		a = in.readInt();
		if (p.z < 0) {
			ans += a;
		}
		a = in.readInt();
		if (p.z > c.z) {
			ans += a;
		}
		a = in.readInt();
		if (p.x < 0) {
			ans += a;
		}
		a = in.readInt();
		if (p.x > c.x) {
			ans += a;
		}
		out.printLine(ans);
    }
}
