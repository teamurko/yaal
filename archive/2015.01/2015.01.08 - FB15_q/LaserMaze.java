package net.spak;

import net.egork.misc.ArrayUtils;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.LinkedList;
import java.util.Queue;

public class LaserMaze {
    private static final int INF = (int) 1e8;
    char[][] maze;
    int n;
    int m;

    final int[] DX = {0, -1, 0, 1};
    final int[] DY = {-1, 0, 1, 0};

    int direction(char c) {
        return "<^>v".indexOf(c);
    }

    class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point shift(int i) {
            return new Point(x + DX[i], y + DY[i]);
        }

        boolean isValid() {
            return x >= 0 && y >= 0 && x < n && y < m;
        }

        boolean isFree() {
            return isValid() && (maze[x][y] == '.' || maze[x][y] == 'G');
        }

        boolean isExit() {
            return isValid() && maze[x][y] == 'G';
        }

        boolean isLaser() {
            return isValid() && direction(maze[x][y]) >= 0;
        }

        boolean isDead(int turn) {
            for (int i = 0; i < DX.length; ++i) {
                Point p = new Point(x + DX[i], y + DY[i]);
                while (p.isFree()) {
                    p = p.shift(i);
                }
                if (p.isLaser()) {
                    if ((direction(maze[p.x][p.y]) + turn + 2) % DX.length == i) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class State {
        final Point p;
        final int turn;

        State(Point p, int turn) {
            this.p = p;
            this.turn = turn;
        }
    }
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        n = in.readInt();
        m = in.readInt();
        maze = new char[n][];
        for (int i = 0; i < n; ++i) {
            maze[i] = in.readString().toCharArray();
        }
        Point start = new Point(0, 0);
        for (int x = 0; x < n; ++x) {
            for (int y = 0; y < m; ++y) {
                if (maze[x][y] == 'S') {
                    maze[x][y] = '.';
                    start = new Point(x, y);
                    break;
                }
            }
        }

        int[][][] dist = new int[4][][];
        for (int i = 0; i < 4; ++i) {
            dist[i] = new int[n][];
            for (int x = 0; x < n; ++x) {
                dist[i][x] = ArrayUtils.createArray(m, INF);
            }
        }
        dist[0][start.x][start.y] = 0;

        Queue<State> queue = new LinkedList<>();
        queue.add(new State(start, 0));

        while (!queue.isEmpty()) {
            State s = queue.poll();
            if (s.p.isExit()) {
                out.printLine(String.format("Case #%d: %d", testNumber, dist[s.turn][s.p.x][s.p.y]));
                return;
            }
            for (int i = 0; i < DX.length; ++i) {
                Point p = s.p.shift(i);
                if (!p.isFree()) continue;
                int turn = (s.turn + 1) % DX.length;
                if (p.isDead(turn)) continue;
                if (dist[turn][p.x][p.y] == INF) {
                    dist[turn][p.x][p.y] = dist[s.turn][s.p.x][s.p.y] + 1;
                    queue.add(new State(p, turn));
                }
            }
        }
        out.printLine(String.format("Case #%d: impossible", testNumber));
    }
}
