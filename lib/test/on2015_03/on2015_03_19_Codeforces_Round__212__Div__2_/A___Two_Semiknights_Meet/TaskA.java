package on2015_03.on2015_03_19_Codeforces_Round__212__Div__2_.A___Two_Semiknights_Meet;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.LinkedList;
import java.util.Queue;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int tc = in.readInt();
		for (int it = 0; it < tc; ++it) {
			solveOne(in, out);
		}
    }

	private void solveOne(InputReader in, OutputWriter out) {
		char[][] t = new char[8][];
		for (int i = 0; i < 8; ++i) {
			t[i] = in.readString().toCharArray();
		}
		Point a = extractPosition(t);
		Point b = extractPosition(t);
		if (meet(t, a, b)) {
			out.printLine("YES");
		} else {
			out.printLine("NO");
		}
	}

	private boolean meet(char[][] t, Point a, Point b) {
		boolean[][][][] used = new boolean[8][8][8][8];
		return dfs(a, b, t, used);
	}

	private boolean dfs(Point a, Point b, char[][] t, boolean[][][][] used) {
		if (a.equals(b) && t[a.x][a.y] == '.' && t[b.x][b.y] == '.') return true;
		used[a.x][a.y][b.x][b.y] = true;
		for (Point sa : new Point[]{new Point(2, 2), new Point(-2, 2), new Point(2, -2), new Point(-2, -2)}) {
			Point na = new Point(sa.x + a.x, sa.y + a.y);
			if (!isValid(na, t)) continue;
			for (Point sb : new Point[]{new Point(2, 2), new Point(-2, 2), new Point(2, -2), new Point(-2, -2)}) {
				Point nb = new Point(sb.x + b.x, sb.y + b.y);
				if (!isValid(nb, t)) continue;
				if (!used[na.x][na.y][nb.x][nb.y]) {
					if (dfs(na, nb, t, used)) return true;
				}
			}
		}
		return false;
	}

	private boolean isValid(Point p, char[][] t) {
		return p.x >= 0 && p.y >= 0 && p.x < 8 && p.y < 8;
	}

	private boolean[][] visit(char[][] t, Point point) {
		boolean[][] used = new boolean[8][8];
		used[point.x][point.y] = true;
		Queue<Point> queue = new LinkedList<>();
		queue.add(point);
		used[point.x][point.y] = true;
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			for (Point shift : new Point[]{new Point(2, 2), new Point(-2, 2), new Point(2, -2), new Point(-2, -2)}) {
				Point q = new Point(p.x + shift.x, p.y + shift.y);
				if (q.x >= 0 && q.x < 8 && q.y >= 0 && q.y < 8) {
					if (!used[q.x][q.y] && t[q.x][q.y] == '.') {
						used[q.x][q.y] = true;
						queue.add(q);
					}
				}
			}
		}
		return used;
	}

	private Point extractPosition(char[][] t) {
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				if (t[i][j] == 'K') {
					t[i][j] = '.';
					return new Point(i, j);
				}
			}
		}
		return null;
	}

	private class Point {
		final int x;
		final int y;

		private Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Point)) {
				return false;
			}
			Point p = (Point) o;
			return p.x == x && p.y == y;
		}
	}
}
