package Com.OrangeHRMTest;

import java.util.logging.Logger;

public class Test {

	final static Logger logger = Logger.getLogger(Test.class.getName());

	public static void main(String[] args) {

		Test obj = new Test();
		try {
			obj.divide();
		} catch (ArithmeticException ex) {
			logger.info("Sorry, something wrong!" + ex);
		}

	}

	private void divide() {

		int i = 10 / 0;

		System.out.println(i);
	}

}
