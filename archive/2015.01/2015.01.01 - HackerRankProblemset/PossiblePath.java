package net.spak;

import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class PossiblePath {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        testNumber = in.readInt();
        for (int it = 0; it < testNumber; ++it) {
            int a = in.readInt();
            int b = in.readInt();
            int x = in.readInt();
            int y = in.readInt();
            if (b == 0 && a == 0) {
                if (x == 0 && y == 0) {
                    out.printLine("YES");
                } else {
                    out.printLine("NO");
                }
            } else {
                if (x == 0 && y == 0) {
                    out.printLine("NO");
                } else {
                    if (IntegerUtils.gcd(a, b) != IntegerUtils.gcd(x, y)) {
                        out.printLine("NO");
                    } else {
                        out.printLine("YES");
                    }
                }
            }
        }
    }
}
