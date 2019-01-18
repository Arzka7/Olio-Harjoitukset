package com.example.app;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MoviesXMLReader {

    public TheaterList readTheatreAreasXML(TheaterList theaters) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlstring = "https://www.finnkino.fi/xml/TheatreAreas/";
            Document doc = builder.parse(urlstring);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Theater theater = new Theater(element.getElementsByTagName("ID").item(0).getTextContent(),element.getElementsByTagName("Name").item(0).getTextContent());
                    theaters.addTheater(theater);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("########THEATERS_DONE########");
        }
        return theaters;
    }

    public String readMoviesXML(String url, String startTime, String endTime, String movieName) {
        String data="", name;
        String[] tokens;
        String[] tokensStart;
        String[] tokensEnd;
        int lowLimit, highLimit, StartTime;
        try  {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    tokens = element.getElementsByTagName("dttmShowStart").item(0).getTextContent().split(Pattern.quote("T"));
                    tokens = tokens[1].split(Pattern.quote(":"));
                    StartTime = Integer.parseInt(tokens[0] + tokens[1]);

                    tokensStart = startTime.split(Pattern.quote(":"));
                    lowLimit = Integer.parseInt(tokensStart[0] + tokensStart[1]);

                    tokensEnd = endTime.split(Pattern.quote(":"));
                    highLimit = Integer.parseInt(tokensEnd[0] + tokensEnd[1]);

                    name = element.getElementsByTagName("Title").item(0).getTextContent();

                    if(movieName.equals("")) {
                        if ((lowLimit <= StartTime) && (StartTime <= highLimit)) {
                            data = data + element.getElementsByTagName("Theatre").item(0).getTextContent() + ": " + name + " -- " + tokens[0] + ":" + tokens[1] + " klo" + "\n" + "\n";
                        }
                    } else if (movieName.equals(name)) {
                        if ((lowLimit <= StartTime) && (StartTime <= highLimit)) {
                            data = data + element.getElementsByTagName("Theatre").item(0).getTextContent() + ": " + name + " -- " + tokens[0] + ":" + tokens[1] + " klo" + "\n" + "\n";
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            System.out.println("########MOVIES_DONE########");
        }
        return data;
    }

}
