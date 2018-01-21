package main.java.com.dbyl.appiumCore.testng;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
// TODO: Auto-generated Javadoc

/**
 * The listener interface for receiving testng events.
 * The class that is interested in processing a testng
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTestngListener<code> method. When
 * the testng event occurs, that object's appropriate
 * method is invoked.
 *
 * @see TestngEvent
 */
public class TestngListener extends TestListenerAdapter {

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSuccess(tr);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailure(tr);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSkipped(tr);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestFailedButWithinSuccessPercentage(tr);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onStart(testContext);
		ITestNGMethod[] itm = testContext.getAllTestMethods();
		for(ITestNGMethod it:itm)
		{
			String[] groups = it.getGroups();
			for(String group:groups)
			{
				System.out.print("Start --- "+group);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext testContext) {
		// TODO Auto-generated method stub
		super.onFinish(testContext);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#getFailedButWithinSuccessPercentageTests()
	 */
	@Override
	public List<ITestResult> getFailedButWithinSuccessPercentageTests() {
		// TODO Auto-generated method stub
		return super.getFailedButWithinSuccessPercentageTests();
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#getFailedTests()
	 */
	@Override
	public List<ITestResult> getFailedTests() {
		// TODO Auto-generated method stub
		return super.getFailedTests();
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#getPassedTests()
	 */
	@Override
	public List<ITestResult> getPassedTests() {
		// TODO Auto-generated method stub
		return super.getPassedTests();
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#getSkippedTests()
	 */
	@Override
	public List<ITestResult> getSkippedTests() {
		// TODO Auto-generated method stub
		return super.getSkippedTests();
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		super.onTestStart(result);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#getTestContexts()
	 */
	@Override
	public List<ITestContext> getTestContexts() {
		// TODO Auto-generated method stub
		return super.getTestContexts();
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onConfigurationFailure(org.testng.ITestResult)
	 */
	@Override
	public void onConfigurationFailure(ITestResult itr) {
		// TODO Auto-generated method stub
		super.onConfigurationFailure(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onConfigurationSkip(org.testng.ITestResult)
	 */
	@Override
	public void onConfigurationSkip(ITestResult itr) {
		// TODO Auto-generated method stub
		super.onConfigurationSkip(itr);
	}

	/* (non-Javadoc)
	 * @see org.testng.TestListenerAdapter#onConfigurationSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onConfigurationSuccess(ITestResult itr) {
		// TODO Auto-generated method stub
		super.onConfigurationSuccess(itr);
	}
	

}
