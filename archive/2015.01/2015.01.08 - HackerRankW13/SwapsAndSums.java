package net.spak;



import net.egork.collections.GTreapArray;
import net.egork.collections.Pair;
import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;


public class SwapsAndSums {
    final class SumArray extends GTreapArray<Long> {
        public SumArray(Long[] array) {
            super(array);
        }

        protected SumArray(Node<Long> root) {
            super(root);
        }

        @Override
        protected GTreapArray<Long> create(Node<Long> root) {
            return new SumArray(root);
        }

        @Override
        protected Long aggregate(Long first, Long second) {
            return first + second;
        }

        @Override
        protected Long empty() {
            return 0L;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int numElements = in.readInt();
        int numQueries = in.readInt();
        Long[] even = new Long[(numElements + 1) / 2];
        Long[] odd = new Long[numElements / 2];
        for (int i = 0; i < numElements; ++i) {
            if (i % 2 == 0) {
                even[i / 2] = in.readLong();
            } else {
                odd[i / 2] = in.readLong();
            }
        }
        GTreapArray<Long> evenArray = new SumArray(even);
        GTreapArray<Long> oddArray = new SumArray(odd);
        for (int queryIndex = 0; queryIndex < numQueries; ++queryIndex) {
            int type = in.readInt();
            int l = in.readInt();
            int r = in.readInt();
            --l;
            int le = (l + 1) / 2;
            int re = (r + 1) / 2;
            int lo = l / 2;
            int ro = r / 2;
            assert(ro - lo == re - le);
            if (type == 1) {
                Pair<GTreapArray<Long>, GTreapArray<Long>> p2 = evenArray.split(re);
                Pair<GTreapArray<Long>, GTreapArray<Long>> p1 = p2.first.split(le);
                Pair<GTreapArray<Long>, GTreapArray<Long>> q2 = oddArray.split(ro);
                Pair<GTreapArray<Long>, GTreapArray<Long>> q1 = q2.first.split(lo);
                evenArray = p1.first.merge(q1.second).merge(p2.second);
                oddArray = q1.first.merge(p1.second).merge(q2.second);
            } else {
                out.printLine(evenArray.sum(le, re) + oddArray.sum(lo, ro));
            }
        }
    }
}
