package main.java.com.dbyl.appiumCore.testng;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import main.java.com.dbyl.appiumCore.utils.CaseId;

/**
 * The listener interface for receiving testng events. The class that is
 * interested in processing a testng event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addTestngListener<code> method. When the testng event
 * occurs, that object's appropriate method is invoked.
 *
 * @author Young
 * @version V1.0
 * @see TestngEvent
 */
public class TestngListener extends TestListenerAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		ITestNGMethod im = tr.getMethod();
		getCaseID(im);
		super.onTestFailure(tr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		ITestNGMethod im = tr.getMethod();
		getCaseID(im);
		super.onTestSkipped(tr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext testContext) {
		System.out.println("Start Test");
		super.onStart(testContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#getFailedTests()
	 */
	@Override
	public List<ITestResult> getFailedTests() {
		// TODO Auto-generated method stub
		return super.getFailedTests();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#getPassedTests()
	 */
	@Override
	public List<ITestResult> getPassedTests() {
		// TODO Auto-generated method stub
		return super.getPassedTests();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#getSkippedTests()
	 */
	@Override
	public List<ITestResult> getSkippedTests() {
		// TODO Auto-generated method stub
		return super.getSkippedTests();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult result) {
		ITestNGMethod im = result.getMethod();
		getCaseID(im);
		super.onTestStart(result);
	}

	/**
	 * Gets the case ID.
	 *
	 * @param im
	 *            the im
	 * @return the case ID
	 */
	private String getCaseID(ITestNGMethod im) {
		String[] groups = im.getGroups();
		for (String group : groups) {
			System.out.println("++++++++++++++++>>>>>>>>>>>>>\n\n\n" + group + "<<<<<<<\n\n");
		}
		Method m = im.getConstructorOrMethod().getMethod();

		CaseId caseId = m.getAnnotation(CaseId.class);
		if (null != caseId) {
				System.out.println("++++++++++++++++>>>>>>>>>>>>>\n\n\n" + caseId.id() + "<<<<<<<\n\n");
		 
			return caseId.id();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.TestListenerAdapter#getTestContexts()
	 */
	@Override
	public List<ITestContext> getTestContexts() {
		// TODO Auto-generated method stub
		return super.getTestContexts();
	}

}
