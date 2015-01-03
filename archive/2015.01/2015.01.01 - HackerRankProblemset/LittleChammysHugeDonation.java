package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LittleChammysHugeDonation {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        List<Long> borders = precalc();
        testNumber = in.readInt();
        for (int it = 0; it < testNumber; ++it) {
            long numCandies = in.readLong();
            int index = Collections.binarySearch(borders, numCandies);
            if (index < 0) {
                index = -index - 1;
            } else {
                index += 1;
            }
            out.printLine(index);
        }
    }

    private List<Long> precalc() {
        final long MAX_INPUT = 1000L * 1000L * 1000L * 1000L * 1000L * 10L;
        List<Long> borders = new ArrayList<>();
        long s = 1;
        for (long i = 2; s <= MAX_INPUT; ++i) {
            borders.add(s);
            s += i * i;
        }
        return borders;
    }
}
