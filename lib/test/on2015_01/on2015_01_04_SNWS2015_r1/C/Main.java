package on2015_01.on2015_01_04_SNWS2015_r1.C;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_01/on2015_01_04_SNWS2015_r1/C/C.task"))
			Assert.fail();
	}
}
