package on2015_01.on2015_01_23_SNWS2015_r4.A;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_01/on2015_01_23_SNWS2015_r4/A/A.task"))
			Assert.fail();
	}
}
