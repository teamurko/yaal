package on2015_02.on2015_02_05_Codeforces_Round__264__Div__2_.E___Caisa_and_Tree;



import net.egork.graph.BidirectionalGraph;
import net.egork.graph.Edge;
import net.egork.graph.Graph;
import net.egork.graph.GraphAlgorithms;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TaskE {
	private static final int UNDEFINED = -1;
	private final int MAXP = 2000007;
	int[][] primes;
	int[] parent;
	Stack<Integer>[] stack;
	int[] height;
	//closest vertex on path to root with gcd > 1
	int[] goodId;
	int[] minPrimeDiv;
	int[] ps;

	private void buildPrimes() {
		minPrimeDiv = ArrayUtils.createArray(MAXP, 0);
		int cnt = 0;
		for (int p = 2; p < MAXP; ++p) {
			if (minPrimeDiv[p] > 0) continue;
			++cnt;
			for (int i = p + p; i < MAXP; i += p) {
				if (minPrimeDiv[i] == 0) {
					minPrimeDiv[i] = p;
				}
			}
		}
		ps = new int[cnt];
		cnt = 0;
		for (int p = 2; p < MAXP; ++p) {
			if (minPrimeDiv[p] == 0) {
				ps[cnt++] = p;
			}
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		buildPrimes();
		int n = in.readInt();
		int numQueries = in.readInt();
		primes = new int[n][];
		for (int i = 0; i < n; ++i) {
			int m = in.readInt();
			primes[i] = simpleFact(m);
		}
		stack = new Stack[ps.length];
		for (int i = 0; i < ps.length; ++i) {
			stack[i] = new Stack<>();
		}
		Graph tree = new BidirectionalGraph(n, n - 1);
		for (int i = 0; i < n - 1; ++i) {
			int u = in.readInt() - 1;
			int v = in.readInt() - 1;
			tree.addSimpleEdge(u, v);
		}
		height = new int[n];
		goodId = ArrayUtils.createArray(n, UNDEFINED);
		parent = new int[n];
		dfs(0, UNDEFINED, 0, tree);
		for (int qi = 0; qi < numQueries; ++qi) {
			int t = in.readInt();
			if (t == 1) {
				int v = in.readInt() - 1;
				out.printLine(format(goodId[v]));
			} else {
				int v = in.readInt() - 1;
				int w = in.readInt();
				update(v, w, tree);
			}
		}
    }

	private int format(int n) {
		if (n == UNDEFINED) return UNDEFINED;
		return n + 1;
	}

	int[] primeToId;
	private void update(int v, int w, Graph tree) {
		if (primeToId == null) {
			primeToId = new int[ps.length];
		}
		for  (int i = 0; i < primeToId.length; ++i) {
			primeToId[i] = UNDEFINED;
		}
		primes[v] = simpleFact(w);
		int x = parent[v];
		goodId[v] = UNDEFINED;
		while (x != UNDEFINED) {
			if (hasDivisor(primes[x], w)) {
				goodId[v] = x;
				break;
			}
			x = parent[x];
		}
		x = v;
		while (x != UNDEFINED) {
			for (int p : primes[x]) {
				int i = Arrays.binarySearch(ps, p);
				if (primeToId[i] == UNDEFINED) {
					primeToId[i] = x;
				}
			}
			x = parent[x];
		}
		boolean[] used = ArrayUtils.createArray(tree.vertexCount(), false);
		if (parent[v] != UNDEFINED) used[parent[v]] = true;
		List<Integer> visited = new ArrayList<>();
		GraphAlgorithms.dfs(tree, v, used, visited);
		for (int u : visited) {
			if (u == v) continue;
			if (goodId[u] == v) {
				goodId[u] = UNDEFINED;
			}
			for (int p : primes[u]) {
				int i = Arrays.binarySearch(ps, p);
				if (primeToId[i] != UNDEFINED) {
					if (goodId[u] == UNDEFINED || height[primeToId[i]] > height[goodId[u]]) {
						goodId[u] = primeToId[i];
					}
				}
			}
		}
	}

	private boolean hasDivisor(int[] primes, int w) {
		for (int prime : primes) {
			if (w % prime == 0) return true;
		}
		return false;
	}

	private void dfs(int v, int p, int h, Graph tree) {
		parent[v] = p;
		height[v] = h;
		for (int prime : primes[v]) {
			int i = Arrays.binarySearch(ps, prime);
			if (!stack[i].empty()) {
				if (goodId[v] == UNDEFINED || height[goodId[v]] < height[stack[i].peek()]) {
					goodId[v] = stack[i].peek();
				}
			}
			stack[i].push(v);
		}
		for (Edge edge : tree.outbound(v)) {
			int u = edge.getDestination();
			if (u == p) continue;
			dfs(u, v, h + 1, tree);
		}
		for (int prime : primes[v]) {
			int i = Arrays.binarySearch(ps, prime);
			stack[i].pop();
		}
	}

	private int[] simpleFact(int m) {
		List<Integer> primeDivs = new ArrayList<>();
		for (int p = 2; p * p <= m; ++p) {
			if (m % p == 0) {
				primeDivs.add(p);
				while (m % p == 0) m /= p;
			}
		}
		if (m > 1) {
			primeDivs.add(m);
		}
		int[] res = new int[primeDivs.size()];
		for (int i = 0; i < res.length; ++i) {
			res[i] = primeDivs.get(i);
		}
		return res;
	}
}
