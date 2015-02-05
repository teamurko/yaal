package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.Arrays;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        char[] a = in.readString().toCharArray();
        int n = a.length;
        int evenIndex = -1;
        for (int i = 0; i < n; ++i) {
            if ((a[i] - '0') % 2 == 0) {
                if (a[i] < a[n - 1]) {
                    char c = a[i];
                    a[i] = a[n - 1];
                    a[n - 1] = c;
                    out.printLine(new String(a));
                    return;
                }
                evenIndex = i;
            }
        }
        if (evenIndex == -1) {
            out.printLine(-1);
        } else {
            char c = a[evenIndex];
            a[evenIndex] = a[n - 1];
            a[n - 1] = c;
            out.printLine(new String(a));
        }
    }
}
