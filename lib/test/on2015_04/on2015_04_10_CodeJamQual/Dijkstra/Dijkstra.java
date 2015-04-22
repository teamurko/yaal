package on2015_04.on2015_04_10_CodeJamQual.Dijkstra;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class Dijkstra {
	static final int[][] MUL = new int[][] {
		{1, 2, 3, 4},
		{2, -1, 4, -3},
		{3, -4, -1, 2},
		{4, 3, -2, -1}
	};

	int mul(int a, int b) {
		return MUL[Math.abs(a) - 1][Math.abs(b) - 1] * sign(a * b);
	}

	private int sign(int n) {
		if (n > 0) return 1;
		if (n < 0) return -1;
		return 0;
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		System.err.println("Test: " + testNumber);
		int n = in.readInt();
		int x = in.readInt();
		char[] s = in.readString().toCharArray();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			if (s[i] == 'i') a[i] = 2;
			else if (s[i] == 'j') a[i] = 3;
			else a[i] = 4;
		}
		out.printLine("Case #" + testNumber + ":", canSplit(a, x) ? "YES" : "NO");
    }

	private boolean canSplit(int[] a, int x) {
		int n = a.length;
		int resAll = 1;
		int[] partMul = new int[n + 1];
		partMul[0] = 1;
		for (int i = 1; i <= n; ++i) {
			partMul[i] = mul(partMul[i - 1], a[i - 1]);
			resAll = mul(resAll, a[i - 1]);
		}
		int m1 = 1;
		for (int i = 1; i <= n; ++i) {
			m1 = mul(m1, a[i - 1]);
			int m2 = 1;
			for (int j = n - 1; j >= 0; --j) {
				m2 = mul(a[j], m2);
				int m31 = calcMul(i, n, partMul);
				int m32 = calcMul(0, j, partMul);
				if (x > 1) {
					if (canSplit(m1, m2, m31, m32, x - 2, resAll)) {
						return true;
					}
				} else if (i < j) {
					int m3 = calcMul(i, j, partMul);
					if (m1 == 2 && m3 == 3 && m2 == 4) return true;
				}
			}
		}
		return false;
	}

	boolean canSplit(int a, int c, int b1, int b2, int x, int all) {
		for (int i = 0; i < 3 && a != 2 && x > 0; ++i) {
			a = mul(all, a);
			--x;
		}
		if (a != 2) return false;
		for (int i = 0; i < 3 && c != 4 && x > 0; ++i) {
			c = mul(c, all);
			--x;
		}
		if (c != 4) return false;
		x &= 3;
		int b = b1;
		for (int i = 0; i < x; ++i) {
			b = mul(b, all);
		}
		b = mul(b, b2);
		return b == 3;
	}

	private int calcMul(int i, int j, int[] partMul) {
		int res = partMul[i];
		if (Math.abs(res) > 1) res = -res;
		return mul(res, partMul[j]);
	}
}
