package net.spak;

import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.collections.function.Function;
import net.egork.misc.ArrayUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int k = in.readInt();
        Pair<Integer, Integer> a[] = new Pair[n];
        for (int i = 0; i < n; ++i) a[i] = Pair.makePair(in.readInt(), i);
        Arrays.sort(a);
        List<Integer> result = new ArrayList<>();
        for (Pair<Integer, Integer> x : a) {
            k -= x.first;
            if (k >= 0) {
                result.add(x.second + 1);
            }
            else break;
        }
        out.printLine(result.size());
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Integer i : result) {
            if (!first)
                sb.append(" ");
            sb.append(i);
            first = false;
        }
        out.printLine(sb);
    }
}
