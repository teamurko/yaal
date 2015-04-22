package on2015_03.on2015_03_21_VK_Cup_2015___Round_1__unofficial_online_mirror__Div__1_only_.B___Group_Photo_20;





import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

import java.util.*;

public class TaskB {
	class Box {
		final int w;
		final int h;

		Box(int w, int h) {
			this.w = w;
			this.h = h;
		}
	}
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n = in.readInt();
		Box[] boxes = new Box[n];
		for (int i = 0; i < n; ++i) {
			int w = in.readInt();
			int h = in.readInt();
			boxes[i] = new Box(w, h);
		}
		int ans = 2000000000;
		for (int height = 1; height <= 1000; ++height) {
			int layCount = 0;
			int width = 0;
			List<Box> bs = new ArrayList<>();
			boolean fail = false;
			for (Box box : boxes) {
				if (box.h > height) {
					if (box.w > height) {
						fail = true;
						break;
					}
					width += box.h;
					++layCount;
				} else {
					bs.add(box);
				}
			}
			if (fail || layCount > n / 2) {
				continue;
			}
			int k = n / 2 - layCount;
			Collections.sort(bs, new Comparator<Box>() {
				@Override
				public int compare(Box o1, Box o2) {
					return (o1.h - o1.w) - (o2.h - o2.w);
				}
			});
			for (int i = 0; i < bs.size(); ++i) {
				Box b = bs.get(i);
				if (k > 0) {
					if (b.w > b.h && b.w <= height) {
						width += b.h;
						--k;
					} else {
						width += b.w;
					}
				} else {
					width += b.w;
				}
			}
			ans = Math.min(ans, height * width);
		}
		out.printLine(ans);
    }
}
