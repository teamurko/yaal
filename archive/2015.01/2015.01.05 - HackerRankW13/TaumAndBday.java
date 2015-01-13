package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaumAndBday {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long b = in.readLong();
        long w = in.readLong();
        long x = in.readLong();
        long y = in.readLong();
        long z = in.readLong();
        out.printLine(b * Math.min(x, y + z) + w * Math.min(y, x + z));
    }
}
