package filehandlers.parser;

import model.Presentation;
import model.Slide;
import model.item.SlideItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by ggo01
 */
public class XMLFileParser extends FileParser {

    /** names of xml tags or attributes */
    private static final String SHOWTITLE = "showtitle";
    private static final String SLIDETITLE = "title";
    private static final String SLIDE = "slide";
    private static final String ITEM = "item";
    private static final String LEVEL = "level";
    private static final String KIND = "kind";
    private static final String TRANSITION = "transition";

    @Override
    public Presentation parseFile(String filename) {
        Presentation presentation = presentationFactory.createPresentation();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename)); // maak een JDOM document
            Element doc = document.getDocumentElement();

            presentation.setTitle(this.getTitle(doc, SHOWTITLE));

            NodeList slides = doc.getElementsByTagName(SLIDE);
            for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
                Element xmlSlide = (Element) slides.item(slideNumber);
                Slide slide = slideFactory.createSlide();
                SlideItem titleItem = slideItemFactory.createSlideItem(0, SlideItem.TEXT);
                titleItem.setContent(getTitle(xmlSlide, SLIDETITLE));
                slide.setTitle(titleItem);

                String transitionString = xmlSlide.getAttribute(TRANSITION);
                boolean transition = Boolean.parseBoolean(transitionString);
                slide.setTransitionEnabled(transition);

                presentation.addSlide(slide);

                NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);

                for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
                    Element item = (Element) slideItems.item(itemNumber);
                    slide.addItem(loadSlideItem(item));
                }
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException saxe) {
            saxe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return presentation;
    }

    private SlideItem loadSlideItem(Element item) {
        int level = 1; // default
        NamedNodeMap attributes = item.getAttributes();
        String levelText = attributes.getNamedItem(LEVEL).getTextContent();
        if (levelText != null) {
            try {
                level = Integer.parseInt(levelText);
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        String type = attributes.getNamedItem(KIND).getTextContent();
        SlideItem slideItem = slideItemFactory.createSlideItem(level, type);
        slideItem.setContent(item.getTextContent());

        return slideItem;
    }

    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }
}
