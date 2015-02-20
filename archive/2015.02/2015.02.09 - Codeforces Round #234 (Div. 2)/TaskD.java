package net.spak;

import net.egork.collections.iss.IndependentSetSystem;
import net.egork.collections.iss.RecursiveIndependentSetSystem;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import sun.awt.UNIXToolkit;
import sun.org.mozilla.javascript.internal.Undefined;

public class TaskD {
	private static final int INF = (int) 1e8;
	private static final int UNDEFINED = -1;

	class Bucket {
		final int beginId;
		final int endId;

		Bucket(int beginId, int endId) {
			this.beginId = beginId;
			this.endId = endId;
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int m = in.readInt();
		int bucketCount = in.readInt();
		int bucketSumSize = 0;
		Bucket[] buckets = new Bucket[bucketCount];
		for (int i = 0; i < bucketCount; ++i) {
			int size = in.readInt();
			buckets[i] = new Bucket(bucketSumSize, bucketSumSize + size);
			bucketSumSize += size;
		}
		int[] u = new int[m];
		int[] v = new int[m];
		int[] w = new int[m];
		IndependentSetSystem dsu = new RecursiveIndependentSetSystem(n);
		for (int i = 0; i < m; ++i) {
			u[i] = in.readInt() - 1;
			v[i] = in.readInt() - 1;
			w[i] = in.readInt();
			if (w[i] == 0) {
				dsu.join(u[i], v[i]);
			}
		}
		boolean correct = true;
		for (int i = 0; i < bucketCount; ++i) {
			for (int j = buckets[i].beginId; j < buckets[i].endId; ++j) {
				if (dsu.get(j) != dsu.get(buckets[i].beginId)) {
					correct = false;
					break;
				}
				if (!correct) break;
			}
		}
		if (!correct) {
			out.printLine("No");
			return;
		}
		out.printLine("Yes");
		int[][] g = new int[bucketCount][bucketCount];
		ArrayUtils.fill(g, INF);
		for (int i = 0; i < bucketCount; ++i) g[i][i] = 0;

		for (int i = 0; i < m; ++i) {
			int a = getBucketId(buckets, u[i]);
			int b = getBucketId(buckets, v[i]);
			g[a][b] = g[b][a] = Math.min(g[a][b], w[i]);
		}

		for (int l = 0; l < bucketCount; ++l) {
			for (int x = 0; x < bucketCount; ++x) {
				for (int y = 0; y < bucketCount; ++y) {
					if (g[x][y] > g[x][l] + g[l][y]) {
						g[x][y] = g[x][l] + g[l][y];
					}
				}
			}
		}

		for (int x = 0; x < bucketCount; ++x) {
			for (int y = 0; y < bucketCount; ++y) {
				if (y > 0) out.print(" ");
				if (g[x][y] == INF) g[x][y] = -1;
				out.print(g[x][y]);
			}
			out.printLine();
		}
    }

	private int getBucketId(Bucket[] buckets, int v) {
		for (int i = 0; i < buckets.length; ++i) {
			if (buckets[i].beginId <= v && v < buckets[i].endId) return i;
		}
		return UNDEFINED;
	}
}
