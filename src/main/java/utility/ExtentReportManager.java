/*
 * package utility;
 * 
 * import java.text.SimpleDateFormat; import java.util.Date;
 * 
 * import org.testng.ITestContext; import org.testng.ITestListener; import
 * org.testng.ITestResult;
 * 
 * import com.aventstack.extentreports.ExtentReports; import
 * com.aventstack.extentreports.ExtentTest; import
 * com.aventstack.extentreports.reporter.ExtentSparkReporter; import
 * com.aventstack.extentreports.reporter.configuration.Theme;
 * 
 * public class ExtentReportManager implements ITestListener {
 * 
 * ExtentSparkReporter htmlReporter; ExtentReports reports; ExtentTest test;
 * 
 * public void configureReport() { String timestamp = new
 * SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); String reportName
 * = "Test-Report" + timestamp + ".html"; htmlReporter = new
 * ExtentSparkReporter(".\\reports\\" + reportName);
 * 
 * reports = new ExtentReports();
 * 
 * reports.attachReporter(htmlReporter);
 * 
 * reports.setSystemInfo("Machine", "PC"); reports.setSystemInfo("OS",
 * "Windows 10");
 * 
 * htmlReporter.config().setDocumentTitle("Report for Test of Ecom Site");
 * htmlReporter.config().setReportName("Extent Report");
 * htmlReporter.config().setTheme(Theme.DARK);
 * 
 * }
 * 
 * @Override public void onTestStart(ITestResult result) {
 * test=reports.createTest(result.getMethod().getMethodName()); }
 * 
 * @Override public void onTestSuccess(ITestResult result) {
 * test.pass("Test Passed");
 * 
 * }
 * 
 * @Override public void onTestFailure(ITestResult result) {
 * test.fail(result.getThrowable());
 * 
 * }
 * 
 * @Override public void onTestSkipped(ITestResult result) {
 * test.skip("Test Skipped");
 * 
 * }
 * 
 * @Override public void onTestFailedButWithinSuccessPercentage(ITestResult
 * result) {
 * 
 * }
 * 
 * @Override public void onTestFailedWithTimeout(ITestResult result) { }
 * 
 * @Override public void onStart(ITestContext context) { configureReport(); }
 * 
 * @Override public void onFinish(ITestContext context) { reports.flush(); } }
 */