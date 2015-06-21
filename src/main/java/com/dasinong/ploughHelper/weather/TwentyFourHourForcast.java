package com.dasinong.ploughHelper.weather;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 

import org.w3c.dom.Document; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList; 
import org.xml.sax.SAXException; 

/*
<hourly source="forecast" starttime="2015-06-20T08:00:00">
<title type="text">Weather Central Dataserver API</title>
<rights>Copyright (c) 2012, Weather Central LP</rights>
<location areaid="101291605">
  <step time="2015-06-20 08:00:00.0">
    <Temperature unit="℃">17.3</Temperature>
    <RelativeHumidity unit="">94</RelativeHumidity>
    <WindDirection_10m unit="Degrees" summary="WSW">253</WindDirection_10m>
    <WindSpeed_10m unit="mps">2.0</WindSpeed_10m>
    <AccumRainTotal unit="mm">0.0</AccumRainTotal>
    <AccumSnowTotal unit="mm">0.0</AccumSnowTotal>
    <AccumIceTotal unit="mm">0.0</AccumIceTotal>
    <POP unit="">10</POP>
    <Icon>cloudy</Icon>
  </step>
*/

public class TwentyFourHourForcast {
	int code;
	Date startTime;
	
	class ForcastDInfo{
		Date time;
		double temperature;   //℃
		int relativeHumidity;
        int windDirection_10m;  //Degrees
	    double windSpeed_10m;  //mps
        double accumRainTotal;   //mm
	    double accumSnowTotal;   //mm
	    double accumIceTotal;    //mm
	    int pOP;                 //Probability of precipitation
	    String icon;
		public ForcastDInfo(Date time, double temperature,
				int relativeHumidity, int windDirection_10m, double windSpeed_10m,
				double accumRainTotal, double accumSnowTotal,
				double accumIceTotal, int pOP, String icon) {
			super();
			this.time = time;
			this.temperature = temperature;
			this.relativeHumidity = relativeHumidity;
			this.windDirection_10m = windDirection_10m;
			this.windSpeed_10m = windSpeed_10m;
			this.accumRainTotal = accumRainTotal;
			this.accumSnowTotal = accumSnowTotal;
			this.accumIceTotal = accumIceTotal;
			this.pOP = pOP;
			this.icon = icon;
		}
	}
	
	public ForcastDInfo[] info = new ForcastDInfo[25];
	
	public TwentyFourHourForcast(String fileName, int code) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		Document document = db.parse(fileName); 
		Node root = document.getFirstChild();
		String startTime = root.getAttributes().getNamedItem("starttime").getNodeValue();
		startTime = startTime.replace('T', ' ');
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    
	    try {
			this.startTime = sdf.parse(startTime);
		} catch (ParseException e) {
			System.out.println("Inproper start time while parsing twentyFourForcast for "+code);
		}
	    this.code=code;
	    
		NodeList forcastHs = document.getElementsByTagName("step");
        
		for (int i = 0; i < forcastHs.getLength(); i++) { 
			try{
				Node forcastH = forcastHs.item(i); 
				String timec = forcastH.getAttributes().getNamedItem("time").getNodeValue();
				Date time = sdf.parse(timec);
				NodeList attributes = forcastH.getChildNodes();
				if (attributes.getLength()==19){
					double temperature = Double.parseDouble(attributes.item(1).getTextContent());
					int relativeHumidity = Integer.parseInt(attributes.item(3).getTextContent());
					int windDirection_10m = Integer.parseInt(attributes.item(5).getTextContent());
					double windSpeed_10m = Double.parseDouble(attributes.item(7).getTextContent());
					double accumRainTotal = Double.parseDouble(attributes.item(9).getTextContent());
					double accumSnowTotal = Double.parseDouble(attributes.item(11).getTextContent());
					double accumIceTotal = Double.parseDouble(attributes.item(13).getTextContent());
					int pOP = Integer.parseInt(attributes.item(15).getTextContent());
					String icon = attributes.item(17).getTextContent();
					ForcastDInfo fdi = new ForcastDInfo(time, temperature, relativeHumidity, windDirection_10m, windSpeed_10m, 
							accumRainTotal, accumSnowTotal, accumIceTotal, pOP, icon);
					info[i] = fdi;
				}
			}catch (Exception e){
				System.out.println("Exception happend while parsing twentyFourForcast for "+code);
			}
		} 
	} 
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ParseException{
		String filename  = "E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/current/101291605";
		TwentyFourHourForcast tfhf = new TwentyFourHourForcast(filename,101291605);
		filename  = "E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/current/101291601";
		tfhf = new TwentyFourHourForcast(filename,101291601);
	}
}
