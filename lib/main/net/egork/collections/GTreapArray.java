package net.egork.collections;

import java.util.Random;

/**
 * Created by stanislav on 1/9/15.
 */
public abstract class GTreapArray<V> {
	Random random = new Random(123);
	public final class Node<V> {
		long priority;
		V value;
		V sum;
		int size;
		Node<V> left;
		Node<V> right;

		private Node(long priority, int size, V value, V sum, Node<V> left, Node<V> right) {
			this.priority = priority;
			this.size = size;
			this.value = value;
			this.sum = sum;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			StringBuilder res = new StringBuilder();
			if (left != null) {
				res.append(left);
				res.append(" ");
			}
			res.append(value);
			if (right != null) {
				res.append(" ");
				res.append(right);
			}
			return res.toString();
		}
	}

	private Node<V> root;

	public GTreapArray(V[] array) {
		for (int i = 0; i < array.length; ++i) {
			insert(i, array[i]);
		}
	}

	protected GTreapArray(Node<V> root) {
		this.root = root;
	}

	public void insert(int at, V value) {
		Pair<Node<V>, Node<V>> parts = split(root, at);
		Node<V> left = createLeaf(random.nextLong(), value);
		root = merge(merge(parts.first, left), parts.second);
	}

	private void update(Node<V> node) {
		node.size = nodeSize(node.left) + nodeSize(node.right) + 1;
		node.sum = aggregate(nodeSum(node.left), nodeSum(node.right));
		node.sum = aggregate(node.sum, node.value);
	}

	private int nodeSize(Node<?> node) {
		if (node == null) return 0;
		return node.size;
	}

	private V nodeValue(Node<V> node) {
		if (node == null) return empty();
		return node.value;
	}

	private V nodeSum(Node<V> node) {
		if (node == null) return empty();
		return node.sum;
	}

	@Override
	public String toString() {
		if (root == null) return null;
		return root.toString();
	}

	private Node<V> createNode(long priority, int size, V value, Node<V> left, Node<V> right) {
		V sum = aggregate(nodeSum(left), nodeSum(right));
		sum = aggregate(sum, value);
		return new Node<>(priority, size, value, sum, left, right);
	}

	private Node<V> createLeaf(long priority, V value) {
		return createNode(priority, 1, value, null, null);
	}

	abstract protected GTreapArray<V> create(Node<V> root);
	abstract protected V aggregate(V first, V second);
	abstract protected V empty();

	private Pair<Node<V>, Node<V>> split(Node<V> root, int at) {
		if (root == null) {
			return Pair.makePair(null, null);
		}
		if (root.size <= at) {
			return Pair.makePair(root, null);
		}
		if (at <= 0) {
			return Pair.makePair(null, root);
		}
		if (nodeSize(root.left) >= at) {
			Pair<Node<V>, Node<V>> leftSplit = split(root.left, at);
			root.left = leftSplit.second;
			update(root);
			return Pair.makePair(leftSplit.first, root);
		} else {
			Pair<Node<V>, Node<V>> rightSplit = split(root.right, at - nodeSize(root.left) - 1);
			root.right = rightSplit.first;
			update(root);
			return Pair.makePair(root, rightSplit.second);
		}
	}

	public GTreapArray<V> merge(GTreapArray<V> o) {
		return create(merge(root, o.root));
	}

	private Node<V> merge(Node<V> first, Node<V> second) {
		if (first == null) return second;
		if (second == null) return first;
		if (first.priority > second.priority) {
			first.right = merge(first.right, second);
			update(first);
			return first;
		}
		second.left = merge(first, second.left);
		update(second);
		return second;
	}

	public int size() {
		return nodeSize(root);
	}

	public void set(int at, V value) {
		if (at < 0 || at >= size()) {
			throw new IndexOutOfBoundsException();
		}
		root = update(root, at, value);
	}

	public V get(int at) {
		if (at < 0 || at >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return find(root, at);
	}

	//to exclusive
	public V sum(int from, int to) {
		return sum(root, from, to);
	}

	private V sum(Node<V> node, int from, int to) {
		if (from >= to || node == null) return empty();
		if (to - from == nodeSize(node)) {
			return nodeSum(node);
		}
		V res = empty();
		int leftSize = nodeSize(node.left);
		if (from < leftSize) {
			res = aggregate(res, sum(node.left, from, Math.min(to, leftSize)));
		}
		if (from <= leftSize && to > leftSize) {
			res = aggregate(res, node.value);
		}
		if (to > leftSize + 1) {
			res = aggregate(res, sum(node.right, Math.max(from - leftSize - 1, 0), to - leftSize - 1));
		}
		return res;
	}

	public Pair<GTreapArray<V>, GTreapArray<V>> split(int at) {
		Pair<Node<V>, Node<V>> parts = split(root, at);
		return Pair.makePair(create(parts.first), create(parts.second));
	}

	private V find(Node<V> root, int at) {
		if (nodeSize(root.left) > at) {
			return find(root.left, at);
		}
		if (nodeSize(root.left) + 1 <= at) {
			return find(root.right, at - nodeSize(root.left) - 1);
		}
		return root.value;
	}

	private Node<V> update(Node<V> root, int at, V value) {
		Node<V> left = root.left;
		Node<V> right = root.right;
		if (nodeSize(root.left) > at) {
			left = update(root.left, at, value);
			value = root.value;
		} else if (nodeSize(root.left) + 1 <= at) {
			right = update(root.right, at - nodeSize(root.left) - 1, value);
			value = root.value;
		}
		return createNode(
			root.priority,
			root.size,
			value,
			left,
			right
		);
	}
}
