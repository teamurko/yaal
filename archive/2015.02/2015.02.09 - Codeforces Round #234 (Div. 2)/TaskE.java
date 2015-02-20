package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.TreeSet;

public class TaskE {
	private static final int N = 20;
	TreeSet<Integer>[] trees = new TreeSet[N];
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int numQueries = in.readInt();
		for (int i = 0; i < N; ++i) {
			trees[i] = new TreeSet<>();
			trees[i].add(-1);
			trees[i].add(n);
		}
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
			for (int j = 0; j < N; ++j) {
				int bit = (a[i] >> j) & 1;
				if (bit == 0) {
					trees[j].add(i);
				}
			}
		}
		long sum = 0;
		for (int i = 0; i < N; ++i) {
			sum += countSum(a, i);
		}

		for (int queryId = 0; queryId < numQueries; ++queryId) {
			int pos = in.readInt() - 1;
			int newVal = in.readInt();
			for (int i = 0; i < N; ++i) {
				int oldBit = trees[i].contains(pos) ? 0 : 1;
				int newBit = (newVal >> i) & 1;
				if (oldBit == newBit) continue;
				if (oldBit == 1) {
					sum -= maxSegmentLen(trees[i], pos) << i;
					trees[i].add(pos);
				} else {
					trees[i].remove(pos);
					sum += maxSegmentLen(trees[i], pos) << i;
				}
			}
			out.printLine(sum);
		}
    }

	private long maxSegmentLen(TreeSet<Integer> tree, int pos) {
		long left = tree.floor(pos);
		long right = tree.ceiling(pos);
		return (right - pos) * (pos - left);
	}

	private long countSum(int[] a, int i) {
		long res = 0;
		int l = 0;
		while (l < a.length) {
			while (l < a.length && ((a[l] >> i) & 1) == 0) ++l;
			int r = l;
			while (r < a.length && ((a[r] >> i) & 1) == 1) ++r;
			res += (r - l) * (long) (r - l + 1) / 2;
			l = r;
		}
		return res << i;
	}
}
