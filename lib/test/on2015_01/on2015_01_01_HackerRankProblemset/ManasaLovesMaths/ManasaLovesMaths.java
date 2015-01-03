package on2015_01.on2015_01_01_HackerRankProblemset.ManasaLovesMaths;



import net.egork.collections.CollectionUtils;
import net.egork.collections.filter.Filter;
import net.egork.collections.sequence.StringWrapper;
import net.egork.misc.ArrayUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.string.StringUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.ArrayList;
import java.util.List;

public class ManasaLovesMaths {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        boolean foundMatch = false;
        String n = in.readLine();
        if (n.length() < 4) {
            foundMatch = naiveMatch(n);
        } else {
            int numZeros = CollectionUtils.count(StringWrapper.wrap(n), new Filter<Character>() {
                @Override
                public boolean accept(Character value) {
                    return Character.getNumericValue(value.charValue()) == 0;
                }
            });
            for (int i = 0; i < n.length() && !foundMatch; ++i) {
                for (int j = 0; j < n.length() && !foundMatch; ++j) {
                    if (j == i) continue;
                    for (int k = 0; k < n.length(); ++k) {
                        if (k == i || k == j) continue;
                        int nz = 0;
                        if (n.charAt(i) == '0') ++nz;
                        if (n.charAt(j) == '0') ++nz;
                        if (n.charAt(k) == '0') ++nz;
                        if (n.length() - numZeros - (3 - nz) == 0) continue;
                        int num = Character.getNumericValue(n.charAt(i)) * 100;
                        num += Character.getNumericValue(n.charAt(j)) * 10;
                        num += Character.getNumericValue(n.charAt(k));
                        if (num % 8 == 0) {
                            foundMatch = true;
                            break;
                        }
                    }
                }
            }
        }
        if (foundMatch) {
            out.printLine("YES");
        } else {
            out.printLine("NO");
        }
    }

    private boolean naiveMatch(String n) {
        if (n.length() == 1) {
            return Integer.parseInt(n) % 8 == 0;
        } else if (n.length() == 2) {
            return Integer.parseInt(n) % 8 == 0 || Integer.parseInt(StringUtils.reverse(n)) % 8 == 0;
        } else {
            boolean foundMatch = false;
            for (int i = 0; i < n.length() && !foundMatch; ++i) {
                for (int j = 0; j < n.length() && !foundMatch; ++j) {
                    if (i == j) continue;
                    for (int k = 0; k < n.length(); ++k) {
                        if (k == i || k == j) continue;
                        int num = Character.getNumericValue(n.charAt(i)) * 100;
                        if (num == 0) continue;
                        num += Character.getNumericValue(n.charAt(j)) * 10;
                        num += Character.getNumericValue(n.charAt(k));
                        if (num % 8 == 0) {
                            foundMatch = true;
                            break;
                        }
                    }
                }
            }
            return foundMatch;
        }
    }

}
