package on2015_02.on2015_02_16_Hackerrank.JimBeam;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class JimBeam {
	class Point {
		final long x;
		final long y;

		Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		Point wBegin = readPoint(in);
		Point wEnd = readPoint(in);
		Point target = readPoint(in);
		out.printLine(intesects(new Point(0, 0), target, wBegin, wEnd) ? "NO" : "YES");
    }

	private boolean intesects(Point s, Point e, Point a, Point b) {
		if (Math.max(s.x, e.x) < Math.min(a.x, b.x)) return false;
		if (Math.max(s.y, e.y) < Math.min(a.y, b.y)) return false;
		if (Math.max(a.x, b.x) < Math.min(s.x, e.x)) return false;
		if (Math.max(a.y, b.y) < Math.min(s.y, e.y)) return false;
		return vectProdSign(s, e, a) * vectProdSign(s, e, b) <= 0 && vectProdSign(a, b, s) * vectProdSign(a, b, e) <= 0;
	}

	private long vectProdSign(Point a, Point b, Point c) {
		long res = det(b.x - a.x, c.x - a.x, b.y - a.y, c.y - a.y);
		if (res > 0) res = 1;
		else if (res < 0) res = -1;
		return res;
	}

	private long det(long x, long y, long z, long w) {
		return x * w - y * z;
	}

	private Point readPoint(InputReader in) {
		long x = in.readInt();
		long y = in.readInt();
		return new Point(x, y);
	}
}
