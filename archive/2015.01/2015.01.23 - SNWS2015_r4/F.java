package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class F {
    char[][] f;
    boolean[][] used;
    int n, m;
    final int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
    final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    boolean isPal(String w) {
        for (int i = 0; i < w.length(); ++i) {
            if (w.charAt(i) != w.charAt(w.length() - 1 - i)) return false;
        }
        return true;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int nw = in.readInt();
        n = in.readInt();
        m = in.readInt();
        f = new char[n][];
        used = new boolean[n][];
        for (int i = 0; i < n; ++i) {
            used[i] = ArrayUtils.createArray(m, false);
            String s = in.readString();
            f[i] = new char[m];
            for (int j = 0; j < m; ++j) {
                f[i][j] = s.charAt(j);
            }
        }
        String[] words = new String[nw];
        for (int i = 0; i < nw; ++i) {
            words[i] = in.readString();
        }

        int nonOneCount = 0;
        for (String w : words) {
            int count = addWord(w);
            if (count == 0) {
                out.printLine("no solution");
                return;
            } else if (count > 1) {
                ++nonOneCount;
            }
        }

        if (nonOneCount > 0) {
            out.printLine("ambiguous");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < m; ++y) {
                if (!used[x][y]) {
                    sb.append(f[x][y]);
                    ++count;
                }
            }
        }
        if (count == 0) {
            out.printLine("empty spell");
        } else {
            out.printLine(sb);
        }
    }

    private int addWord(String w) {
        boolean pal = isPal(w);
        int numOcc = 0;
        int len = w.length();
        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < m; ++y) {
                for (int i = 0; i < dx.length; ++i) {
                    if (!isInside(x + dx[i] * (len - 1), y + dy[i] * (len - 1))) continue;
                    int xx = x;
                    int yy = y;
                    boolean ok = true;
                    for (int j = 0; j < len; ++j) {
                        if (f[xx][yy] != w.charAt(j)) {
                            ok = false;
                            break;
                        }
                        xx += dx[i];
                        yy += dy[i];
                    }
                    if (ok) {
                        ++numOcc;
                        xx = x;
                        yy = y;
                        for (int j = 0; j < len; ++j) {
                            used[xx][yy] = true;
                            xx += dx[i];
                            yy += dy[i];
                        }
                    }
                }
            }
        }

        if (w.length() == 1) {
            assert(numOcc % 8 == 0);
            return numOcc / 8;
        }
        if (pal) {
            assert(numOcc % 2 == 0);
            return numOcc / 2;
        }
        return numOcc;
    }
}
