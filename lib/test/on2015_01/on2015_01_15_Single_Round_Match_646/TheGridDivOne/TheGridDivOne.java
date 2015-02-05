package on2015_01.on2015_01_15_Single_Round_Match_646.TheGridDivOne;



import net.egork.misc.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

public class TheGridDivOne {
    private static final long INF = (long) 1e14;

    class Point implements Comparable<Point> {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public int compareTo(Point o) {
            if (x != o.x) return x - o.x;
            return y - o.y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point) o;
                return p.x == x && p.y == y;
            } return false;
        }
    }
    public int find(int[] x, int[] y, int k) {
        if (x.length == 0) {
            return k;
        }
        int maxN = x.length * 20;
        Point[] ps = new Point[maxN];
        int n = 1;
        ps[0] = new Point(0, 0);
        Set<Point> used = new HashSet<>();
        for (int i = 0; i < x.length; ++i) {
            used.add(new Point(x[i], y[i]));
        }
        used.add(new Point(0, 0));
        for (int i = 0; i < x.length; ++i) {
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dy = -1; dy <= 1; ++dy) {
                    if (dx == 0 && dy == 0) continue;
                    Point p = new Point(x[i] + dx, y[i] + dy);
                    if (!used.contains(p)) {
                        used.add(p);
                        ps[n++] = p;
                    }
                }
            }
        }
        long[] dist = ArrayUtils.createArray(n, INF);
        dist[0] = 0;
        boolean[] us = ArrayUtils.createArray(n, false);
        while (true) {
            int v = -1;
            for (int i = 0; i < n; ++i) {
                if (dist[i] != INF && !us[i]) {
                    if (v == -1 || dist[i] < dist[v]) {
                        v = i;
                    }
                }
            }
            if (v == -1) break;
            us[v] = true;
            for (int u = 0; u < n; ++u) {
                if (!us[u] && can(ps[v], ps[u], x, y)) {
                    long add = calcDist(ps[v], ps[u]);
                    if (dist[v] + add < dist[u]) {
                        dist[u] = dist[v] + add;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (dist[i] == INF) continue;
            if (dist[i] > k) continue;
            int candx = ps[i].x;
            int rightx = candx + k;
            rightx -= dist[i];
            for (int j = 0; j < x.length; ++j) {
                if (y[j] == ps[i].y && candx < x[j]) {
                    rightx = Math.min(rightx, x[j] - 1);
                }
            }
            ans = Math.max(ans, rightx);
        }
		return ans;
    }

    private boolean can(Point p, Point q, int[] x, int[] y) {
        for (int i = 0; i < x.length; ++i) {
            if (inside(p.x, q.x, x[i]) && inside(p.y, q.y, y[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean inside(int a, int b, int c) {
        return Math.min(a, b) <= c && c <= Math.max(a, b);
    }

    private long calcDist(Point p, Point q) {
        return Math.abs(p.x - (long) q.x) + Math.abs(p.y - (long)q.y);
    }
}
