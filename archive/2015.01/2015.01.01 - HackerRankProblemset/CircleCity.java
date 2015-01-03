package net.spak;

import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class CircleCity {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int numTests = in.readInt();
        for (int testId = 0; testId < numTests; ++testId) {
            int radiusSquared = in.readInt();
            int numStations = in.readInt();
            if (isEnough(radiusSquared, numStations)) {
                out.print("possible\n");
            } else {
                out.print("impossible\n");
            }
        }
    }

    private boolean isEnough(int radiusSquared, int numStations) {
        int radius = (int) (Math.sqrt(radiusSquared) + 0.1);
        for (int x = -radius; x <= radius; ++x) {
            int y2 = radiusSquared - x * x;
            if (y2 < 0) continue;
            int y = (int) (Math.sqrt(y2) + 0.1);
            if (y * y == y2) {
                if (y2 == 0) {
                    numStations -= 1;
                } else {
                    numStations -= 2;
                }
                if (numStations < 0) {
                    return false;
                }
            }
        }
        return numStations >= 0;
    }
}
