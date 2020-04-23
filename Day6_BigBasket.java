package seleniumPractiseSessions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day6_BigBasket {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//1.open url
		driver.get("https://www.bigbasket.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//2.mouse over on  Shop by Category
		WebElement category = driver.findElementByXPath("//a[text()=' Shop by Category ']");
		Actions category1=new Actions(driver);
		category1.moveToElement(category).perform();
		
		//3.Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS
		Thread.sleep(3000);
		WebElement foodgrains = driver.findElementByXPath("(//a[text()='Foodgrains, Oil & Masala'])[2]");
		Actions foodgrn=new Actions(driver);
		foodgrn.moveToElement(foodgrains).build().perform();
		driver.findElementByXPath("(//a[@ng-href='/pc/foodgrains-oil-masala/rice-rice-products/?nc=nb'])[2]").click();
		
		//4.Click on Boiled & Steam Rice
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[text()='Boiled & Steam Rice'])[1]").click();

         //5.Choose the Brand as bb Royal
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[text()='bb Royal'])[1]").click();
		
		//6.Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		JavascriptExecutor  js1=(JavascriptExecutor) driver;  //page scrolldown
        js1.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(2000);	
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::div[@class='btn-group btn-input clearfix ng-scope'])[1]").click();
	    Thread.sleep(3000);
		driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[contains(text(),'5 kg')])[1]").click();
		
	    
	    //7.print the price of Rice
	    String riceprice = driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::span[@class='discnt-price'])[1]").getText();
	    System.out.println(riceprice);
	    Thread.sleep(2000);
	    
		
		//8. Click Add button
	    driver.findElementByXPath("(//a[text()='Ponni Boiled Rice - Super Premium']/following::button[@qa='add'])[1]").click();
		Thread.sleep(2000);
	    driver.findElementByXPath("(//a[text()='Continue'])[1]").click();
		
		//9.Verify the success message displayed 
	    //wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//div[@class='toast-title']")));
		//String ricebag = driver.findElementByXPath("//div[@class='toast-title']").getText();
		//System.out.println("added item is:"+ricebag);
		//driver.findElementByClassName("toast-close-button").click();
		
		//10. Type Dal in Search field and enter
		driver.findElementByXPath("(//input[@placeholder='Search for Products..'])[1]").sendKeys("Dal",Keys.ENTER);
		
		
		//12. Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[@class='btn btn-default dropdown-toggle form-control'])[1]").click();
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[text()='2 kg'])[1]").click();
		WebElement quantity = driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::span[text()='Qty']/following-sibling::input)[1]");
		quantity.clear();
		quantity.sendKeys("2");
		
		//13.Print the price of Dal
		Thread.sleep(2000);
		String price = driver.findElementByXPath("//span[text()='219']").getText();
		System.out.println("price of dal is:"+price);
		
		//14. Click Add button
		driver.findElementByXPath("(//a[text()='Toor/Arhar Dal/Thuvaram Paruppu']/following::button[contains(text(),'Add')])[1]").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='toast-title']")));
		String dalSuccessMsg= driver.findElementByXPath("//div[@class='toast-title']").getText();
		System.out.println(dalSuccessMsg);
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		
		//15. Mouse hover on My Basket 
		WebElement basket = driver.findElementByXPath("//span[@title='Your Basket']");
		Actions myBasket=new Actions(driver);
		myBasket.moveToElement(basket).perform();
		Thread.sleep(2000);
		
		//16. Validate the Sub Total displayed for the selected items
		String Product1 = driver.findElementByXPath("(//div[@class='row mrp']/span)[1]").getText();
		String Product2 = driver.findElementByXPath("(//div[@class='row mrp']/span)[2]").getText();
		String Total = driver.findElementByXPath("//span[@style='float: right']/span").getText();
		double sum = Double.parseDouble(Product1)+Double.parseDouble(Product2);
		double subTotal = Double.parseDouble(Total);
		
		if (sum == subTotal) {
			System.out.println("Total amount matches ");
		}else {
			System.out.println("Mismatch in total amount");
		}
		
	
		//17. Reduce the Quantity of Dal as 1 
		driver.findElementByXPath("(//button[@qa='decQtyMB'])[2]").click();
		Thread.sleep(2000);
		
		
		//18. Validate the Sub Total for the current items
		String NewProduct2 = driver.findElementByXPath("(//div[@class='row mrp']/span)[2]").getText();
		String newTotal = driver.findElementByXPath("//span[@style='float: right']/span").getText();
		double Sum2 = Double.parseDouble(Product1)+Double.parseDouble(NewProduct2);
		double NewsubTotal = Double.parseDouble(newTotal);
		
		if (Sum2 == NewsubTotal) {
			System.out.println("Total amount matches after reducing the Quantity of Dal as 1 ");
		}else {
			System.out.println("Mismatch amount  after reducing the Quantity of Dal ");
		}
		
		
		//19. Close the Browser
		driver.close();
		
		
		

		
		

	}

}
