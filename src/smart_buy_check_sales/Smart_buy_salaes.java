package smart_buy_check_sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Smart_buy_salaes {

public WebDriver driver;
public SoftAssert softassert=new SoftAssert();


@BeforeTest

public void open_web_site() {
	
	WebDriverManager.chromedriver().setup();
	
	driver=new ChromeDriver();
	
	driver.get("https://smartbuy-me.com/smartbuystore/ar/");
	driver.manage().window().maximize();
	driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
	
	
}
@Test
public void test_sales() {
	
	//teh price before sales
	String beforesales= driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[2]")).getText();
	String befosalessplit[]= beforesales.split("JOD");
	String beforesalestrim=befosalessplit[0].trim();
	String befosalessplitrep=beforesalestrim.replace(",", ".");
	
	double pricebeforesales=Double.parseDouble(befosalessplitrep);
	
	
	//the price of sales
	String discount=driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[1]")).getText();
			String discuontrep=discount.replace("%", "");
			String discounttrim=discuontrep.trim();
			double discountprice=Double.parseDouble(discounttrim);
			
		//the price after sales
			String aftersales=driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]")).getText();
			String aftersalessplit[]=aftersales.split("JOD");
			String aftersalestrim=aftersalessplit[0].trim();
			String aftersalesrep=aftersalestrim.replace(",", ".");
			
			double price_After_sales_actual=Double.parseDouble(aftersalesrep);
			
			System.out.println(price_After_sales_actual);
			
			double	discountpricefoel=discountprice/100;
		
		double thesales=pricebeforesales*discountpricefoel;
			
		double priceaftersales_expected=pricebeforesales-thesales;
		
		
		
		softassert.assertEquals(price_After_sales_actual, priceaftersales_expected);	
	softassert.assertAll();
	
}

}
