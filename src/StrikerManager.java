//import org.apache.xerces.impl.xs.identity.Selector.Matcher;
import org.openqa.selenium.*;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.datatransfer.*;
import java.util.regex.*;

public class StrikerManager{
    public static void main(String[] args) throws Exception {
    	//To get the sorted order of players in div6 based on their goals
    	String username="";
    	String password="";
    	WebDriver driver = new FirefoxDriver();
    	String header="http://en.strikermanager.com/inicio.php?accion=/estadisticas.php%3Fid_liga%3D5";//+1376"
    	driver.get("http://en.strikermanager.com");
    	driver.findElements(By.xpath("//input[@name='alias']")).get(0).sendKeys(username);
    	driver.findElements(By.xpath("//input[@name='pass']")).get(0).sendKeys(password);
    	driver.findElement(By.xpath("//input[@class='boton']")).click();    	
    	CSVWriter f=new CSVWriter(new FileWriter("results/output.csv"),'\t');
    	int id=1376,i;
    	List<Player> players= new ArrayList<Player>();
    	for(i=0;i<256;i++,id+=2){ //269 is the number of groups in div6//256 in div5
    		String url=header+Integer.toString(id);
    		driver.get(url);
    		Thread.sleep(2000);
    		driver.switchTo().frame("marco");    		
    		WebElement elem=driver.findElements(By.xpath("//tbody")).get(0);
    		WebElement ele=elem.findElements(By.xpath(".//tr[@class='tipo2']")).get(0);
    		Player p=new Player();
    		p.name=ele.findElement(By.xpath(".//td[@class='equipo']")).getText();
    		p.playerUrl=ele.findElement(By.xpath(".//td[@class='equipo']/a")).getAttribute("href");
    		p.teamName=ele.findElement(By.xpath(".//td[@class='equipo2']")).getText();
    		p.teamUrl=ele.findElement(By.xpath(".//td[@class='equipo2']/a")).getAttribute("href");
    		p.goals=Integer.valueOf(ele.findElement(By.xpath(".//td[@class='last numerico rojo']")).getText());
    		players.add(p);
    		System.out.println(p.name+" "+p.teamName+" "+p.goals);
    		System.out.println(p.playerUrl); System.out.println(p.teamUrl);
    		ele=elem.findElements(By.xpath(".//tr[@class='tipo1']")).get(0);
    		Player p2=new Player();    		
    		p2.name=ele.findElement(By.xpath(".//td[@class='equipo']")).getText();
    		p2.playerUrl=ele.findElement(By.xpath(".//td[@class='equipo']/a")).getAttribute("href");
    		p2.teamName=ele.findElement(By.xpath(".//td[@class='equipo2']")).getText();
    		p2.teamUrl=ele.findElement(By.xpath(".//td[@class='equipo2']/a")).getAttribute("href");
    		p2.goals=Integer.valueOf(ele.findElement(By.xpath(".//td[@class='last numerico rojo']")).getText());
    		players.add(p2);
    		System.out.println(p2.name+" "+p2.teamName+" "+p2.goals);
    		System.out.println(p2.playerUrl); System.out.println(p2.teamUrl);
    		
    	}
    	Collections.sort(players);
    	for(i=0;i<players.size();i++){
    		String [] a= new String[5];
    		a[0]=Integer.toString(players.get(i).goals);
    		a[1]=players.get(i).name;
    		a[2]=players.get(i).teamName;
    		a[3]=players.get(i).playerUrl;
    		f.writeNext(a);
    	}
    	f.close();
     }
}