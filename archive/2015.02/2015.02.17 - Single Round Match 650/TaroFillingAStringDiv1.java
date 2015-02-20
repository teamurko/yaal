package net.spak;

import java.util.Arrays;

public class TaroFillingAStringDiv1 {
	class Node implements Comparable<Node> {
		final int i;
		final char c;

		Node(int i, char c) {
			this.i = i;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return i - o.i;
		}
	}
    public int getNumber(int N, int[] position, String value) {
		final long MOD = 1000000007;
		long ans = 1;
		Node[] a = new Node[position.length];
		for (int i = 0; i < position.length; ++i) {
			a[i] = new Node(position[i], value.charAt(i));
		}
		Arrays.sort(a);
		for (int i = 0; i < position.length - 1; ++i) {
			int len = a[i + 1].i - a[i].i - 1;
			if (len == 0) continue;
			if (a[i].c == a[i + 1].c) {
				if (len % 2 == 0) {
					ans = (ans * (len + 1)) % MOD;
				}
			} else {
				if (len % 2 == 1) {
					ans = (ans * (len + 1)) % MOD;
				}
			}
		}
		return (int) ans;
    }
}
