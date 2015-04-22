package net.spak;

import net.egork.collections.intervaltree.IntervalTree;
import net.egork.collections.intervaltree.LongIntervalTree;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class TaskC {
	private static final int INF = (int)1e9;

	class Rook {
		final int x;
		final int y;

		Rook(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	class Box {
		final int id;
		final int x1;
		final int y1;
		final int x2;
		final int y2;

		Box(int id, int x1, int y1, int x2, int y2) {
			this.id = id;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	class MaxIntTree  {
		final int[] data;
		final int size;

		MaxIntTree(int size, int init) {
			this.data = new int[size * 4];
			Arrays.fill(data, init);
			this.size = size;
		}

		void update(int i, int val) {
			updateImpl(0, 0, size, i, val);
		}

		int query(int l, int r) {
			return queryImpl(0, 0, size, l, r);
		}

		private int queryImpl(int v, int l, int r, int a, int b) {
			if (l == a && r == b) {
				return data[v];
			}
			int m = (l + r) >> 1;
			int ret = INF;
			if (a < m) ret = Math.min(ret, queryImpl((v << 1) + 1, l, m, a, Math.min(b, m)));
			if (m < b) ret = Math.min(ret, queryImpl((v + 1) << 1, m, r, Math.max(a, m), b));
			return ret;
		}

		private void updateImpl(int v, int l, int r, int i, int val) {
			if (l + 1 == r) {
				data[v] = val;
				return;
			}
			int m = (l + r) >> 1;
			if (i < m) updateImpl((v << 1) + 1, l, m, i, val);
			else updateImpl((v + 1) << 1, m, r, i, val);
			data[v] = Math.min(data[(v << 1) + 1], data[(v + 1) << 1]);
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int numRooks = in.readInt();
		int numQueries = in.readInt();
		Rook[] rooks = new Rook[numRooks];
		for (int i = 0; i < numRooks; ++i) {
			int x = in.readInt();
			int y = in.readInt();
			rooks[i] = new Rook(x, y);
		}
		Box[] boxes = new Box[numQueries];
		for (int i = 0; i < numQueries; ++i) {
			int x1 = in.readInt();
			int y1 = in.readInt();
			int x2 = in.readInt();
			int y2 = in.readInt();
			boxes[i] = new Box(i, x1, y1, x2, y2);
		}
		int[] okCount = new int[numQueries];
		check(rooks, boxes, n, m, okCount);
		check(swap(rooks), swap(boxes), m, n, okCount);
		for (int i = 0; i < numQueries; ++i) {
			out.printLine((okCount[i] >= 1) ? "YES" : "NO");
		}
    }

	private void check(Rook[] rooks, Box[] boxes, int n, int m, int[] okCount) {
		rooks = addGuards(rooks, n, m);
		Arrays.sort(rooks, new Comparator<Rook>() {
			@Override
			public int compare(Rook o1, Rook o2) {
				return o1.y - o2.y;
			}
		});
		Arrays.sort(boxes, new Comparator<Box>() {
			@Override
			public int compare(Box o1, Box o2) {
				return o1.y2 - o2.y2;
			}
		});
		MaxIntTree tree = new MaxIntTree(n + 1, INF);
		int rookId = 0;
		for (Box box : boxes) {
			while (rookId < rooks.length && rooks[rookId].y <= box.y2) {
				Rook r = rooks[rookId];
				tree.update(r.x, r.y);
				++rookId;
			}
			long mx = tree.query(box.x1, box.x2 + 1);
			if (mx >= box.y1) {
				okCount[box.id]++;
			}
		}
	}

	private Rook[] addGuards(Rook[] rooks, int n, int m) {
		Rook[] res = new Rook[rooks.length + n + 1];
		for (int i = 0; i <= n; ++i) {
			res[i] = new Rook(i, 0);
		}
		n++;
		for (Rook r : rooks) {
			res[n++] = r;
		}
		return res;
	}

	private Box[] swap(Box[] bs) {
		Box[] res = new Box[bs.length];
		for (int i = 0; i < bs.length; ++i) {
			Box b = bs[i];
			res[i] = new Box(b.id, b.y1, b.x1, b.y2, b.x2);
		}
		return res;
	}
	private Rook[] swap(Rook[] rs) {
		Rook[] res = new Rook[rs.length];
		for (int i = 0; i < rs.length; ++i) {
			Rook r = rs[i];
			res[i] = new Rook(r.y, r.x);
		}
		return res;
	}
}
