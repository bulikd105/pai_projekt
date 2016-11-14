package pai_projekt;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLoperations 
{

	public String readXML(String str)
	{
		String answer = null;
		try 
		{
			File fXmlFile = new File("ServerConfiguration.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile); 
			
			doc.getDocumentElement().normalize(); 
			NodeList nList = doc.getElementsByTagName("staff");

			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode; 
					answer = eElement.getElementsByTagName(str).item(0).getTextContent();
					System.out.println(str + ": " + eElement.getElementsByTagName(str).item(0).getTextContent());					
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return answer;
	}
}
