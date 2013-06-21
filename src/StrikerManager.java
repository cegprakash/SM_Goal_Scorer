//import org.apache.xerces.impl.xs.identity.Selector.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import java.util.regex.*;

public class StrikerManager{
    public static void main(String[] args) throws Exception {
    	//To get the sorted order of players in div6 based on their goals
    	String username="";
    	String password="";
    	WebDriver driver = new FirefoxDriver();
    	String url="en.strikermanager.com/inicio.php?accion=/estadisticas.php%3Fid_liga%3D5";//+1888"
    	driver.get("http://en.strikermanager.com");
    	driver.findElements(By.xpath("//input[@name='alias']")).get(0).sendKeys(username);
    	driver.findElements(By.xpath("//input[@name='pass']")).get(0).sendKeys(password);
    	driver.findElement(By.xpath("//input[@class='boton']")).click();    	
    	
    	int id=1888,i;
    	List<Player> players= new ArrayList<Player>();
    	for(i=0;i<269;i++,id+=2){ //269 is the number of groups in div6
    		url=url+Integer.toString(id);
    		driver.get(url);
    		System.out.println(driver.getPageSource());
    		WebElement ele=driver.findElements(By.xpath(".//tr[@class='tipo2']")).get(0);
    		Player p=new Player();
    		p.name=ele.findElement(By.xpath("//td[@class='equipo']")).getText();
    		p.playerUrl=ele.findElement(By.xpath("//td[@class='equipo']")).getAttribute("href");
    		p.teamName=ele.findElement(By.xpath("//td[@class='equipo2']")).getText();
    		p.teamUrl=ele.findElement(By.xpath("//td[@class='equipo2']")).getAttribute("href");
    		p.goals=Integer.valueOf(ele.findElement(By.xpath("//td[@class='last numerico rojo']")).getText());
    		players.add(p);
    		System.out.println(p.name+" "+p.teamName+" "+p.goals);
    		p=new Player();
    		ele=driver.findElements(By.xpath(".//tr[@class='tipo1']")).get(0);
    		p.name=ele.findElement(By.xpath("//td[@class='equipo']")).getText();
    		p.playerUrl=ele.findElement(By.xpath("//td[@class='equipo']")).getAttribute("href");
    		p.teamName=ele.findElement(By.xpath("//td[@class='equipo2']")).getText();
    		p.teamUrl=ele.findElement(By.xpath("//td[@class='equipo2']")).getAttribute("href");
    		p.goals=Integer.valueOf(ele.findElement(By.xpath("//td[@class='last numerico rojo']")).getText());
    		players.add(p);
    		System.out.println(p.name+" "+p.teamName+" "+p.goals);

    		
    	}
     }
}