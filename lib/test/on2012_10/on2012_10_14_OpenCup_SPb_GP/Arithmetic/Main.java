package on2012_10.on2012_10_14_OpenCup_SPb_GP.Arithmetic;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2012_10/on2012_10_14_OpenCup_SPb_GP/Arithmetic/Arithmetic.task"))
			Assert.fail();
	}
}
