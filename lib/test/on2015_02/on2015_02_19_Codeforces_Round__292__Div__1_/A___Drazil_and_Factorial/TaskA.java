package on2015_02.on2015_02_19_Codeforces_Round__292__Div__1_.A___Drazil_and_Factorial;



import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		String s = in.readString();
		int[] count = ArrayUtils.createArray(8, 0);
		for (char c : s.toCharArray()) {
			if (c == '0') continue;
			for (int i = 2; i <= c - '0'; ++i) {
				int num = i;
				for (int divisor = 2; divisor <= num; ++divisor) {
					while (num % divisor == 0) {
						count[divisor]++;
						num /= divisor;
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int digit = 7; digit >= 2; --digit) {
			if (count[digit] == 0) continue;
			for (int i = 0; i < count[digit]; ++i) {
				sb.append(digit);
			}
			int c = count[digit];
			for (int i = 2; i <= digit; ++i) {
				int num = i;
				for (int divisor = 2; divisor <= num; ++divisor) {
					while (num % divisor == 0) {
						num /= divisor;
						count[divisor] -= c;
					}
				}
			}
		}
		out.printLine(sb);
    }
}
