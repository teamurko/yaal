package on2015_02.on2015_02_19_Codeforces_Round__292__Div__1_.C___Drazil_and_Park;



import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
	private static final long UNDEFINED = -(long) 1e18;

	class Node {
		long maxLeft;
		long maxRight;
		long leftMax;
		long rightMax;
		Node() {
			maxLeft = maxRight = leftMax = rightMax = UNDEFINED;
		}
		Node(long leftMax, long rightMax) {
			maxLeft = maxRight = UNDEFINED;
			this.leftMax = leftMax;
			this.rightMax = rightMax;
		}
	}
	long[] leftSum;
	long[] rightSum;
	Node[] tree;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int numTrees = in.readInt();
		int numDays = in.readInt();
		long[] dist = new long[numTrees];
		for (int i = 0; i < numTrees; ++i) {
			dist[i] = in.readInt();
		}
		long[] height = new long[numTrees];
		for (int i = 0; i < numTrees; ++i) {
			height[i] = 2 * in.readInt();
		}

		leftSum = new long[2 * numTrees + 10];
		rightSum = new long[2 * numTrees + 10];
		for (int i = 0; i + 1 < 2 * numTrees + 10; ++i) {
			rightSum[i + 1] = height[(i + 1) % numTrees] + dist[i % numTrees];
			if (i > 0) {
				rightSum[i + 1] -= height[i % numTrees];
			}
			rightSum[i + 1] += rightSum[i];
			leftSum[i + 1] = height[(i + 1) % numTrees] - dist[i % numTrees];
			if (i > 0) {
				leftSum[i + 1] -= height[i % numTrees];
			}
			leftSum[i + 1] += leftSum[i];
		}
		tree = new Node[numTrees * 15];
		buildTree(0, 0, numTrees * 2 + 10);

		for (int day = 0; day < numDays; ++day) {
			int startTreeId = in.readInt() - 1;
			int endTreeId = in.readInt();
			Pair<Integer, Integer> range = calcRange(numTrees, startTreeId, endTreeId);
			Node result = rmq(0, 0, numTrees * 2 + 10, range.first, range.second);
			out.printLine(result.maxRight + result.maxLeft);
		}
    }

	private Node rmq(int v, int l, int r, int a, int b) {
		if (l == a && r == b) {
			return tree[v];
		}
		int m = (l + r) >> 1;
		Node node = new Node();
		Node left = null, right = null;
		if (a < m) {
			Node sub = rmq(leftChild(v), l, m, a, Math.min(b, m));
			if (node.leftMax == UNDEFINED || node.leftMax < sub.leftMax) {
				node.leftMax = sub.leftMax;
			}
			if (node.rightMax == UNDEFINED || node.rightMax < sub.rightMax) {
				node.rightMax = sub.rightMax;
			}
			if (node.maxLeft == UNDEFINED || (node.maxLeft + node.maxRight < sub.maxLeft + sub.maxRight)) {
				node.maxLeft = sub.maxLeft;
				node.maxRight = sub.maxRight;
			}
			left = sub;
		}
		if (m < b) {
			Node sub = rmq(rightChild(v), m, r, Math.max(m, a), b);
			if (node.leftMax == UNDEFINED || node.leftMax < sub.leftMax) {
				node.leftMax = sub.leftMax;
			}
			if (node.rightMax == UNDEFINED || node.rightMax < sub.rightMax) {
				node.rightMax = sub.rightMax;
			}
			if (node.maxLeft == UNDEFINED || (node.maxLeft + node.maxRight < sub.maxLeft + sub.maxRight)) {
				node.maxLeft = sub.maxLeft;
				node.maxRight = sub.maxRight;
			}
			right = sub;
		}
		if (left != null && right != null) {
			if (node.maxLeft == UNDEFINED || (node.maxLeft + node.maxRight < left.leftMax + right.rightMax)) {
				node.maxLeft = left.leftMax;
				node.maxRight = right.rightMax;
			}
		}
		return node;
	}

	private Pair<Integer, Integer> calcRange(int numTrees, int begin, int end) {
		if (begin > end) {
			return Pair.makePair(end, begin);
		}
		return Pair.makePair(end, begin + numTrees);
	}

	private void buildTree(int v, int l, int r) {
		if (l + 1 == r) {
			tree[v] = new Node(leftSum[l], rightSum[l]);
			return;
		}
		int m = (l + r) >> 1;
		buildTree(leftChild(v), l, m);
		buildTree(rightChild(v), m, r);
		update(v);
	}

	private void update(int v) {
		Node left = tree[leftChild(v)];
		Node right = tree[rightChild(v)];
		tree[v] = new Node(Math.max(left.leftMax, right.leftMax), Math.max(left.rightMax, right.rightMax));
		if (tree[v].maxLeft == UNDEFINED || (left.maxLeft != UNDEFINED && tree[v].maxLeft + tree[v].maxRight < left.maxLeft + left.maxRight)) {
			tree[v].maxLeft = left.maxLeft;
			tree[v].maxRight = left.maxRight;
		}
		if (right.maxLeft != UNDEFINED && tree[v].maxLeft + tree[v].maxRight < right.maxLeft + right.maxRight) {
			tree[v].maxLeft = right.maxLeft;
			tree[v].maxRight = right.maxRight;
		}
		if (tree[v].maxLeft + tree[v].maxRight < left.leftMax + right.rightMax) {
			tree[v].maxLeft = left.leftMax;
			tree[v].maxRight = right.rightMax;
		}
	}

	private int leftChild(int v) {
		return (v << 1) + 1;
	}

	private int rightChild(int v) {
		return (v + 1) << 1;
	}
}
