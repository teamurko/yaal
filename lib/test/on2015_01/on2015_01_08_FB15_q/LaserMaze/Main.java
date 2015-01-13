package on2015_01.on2015_01_08_FB15_q.LaserMaze;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("lib/test/on2015_01/on2015_01_08_FB15_q/LaserMaze/LaserMaze.task"))
			Assert.fail();
	}
}
