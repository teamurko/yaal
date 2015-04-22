package on2015_03.on2015_03_21_VK_Cup_2015___Round_1__unofficial_online_mirror__Div__1_only_.D___Social_Network;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TaskD {
	class Request {
		final int id;
		final long beginTime;
		final long endTime;

		Request(int id, long beginTime, long endTime) {
			this.id = id;
			this.beginTime = beginTime;
			this.endTime = endTime;
		}
	}

	class Event {
		final int id;
		final long time;
		final int type;

		Event(int id, long time, int type) {
			this.id = id;
			this.time = time;
			this.type = type;
		}
	}

	public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		int M = in.readInt();
		int T = in.readInt();
		Request[] requests = new Request[n];
		long[] times = new long[n];
		for (int i = 0; i < n; ++i) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			try {
				Date date = sdf.parse(in.readString());
				times[i] = date.getTime() / 1000;
			} catch (ParseException e) {
				throw new RuntimeException();
			}
		}
		long minTime = ArrayUtils.minElement(times);
		List<Event> events = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			requests[i] = new Request(i, times[i] - minTime, times[i] - minTime + T - 1);
			events.add(new Event(i, requests[i].beginTime, 0));
			events.add(new Event(i, requests[i].endTime + 1, 1));
		}
		Collections.sort(events, new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {
				int r = (int) (o1.time - o2.time);
				if (r != 0) return r;
				return -o1.type + o2.type;
			}
		});
		long bestTime = -1;
		int eventId = 0;
		int balance = 0;
		int bestBalance = -1;
		for (long time = 0; ; ++time) {
			while (eventId < events.size() && events.get(eventId).time <= time) {
				if (events.get(eventId).type == 0) {
					++balance;
				} else {
					--balance;
				}
				++eventId;
			}
			if (balance >= M) {
				if (bestBalance == -1 || bestBalance > balance) {
					bestBalance = balance;
					bestTime = time;
				}
			}
			if (events.size() == eventId) break;
		}
		if (bestTime == -1) {
			out.printLine("No solution");
			return;
		}

		int[] ids = ArrayUtils.createArray(n, -1);
		int id = 0;

		eventId = 0;
		Deque<Integer> colors = new LinkedList<>();
		for (long time = 0; ; ++time) {
			while (eventId < events.size() && events.get(eventId).time <= time) {
				if (events.get(eventId).type == 0) {
					int newId;
					if (colors.isEmpty() || colors.getLast() - colors.getFirst() + 1 < M) {
						newId = id++;
					} else {
						newId = id - 1;
					}
					ids[events.get(eventId).id] = newId;
					colors.addLast(newId);
				} else {
					colors.removeFirst();
				}
				++eventId;
			}

			if (eventId == events.size()) break;
		}

		out.printLine(id);
		for (int i : ids) {
			out.printLine(i + 1);
		}
	}
}
