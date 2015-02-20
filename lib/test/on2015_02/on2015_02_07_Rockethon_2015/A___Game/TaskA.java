package on2015_02.on2015_02_07_Rockethon_2015.A___Game;



import net.egork.utils.io.InputReader;
import net.egork.utils.io.OutputWriter;

public class TaskA {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
		int n1 = in.readInt();
		int n2 = in.readInt();
		if (n1 > n2) out.printLine("First");
		else out.printLine("Second");
    }
}
