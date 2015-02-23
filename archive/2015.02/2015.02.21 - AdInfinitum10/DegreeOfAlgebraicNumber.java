package net.spak;

import net.egork.collections.Pair;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class DegreeOfAlgebraicNumber {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		List<Set<Long>> independentRadicals = new ArrayList<>();
		long ans = 1;
		for (int i = 0; i < n; ++i) {
			List<Pair<Long, Integer>> factors = IntegerUtils.factorize(in.readInt());
			Set<Long> primes = new TreeSet<>();
			for (Pair<Long, Integer> factor : factors) {
				if (factor.second % 2 != 0) {
					primes.add(factor.first);
				}
			}
			if (areIndependent(independentRadicals, primes)) {
				independentRadicals.add(primes);
				ans <<= 1;
			}
		}
		out.printLine(ans);
    }

	private boolean areIndependent(List<Set<Long>> independentRadicals, Set<Long> radical) {
		int size = independentRadicals.size() + 1;
		TreeSet<Long>[] a = new TreeSet[size];
		for (int i = 0; i < size - 1; ++i) {
			a[i] = new TreeSet<>();
			a[i].addAll(independentRadicals.get(i));
		}
		a[size - 1] = new TreeSet<>();
		a[size - 1].addAll(radical);

		for (int i = 0; i < size; ++i) {
			if (a[i].isEmpty()) return false;
			int j = i;
			for (int k = i + 1; k < size; ++k) {
				if (a[k].isEmpty()) return false;
				if (a[k].first() < a[j].first()) {
					j = k;
				}
			}
			TreeSet<Long> t = a[j];
			a[j] = a[i];
			a[i] = t;
			for (int k = i + 1; k < size; ++k) {
				if (!a[k].contains(a[i].first())) continue;
				for (Long e : a[i]) {
					if (a[k].contains(e)) {
						a[k].remove(e);
					} else {
						a[k].add(e);
					}
				}
			}
		}
		return true;
	}
}
