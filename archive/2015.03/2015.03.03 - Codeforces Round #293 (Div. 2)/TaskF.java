package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskF {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		char[][] table = new char[n][];
		for (int i = 0; i < n; ++i) {
			table[i] = in.readString().toCharArray();
		}
		long ans = 0;
		for (int i = 0; i < 4; ++i) {
			ans += count(table);
			table = rotate(table);
		}
		assert(ans % 2 == 0);
		ans /= 2;
		out.printLine(ans);
    }

	private long count(char[][] table) {
		int n = table.length;
		int m = table[0].length;
		int[][] left = new int[n][m];
		int[][] right = new int[n][m];
		int[][] down = new int[n][m];
		int[][] up = new int[n][m];
		for (int i = 0; i < n; ++i) {
			left[i][0] = 0;
			for (int j = 1; j < m; ++j) {
				if (table[i][j] == '#') {
					left[i][j] = j;
				} else {
					left[i][j] = left[i][j - 1];
				}
			}
			right[i][m - 1] = m - 1;
			for (int j = m - 2; j >= 0; --j) {
				if (table[i][j] == '#') {
					right[i][j] = j;
				} else {
					right[i][j] = right[i][j + 1];
				}
			}
		}
		for (int j = 0; j < m; ++j) {
			down[n - 1][j] = n - 1;
			for (int i = n - 2; i >= 0; --i) {
				if (table[i][j] == '#') {
					down[i][j] = i;
				} else {
					down[i][j] = down[i + 1][j];
				}
			}
			up[0][j] = 0;
			for (int i = 1; i < n; ++i) {
				if (table[i][j] == '#') {
					up[i][j] = i;
				} else {
					up[i][j] = up[i - 1][j];
				}
			}
		}

		int[][] downCount = new int[n][m];
		for (int i = 0; i < n; ++i) {
			downCount[i][0] = 0;
			for (int j = 1; j + 1 < m; ++j) {
				downCount[i][j] = downCount[i][j - 1];
				if (down[i][j] == n - 1 && table[n - 1][j] == '.') {
					++downCount[i][j];
				}
			}
			downCount[i][m - 1] = downCount[i][m - 2];
		}

		int[][] upCount = new int[n][m];
		for (int i = n - 1; i >= 0; --i) {
			upCount[i][0] = 0;
			for (int j = 1; j + 1 < m; ++j) {
				upCount[i][j] = upCount[i][j - 1];
				if (up[i][j] == 0 && table[0][j] == '.') {
					++upCount[i][j];
				}
			}
			upCount[i][m - 1] = upCount[i][m - 2];
		}

		long result = 0;

		for (int j = 1; j + 1 < m; ++j) {
			if (table[0][j] == '#') continue;
			if (down[0][j] == n - 1 && table[n - 1][j] == '.') ++result;
			for (int i = 1; i + 1 < n; ++i) {
				if (table[i][j] == '#') break;
				if (left[i][j] == 0 && table[i][0] == '.') ++result;
				if (right[i][j] == m - 1 && table[i][m - 1] == '.') ++result;
				int l = left[i][j] + 1;
				int r = right[i][j] - 1;
				if (l <= j - 1) {
					result += downCount[i][j - 1] - downCount[i][l - 1];
				}
				if (j + 1 <= r) {
					result += downCount[i][r] - downCount[i][j];
				}
				if (l <= j - 2) {
					result += upCount[i][j - 2] - upCount[i][l - 1];
				}
				if (j + 2 <= r) {
					result += upCount[i][r] - upCount[i][j + 1];
				}
			}
		}

		return result;
	}

	private char[][] rotate(char[][] table) {
		int n = table.length;
		int m = table[0].length;
		char[][] res = new char[m][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				res[m - j - 1][i] = table[i][j];
			}
		}
		return res;
	}
}
