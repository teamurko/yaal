package on2015_01.on2015_01_28_Codeforces_Round__287__Div__2_.C___Guess_Your_Way_Out_;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskC {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long h = in.readLong();
        long n = in.readLong();
        long l = 1;
        long r = 1L << h;
        long ans = 0;
        boolean left = true;
        while (l < r) {
            long m = (l + r) >> 1;
            if (n <= m) {
                if (left) {
                    ++ans;
                } else {
                    ans += r - l + 1;
                }
                r = m;
                left = false;
            } else {
                if (left) {
                    ans += r - l + 1;
                } else {
                    ++ans;
                }
                l = m + 1;
                left = true;
            }
        }
        out.printLine(ans);
    }
}
