package application;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;

import org.xml.sax.SAXException;

public class ParserDOM {
	public String[] parser(String city) {
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + city
				+ "&units=metric&mode=xml&APPID=23cb5cee802fd8779acf3a5d8277b43d";

		// Variables
		String cName = null;
		String tempValue = null;
		String rain = null;
		String weather = null;
		String[] arrParsed = new String[4];

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		try {
			Document document = builder.parse(new URL(url).openStream());
			Element rootElement = document.getDocumentElement();

			NodeList nodes = rootElement.getChildNodes();

			// Go through all child elements to root
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);

				if (node instanceof Element) {

					Element child = (Element) node;

					if (child.getNodeName() == "city") {
						cName = child.getAttribute("name");
						arrParsed[0] = cName;

					} else if (child.getNodeName() == "temperature") {
						tempValue = child.getAttribute("value");
						arrParsed[1] = tempValue + "°";

					} else if (child.getNodeName() == "precipitation") {
						rain = child.getAttribute("mode");
						arrParsed[2] = rain.substring(0, 1).toUpperCase() + rain.substring(1);

					} else if (child.getNodeName() == "weather") {
						weather = child.getAttribute("value");
						arrParsed[3] = weather.substring(0, 1).toUpperCase() + weather.substring(1);

					}
				}

			}
			// System.out.println(Arrays.toString(arrParsed));
			return arrParsed;

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			arrParsed[0] = "Not found";

			for (int i = 1; i <= 3; i++) {
				arrParsed[i] = "-";
			}
			return arrParsed;
		}
		return arrParsed;

	}
}
