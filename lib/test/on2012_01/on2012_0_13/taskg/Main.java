package on2012_01.on2012_0_13.taskg;

import net.egork.chelper.tester.Tester;
import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!Tester.test("net.egork.utils.io.InputReader",
			"on2012_01.on2012_0_13.taskg.TaskG",
			"SINGLE",
			"zbbg/__2 3/__;;zbbg/__;;true::gaqipkajibk/__5 6/__;;gaqikpajibk/__;;true",
			"net.egork.utils.io.OutputWriter"))
		{
			Assert.fail();
		}
	}
}
