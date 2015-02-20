package on2015_02.on2015_02_16_Hackerrank.CubeSummation;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CubeSummation {
	class CubeFenwickTree {
		final int size;
		final long[][][] data;

		CubeFenwickTree(int size) {
			this.size = size;
			data = new long[size][size][size];
		}

		void update(int x0, int y0, int z0, long val) {
			for (int x = x0; x < size; x = x | (x + 1)) {
				for (int y = y0; y < size; y = y | (y + 1)) {
					for (int z = z0; z < size; z = z | (z + 1)) {
						data[x][y][z] += val;
					}
				}
			}
		}

		long get(int x0, int y0, int z0) {
			long res = 0;
			for (int x = x0; x >= 0; x = (x & (x + 1)) - 1) {
				for (int y = y0; y >= 0; y = (y & (y + 1)) - 1) {
					for (int z = z0; z >= 0; z = (z & (z + 1)) - 1) {
						res += data[x][y][z];
					}
				}
			}
			return res;
		}

		long get(int x1, int y1, int z1, int x2, int y2, int z2) {
			return get(x2, y2, z2) - get(x1 - 1, y2, z2) - get(x2, y1 - 1, z2)
				- get(x2, y2, z1 - 1) + get(x1 - 1, y1 - 1, z2) + get(x1 - 1, y2, z1 - 1)
				+ get(x2, y1 - 1, z1 - 1) - get(x1 - 1, y1 - 1, z1 - 1);
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		CubeFenwickTree tree = new CubeFenwickTree(n);
		int numQueries = in.readInt();
		for (int queryId = 0; queryId < numQueries; ++queryId) {
			String name = in.readString();
			if ("UPDATE".equals(name)) {
				int x = in.readInt() - 1;
				int y = in.readInt() - 1;
				int z = in.readInt() - 1;
				tree.update(x, y, z,  -tree.get(x, y, z, x, y, z));
				tree.update(x, y, z, in.readInt());
			} else {
				int x1 = in.readInt() - 1;
				int y1 = in.readInt() - 1;
				int z1 = in.readInt() - 1;
				int x2 = in.readInt() - 1;
				int y2 = in.readInt() - 1;
				int z2 = in.readInt() - 1;
				out.printLine(tree.get(x1, y1, z1, x2, y2, z2));
			}
		}
    }
}
