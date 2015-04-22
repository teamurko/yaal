package on2015_04.on2015_04_04_ZeptoLab_Code_Rush_2015.A___King_of_Thieves;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		char[] a = in.readString().toCharArray();
		boolean ok = false;
		for (int i = 0; i < n; ++i) {
			if (ok) break;
			if (a[i] == '.') continue;
			for (int j = i + 1; j < n; ++j) {
				if (ok) break;
				int d = j - i;
				boolean f = true;
				for (int k = 1; k <= 4; ++k) {
					if (i + k * d >= n) {
						f = false;
						break;
					}
					if (a[i + k * d] == '.') {
						f = false;
						break;
					}
				}
				if (f) ok = true;
			}
		}
		if (ok) out.printLine("yes");
		else out.printLine("no");
    }
}
