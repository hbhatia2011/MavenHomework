package maventest;

import org.hamcrest.core.Is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class RegistrationPage {
    protected static WebDriver driver;

    public String generateEmail(String startValue) {
        String email = startValue.concat(new Date().toString());

        return email;
    }
    public static String randomDate(){

        DateFormat format=new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }

    @BeforeMethod()
    public void openbrowser(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDrivers\\chromedriver.exe");

        //To open Browser
        driver=new ChromeDriver();
        //To maximise the Browser screen
        driver.manage().window().fullscreen();
        // To set the implicity wait for the driver object
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        //To open the website
        driver.get("https://demo.nopcommerce.com/");

    }
    //to quite the browser
    @AfterMethod
    public void closebrowser() {

        driver.quit();
    }

    @Test (priority = 0)
    public  void UsershouldbeabletoregisterSuccessfully() {

        //Click on Register tab - using Class locator
        driver.findElement(By.className("ico-register")).click();
        //enter firstname - using ID locator
        driver.findElement(By.id("FirstName")).sendKeys("Leena");
        //enter lastname - using ID locator
        driver.findElement(By.id("LastName")).sendKeys("Beena");
        //enter email address - using by Name locator
        driver.findElement(By.name("Email")).sendKeys("test"+randomDate()+"@test.com");
        System.out.println("test"+randomDate()+"@test.com");
        //enter password = using XPath locator
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("test123");
        //enter confirm password - using XPath locator
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("test123");
        // Click on Register - using ID Locator
        driver.findElement(By.id("register-button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //to test the actual and expected results
        String Expectedresults="Your registration completed";
        String Actualresult=driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(Actualresult,Expectedresults);


        }

    @Test(priority = 1)
    public  void UsershouldbeabletoReferaProducttoFriend() {

        //Click on Register tab - using Class locator
        driver.findElement(By.className("ico-register")).click();
        //enter firstname - using ID locator
        driver.findElement(By.id("FirstName")).sendKeys("H");
        //enter lastname - using ID locator
        driver.findElement(By.id("LastName")).sendKeys("BBB");
        //enter email address - using by Name locator
        driver.findElement(By.name("Email")).sendKeys("test"+randomDate()+"@test.com");
        System.out.println("test"+randomDate()+"@test.com");
        //enter password = using XPath locator
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("test123");
        //enter confirm password - using XPath locator
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("test123");
        // Click on Register - using ID Locator
        driver.findElement(By.id("register-button")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Click on Continue - using Name Locator
        driver.findElement(By.name("register-continue")) .click();
        //Select Apple MacBook Pro - using XPath Locator
        driver.findElement(By.xpath("//h2/a[@href=\"/apple-macbook-pro-13-inch\"]")).click();
        //Click on Email a Friend tab - using XPath Locator
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();
        //Enter Friend Email address- using XPath Locator
        driver.findElement(By.xpath("//input[@id='FriendEmail']")).sendKeys("info@test.com");
        //Enter personal message - using XPath Locator
        driver.findElement(By.xpath("//textarea[@id='PersonalMessage']")).sendKeys("Check this product");
        //Click on Send mail -  using Name Locator
        driver.findElement(By.name("send-email")).click();


        //to test the actual and expected results Email the product successfully
        String Expectedresults="Your message has been sent.";
        String Actualresult=driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(Actualresult,Expectedresults);

    }

    @Test(priority = 2)
    public  void UsershouldbeabletoNavigateCameraandPhotopage() {


        //Select Electronics Tab - using LinkText Locator
        driver.findElement(By.linkText("Electronics")).click();
        //Select Camera & photo tab - using LinkText Locator
        driver.findElement(By.linkText("Camera & photo")).click();

        //to test the actual and expected results

        String Expectedresults="Camera & photo";
        String Actualresult=driver.findElement(By.linkText("Camera & photo")).getText();
        Assert.assertEquals(Actualresult,Expectedresults);

    }

    @Test(priority = 4)
    public  void UsershouldbeabletoaddBookstoCart() {


        //Select Books Tab - using LinkText Locator
        driver.findElement(By.linkText("Books")).click();
        //Add to Cart - 'Fahrenheit 451 by Ray Bradbury' book - using Xpath Locator
        driver.findElement(By.xpath("//input[contains(@onclick,'/37/1/1')]")).click();
        //Check the Cart to confirm the book is added - using linktext Locator
        driver.findElement(By.linkText("Shopping cart")).click();
        //Click on Continue Shopping to add another book - using Name Locator
        driver.findElement(By.name("continueshopping")).click();
        //Add to Cart - 'First Prize Pies' book - using Xpath Locator
        driver.findElement(By.xpath("//input[contains(@onclick,'/38/1/1')]")).click();

        //to test the actual and expected results

        String Expectedresults="Shopping cart";
        String Actualresult=  driver.findElement(By.linkText("Shopping cart")).getText();
        Assert.assertEquals(Actualresult,Expectedresults);
    }

    @Test(priority = 3)
    public  void UsershouldbeabletoselectJwellerybypricefilter() {

        //Select Jewelry Tab - using LinkText Locator
        driver.findElement(By.linkText("Jewelry")).click();
        //Select price range $700-$3000 - Using     Locator
        driver.findElement(By.xpath("//a[contains(@href,\"700-3000\")]")) .click();

        //to test the Actual and Expected results

        //save expectedtitle by locators
        String expectedtitle = "$700.00 - $3,000.00";
        //save actualtitle by locators
        String actualtitle = driver.findElement(By.xpath("//span[@class= 'item']")).getText();
        Assert.assertEquals(actualtitle,expectedtitle);
        //find minimum price
        String minimumrange = driver.findElement(By.xpath("//span[@class=\"PriceRange\"and text()= '$700.00']")).getText();
        System.out.println(minimumrange);
        // find Actual price
        String actualrange = driver.findElement(By.xpath("//span[@class=\"price actual-price\" and text() ='$2,100.00']")).getText();
        System.out.println(actualrange);
        //find maximum price
        String maximumrange = driver.findElement(By.xpath("//span[@class=\"PriceRange\"and text()= '$3,000.00']")).getText();
        System.out.println(maximumrange);
        // convert string minrange to float
        float minrange = Float.parseFloat(minimumrange.substring(1));
        System.out.println(minrange);
        //convert String actualrange
        float arange = Float.parseFloat(actualrange.replace(",","").substring(1));
        System.out.println(arange);
        //Convert String maxminrange
        float maxrange = Float.parseFloat(maximumrange.replace(",","").substring(1));
        System.out.println(maxrange );
        //checking actual price between minimum and maximum range
        Assert.assertTrue(arange>=minrange && arange<=maxrange);




    }


}





