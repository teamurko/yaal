package net.spak;

import net.egork.geometry.GeometryUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskB {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        long r = in.readLong();
        long x1 = in.readLong();
        long y1 = in.readLong();
        long x2 = in.readLong();
        long y2 = in.readLong();
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        out.printLine(Math.round(Math.ceil(d / r / 2)));
    }
}
