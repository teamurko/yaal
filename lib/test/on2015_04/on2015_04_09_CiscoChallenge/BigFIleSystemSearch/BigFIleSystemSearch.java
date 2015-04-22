package on2015_04.on2015_04_09_CiscoChallenge.BigFIleSystemSearch;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;
import java.util.HashMap;
import java.util.Map;

public class BigFIleSystemSearch {
	static class File {
		final Map<Integer, Integer> freqSet;

		File(Map<Integer, Integer> data) {
			this.freqSet = new HashMap<>();
		}

		int containsCount(Map<Integer, Integer> content) {
			int count = 0;
			for (Map.Entry<Integer, Integer> entry : content.entrySet()) {
				if (freqSet.containsKey(entry.getKey())) {
					if (freqSet.get(entry.getKey()) >= entry.getValue()) {
						++count;
					}
				}
			}
			return count;
		}


		static File read(InputReader in) {
			return new File(readContent(in));
		}
	}

	static Map<Integer, Integer> readContent(InputReader in) {
		int length = in.readInt();
		Map<Integer, Integer> freqSet = new HashMap<>();
		for (int i = 0; i < length; ++i) {
			int v = in.readInt();
			if (freqSet.containsKey(v)) {
				freqSet.put(v, freqSet.get(v) + 1);
			} else {
				freqSet.put(v, 1);
			}
		}
		return freqSet;
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int numFiles = in.readInt();
		File[] files = new File[numFiles];
		for (int i = 0; i < numFiles; ++i) {
			files[i] = File.read(in);
		}
		int numQueries = in.readInt();
		for (int qId = 0; qId < numQueries; ++qId) {
			int type = in.readInt();
			Map<Integer, Integer> content = readContent(in);
			int ans = 0;
			for (File file : files) {
				int count = file.containsCount(content);
				if (type == 1) {
					if (count == content.size()) ++ans;
				} else if (type == 2) {
					if (count > 0) ++ans;
				} else {
					if (count > 0 && count < content.size()) ++ans;
				}
			}
			out.printLine(ans);
		}
    }
}
