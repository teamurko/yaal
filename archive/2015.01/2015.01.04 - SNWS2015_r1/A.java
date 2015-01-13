package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class A {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int o = in.readInt();
        int n = in.readInt();
        int s = o * n;
        out.print(findMin(n, s) / n);
        out.print(" ");
        out.printLine(findMax(n, s) / n);
    }

    private double findMax(int n, int s) {
        s -= 60 * n;
        double ret = 2 * n;
        for (int i = 0; i < n; ++i) {
            if (s <= 0) break;
            if (s >= 25) {
                s -= 25;
                ret += 2;
                continue;
            }
            if (s >= 20) {
                s -= 20;
                ret += 1.5;
                continue;
            }
            if (s >= 15) {
                s -= 15;
                ret += 1;
                continue;
            }
            if (s >= 10) {
                s -= 10;
                ret += 0.5;
                continue;
            }
            break;
        }
        return ret;
    }

    private double findMin(int n, int s) {
        s -= 69 * n;
        double ret = 2 * n;
        if (s <= 0) return ret;
        for (int i = 0; i < n; ++i) {
            if (s >= 16) {
                s -= 31;
                ret += 2;
                continue;
            }
            if (s >= 11) {
                s -= 15;
                ret += 1.5;
                continue;
            }
            if (s >= 6) {
                s -= 10;
                ret += 1;
                continue;
            }
            if (s <= 0) break;
            s -= 5;
            ret += 0.5;
        }
        return ret;
    }
}
