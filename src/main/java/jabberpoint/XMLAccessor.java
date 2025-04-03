package jabberpoint;

import jabberpoint.creators.SlideItemCreator;
import jabberpoint.presentationComponents.Presentation;
import jabberpoint.presentationComponents.Slide;
import jabberpoint.presentationComponents.slideItems.SlideItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * XMLAccessor, reads and writes XML files
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class XMLAccessor extends Accessor {
    /**
     * namen van xml tags of attributen
     */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";

    /**
     * tekst van messages
     */
    protected static final String PCE = "Parser Configuration Exception";

    public void loadFile(Presentation presentation, String filename) throws IOException {
        int slideNumber, itemNumber, max = 0, maxItems = 0;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); // Create a JDOM document
            Element doc = document.getDocumentElement();
            presentation.setTitle(getTitle(doc, SHOWTITLE));

            NodeList slides = doc.getElementsByTagName(SLIDE);
            max = slides.getLength();
            for (slideNumber = 0; slideNumber < max; slideNumber++) {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = new Slide();
                slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
                presentation.append(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
                maxItems = slideItems.getLength();
                for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
                    Element item = (Element) slideItems.item(itemNumber);
                    loadSlideItem(slide, item);
                }
            }
        } catch (IOException iox) {
            System.err.println(iox.toString());
        } catch (SAXException sax) {
            System.err.println(sax.getMessage());
        } catch (ParserConfigurationException pcx) {
            System.err.println(PCE);
        }
    }

    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();

    }

    private void loadSlideItem(Slide slide, Element item) {
        SlideItemCreator.createSlideItem(slide, item);
    }

    public void saveFile(Presentation presentation, String filename) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        out.println("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
        out.println("<presentation>");
        out.print("<showtitle>");
        out.print(presentation.getTitle());
        out.println("</showtitle>");
        for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
            Slide slide = presentation.getSlide(slideNumber);
            out.println("<slide>");
            out.println("<title>" + slide.getTitle() + "</title>");
            for (SlideItem slideItem : slide.getSlideItems()) {
                out.println(slideItem.getSaveString());
            }
            out.println("</slide>");
        }
        out.println("</presentation>");
        out.close();
    }
}
