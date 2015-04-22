package on2015_04.on2015_04_13_Codeforces_Round__298__Div__2_.E___Berland_Local_Positioning_System0;





import net.egork.collections.Pair;
import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskE {
	class Range {
		final int length;
		final int weight;

		Range(int length, int weight) {
			this.length = length;
			this.weight = weight;
		}
		@Override
		public boolean equals(Object o) {
			if (o instanceof Range) {
				Range r = (Range) o;
				return r.length == length && r.weight == weight;
			} else {
				return false;
			}
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int numBusStops = in.readInt();
		int[] distances = new int[numBusStops];
		for (int i = 0; i < numBusStops; ++i) {
			distances[i] = in.readInt();
		}
		int numStopsPassed = in.readInt();
		int[] busStops = new int[numStopsPassed];
		for (int i = 0; i < numStopsPassed; ++i) {
			busStops[i] = in.readInt() - 1;
		}
		Range[] targetRanges = toRanges(busStops, numBusStops);
		List<Pair<Integer, Range[]>> candidateRanges =
			generateCandidateRanges(targetRanges, numBusStops, numStopsPassed, numBusStops * 2);
		Set<Long> counter = new HashSet<>();
		for (Pair<Integer, Range[]> candidateData : candidateRanges) {
			int start = candidateData.first;
			int dir = start >= 0 ? -1 : 1;
			start = Math.abs(start);
			long totalDistance = 0;
			for (int i = 0; i < numStopsPassed - 1; ++i) {
				int next = start + dir;
				if (next < 0) {
					dir = -dir;
					next = 1;
				} else if (next == numBusStops) {
					next = numBusStops - 2;
					dir = -dir;
				}
				totalDistance += Math.abs(distances[next] - distances[start]);
				start = next;
			}
			counter.add(totalDistance);
		}
		if (counter.size() != 1) {
			out.printLine(-1);
		} else {
			out.printLine(counter.iterator().next());
		}
    }

	private List<Pair<Integer, Range[]>> generateCandidateRanges(Range[] targetRanges, int numBusStops, int numStopsPassed, int limit) {
		List<Pair<Integer, Range[]>> res = new ArrayList<>();
		if (limit <= 0) return res;
		for (int startBusStop = 0; startBusStop < numBusStops; ++startBusStop) {
			Range[] candidate = generateRange(startBusStop, numBusStops, numStopsPassed);
			if (Arrays.equals(targetRanges, candidate)) {
				res.add(Pair.makePair(startBusStop, candidate));
				if (res.size() == limit) return res;
			}
			ArrayUtils.reverse(candidate);
			if (Arrays.equals(targetRanges, candidate)) {
				res.add(Pair.makePair(-(numBusStops - startBusStop - 1), candidate));
				if (res.size() == limit) return res;
			}
		}
		return res;
	}

	private Range[] generateRange(int startBusStop, int numBusStops, int numStopsPassed) {
		List<Range> ranges = new ArrayList<>();
		ranges.add(new Range(numBusStops, 0));
		int l = Math.max(1, startBusStop + 1 - numStopsPassed);
		if (startBusStop > 0) {
			addRange(ranges, l, startBusStop, 1);
		}
		numStopsPassed -= startBusStop - l + 1;
		if (numStopsPassed == 0) {
			return toArray(ranges);
		}
		int fullRideCount = numStopsPassed / (numBusStops - 1);
		if (fullRideCount > 0) {
			addRange(ranges, 0, numBusStops - 2, (fullRideCount + 1) / 2);
			if (fullRideCount >= 2) {
				addRange(ranges, 1, numBusStops - 1, fullRideCount / 2);
			}
		}
		numStopsPassed %= (numBusStops - 1);
		if (numStopsPassed > 0) {
			if (fullRideCount % 2 == 0) {
				addRange(ranges, 0, numStopsPassed - 1, 1);
			} else {
				addRange(ranges, numBusStops - numStopsPassed, numBusStops - 1, 1);
			}
		}
		return toArray(ranges);
	}

	private void addRange(List<Range> ranges, int from, int to, int weight) {
		List<Range> copy = new ArrayList<>(ranges);
		ranges.clear();
		int start = 0;
		for (Range range : copy) {
			for (Range r : splitRange(range, start, from, to, weight)) {
				ranges.add(r);
			}
			start += range.length;
		}
		compress(ranges);
	}

	private void compress(List<Range> ranges) {
		List<Range> copy = new ArrayList<>(ranges);
		ranges.clear();
		int l = 0;
		while (l < copy.size()) {
			int r = l + 1;
			int len = copy.get(l).length;
			while (r < copy.size() && copy.get(r).weight == copy.get(l).weight) {
				len += copy.get(r).length;
				++r;
			}
			ranges.add(new Range(len, copy.get(l).weight));
			l = r;
		}
	}

	private Range[] splitRange(Range range, int start, int from, int to, int weight) {
		int end = range.length - 1 + start;
		List<Range> res = new ArrayList<>();
		if (to < start || from > end) {
			res.add(range);
			return toArray(res);
		}
		if (start < from) {
			res.add(new Range(from - start, range.weight));
			start = from;
		}
		res.add(new Range(Math.min(end, to) - start + 1, weight + range.weight));
		start += res.get(res.size() - 1).length;
		if (start <= end) {
			res.add(new Range(end - start + 1, range.weight));
		}
		return toArray(res);
	}

	private Range[] toRanges(int[] busStops, int numBusStops) {
		List<Range> res = new ArrayList<>();
		if (busStops.length == 0) {
			res.add(new Range(numBusStops, 0));
			return toArray(res);
		}
		if (busStops[0] > 0) {
			res.add(new Range(busStops[0], 0));
		}
		int l = 0;
		while (l < busStops.length) {
			int r = l + 1;
			while (r < busStops.length && busStops[r] == busStops[l]) ++r;
			if (res.isEmpty() || res.get(res.size() - 1).weight != r - l) {
				res.add(new Range(1, r - l));
			} else {
				Range last = res.get(res.size() - 1);
				res.set(res.size() - 1, new Range(last.length + 1, last.weight));
			}
			l = r;
		}
		if (busStops[busStops.length - 1] + 1 < numBusStops) {
			res.add(new Range(numBusStops - busStops[busStops.length - 1] - 1, 0));
		}
		return toArray(res);
	}

	private Range[] toArray(List<Range> cs) {
		Range[] res = new Range[cs.size()];
		cs.toArray(res);
		return res;
	}
}
