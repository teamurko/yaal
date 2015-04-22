package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskE {
	int[] buf;
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int q = in.readInt();
		int[] a = new int[n * 2];
		int[] lenBack = new int[n];
		buf = new int[n * 2];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
			a[i + n] = a[i];
		}
		for (int it = 0; it < q; ++it) {
			long b = in.readLong();
			int[] next = new int[n];
			long s = 0;
			boolean isOneGroup = false;
			for (int i = 0; i < n; ++i) lenBack[i] = 0;
			for (int i = 0, r = 0; i < n; ++i) {
				assert(r >= i);
				while (r - i < n && s + a[r] <= b) {
					s += a[r++];
				}
				if (r - i == n) {
					isOneGroup = true;
					break;
				}
				next[i] = r - i;
				int k = (r - 1) % n;
				lenBack[k] = Math.max(lenBack[k], r - i);
				s -= a[i];
			}
			if (isOneGroup) {
				out.printLine(1);
				continue;
			}
			int minLen = n;
			int minLenStartIndex = -1;
			for (int i = 0; i < n; ++i) {
				if (lenBack[i] > 0 && lenBack[i] < minLen) {
					minLen = lenBack[i];
					minLenStartIndex = (i - lenBack[i] + 1 + n) % n;
				}
			}
			rotate(a, next, n, minLenStartIndex);
			int ans = n;
			for (int i = 0; i < minLen; ++i) {
				int numGroups = 1;
				int j = i;
				while (j + next[j % n] < i + n) {
					++numGroups;
					j += next[j % n];
				}
				ans = Math.min(ans, numGroups);
			}
			out.printLine(ans);
		}
    }

	private void rotate(int[] a, int[] next, int n, int j) {
		int m = 2 * n;
		for (int i = 0; i < m; ++i) {
			buf[i] = a[i];
		}
		for (int i = 0; i < m; ++i) {
			a[i] = buf[(i + j) % m];
		}
		for (int i = 0; i < n; ++i) {
			buf[i] = next[i];
		}
		for (int i = 0; i < n; ++i) {
			next[i] = buf[(i + j) % n];
		}
	}
}
