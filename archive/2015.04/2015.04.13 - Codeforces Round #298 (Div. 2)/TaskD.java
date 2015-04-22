package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskD {
	class MultiSet<E> {
		final TreeMap<E, Integer> store;

		MultiSet() {
			store = new TreeMap<>();
		}

		void add(E element) {
			if (!store.containsKey(element)) {
				store.put(element, 1);
			} else {
				store.put(element, store.get(element) + 1);
			}
		}

		void remove(E element) {
			if (!store.containsKey(element)) {
				throw new NoSuchElementException();
			}
			int count = store.get(element) - 1;
			if (0 == count) {
				store.remove(element);
			} else {
				store.put(element, count);
			}
		}

		E maxNotGreater(E upperBound) {
			E key = store.floorKey(upperBound);
			if (key == null) {
				throw new NoSuchElementException();
			}
			return key;
		}

		boolean isEmpty() {
			return store.isEmpty();
		}
	}
	class Student implements Comparable<Student> {
		final int handshakeCount;
		final int id;

		Student(int id, int handshakeCount) {
			this.handshakeCount = handshakeCount;
			this.id = id;
		}

		@Override
		public int compareTo(Student o) {
			if (handshakeCount != o.handshakeCount) {
				return handshakeCount - o.handshakeCount;
			}
			return id - o.id;
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; ++i) {
			a[i] = in.readInt();
		}
		MultiSet<Student>[] sets = new MultiSet[3];
		for (int i = 0; i < 3; ++i) {
			sets[i] = new MultiSet<>();
		}
		for (int i = 0; i < n; ++i) {
			sets[a[i] % 3].add(new Student(i, a[i]));
		}
		List<Integer> studentIdsOrder = new ArrayList<>();
		int currentPeopleCount = 0;
		try {
			Student candidate = sets[0].maxNotGreater(new Student(n, 0));
			studentIdsOrder.add(candidate.id);
			sets[0].remove(candidate);
			for (int it = 1; it < n; ++it) {
				++currentPeopleCount;
				candidate = sets[currentPeopleCount % 3].maxNotGreater(new Student(n, currentPeopleCount));
				sets[currentPeopleCount % 3].remove(candidate);
				studentIdsOrder.add(candidate.id);
				currentPeopleCount = candidate.handshakeCount;
			}
		} catch (NoSuchElementException e) {
			out.printLine("Impossible");
			return;
		}
		out.printLine("Possible");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			if (i > 0) sb.append(" ");
			sb.append(studentIdsOrder.get(i) + 1);
		}
		out.printLine(sb);
    }
}
