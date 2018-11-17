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

import filehandlers.format.XMLFormat;

/**
 * Created by ggo01
 * XML File Parses makes use of DocumentBuilderFactory to parse the loaded file.
 * Files are assumed to have a fixed structure of presentation -> slide -> item.
 */
public class XMLFileParser extends FileParser {

    /**
     * load an individual slide and return it to add to the presentation
     * @param xmlSlide Current slide item that needs to be created.
     * @return A slide complete with associated slide items
     */
    private Slide loadSlide(Element xmlSlide) {
        Slide slide = slideFactory.createSlide();
        SlideItem titleItem = slideItemFactory.createSlideItem(0, SlideItem.TEXT);
        titleItem.setContent(getTitle(xmlSlide, XMLFormat.SLIDETITLE));
        slide.setTitle(titleItem);

        String transitionString = xmlSlide.getAttribute(XMLFormat.TRANSITION);
        boolean transition = Boolean.parseBoolean(transitionString);
        slide.setTransitionEnabled(transition);

        if(xmlSlide.getElementsByTagName(XMLFormat.ITEM).getLength()>0) {
            NodeList slideItems = xmlSlide.getElementsByTagName(XMLFormat.ITEM);

            for (int itemNumber = 0; itemNumber < slideItems.getLength(); itemNumber++) {
                Element item = (Element) slideItems.item(itemNumber);
                slide.addItem(loadSlideItem(item));
            }
        }
        return slide;
    }

    /**
     * Primary file parsing command. Opens de file and reads all the nodes spawning the required slides and items.
     * @param filename full file address to open
     * @return Presentation object completely loaded with all the data.
     */
    @Override
    public Presentation parseFile(String filename) {
        Presentation presentation = presentationFactory.createPresentation();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Element doc = document.getDocumentElement();

            presentation.setTitle(this.getTitle(doc, XMLFormat.SHOWTITLE));

            if(doc.getElementsByTagName(XMLFormat.SLIDE).getLength() > 0) {
                NodeList slides = doc.getElementsByTagName(XMLFormat.SLIDE);
                for (int slideNumber = 0; slideNumber < slides.getLength(); slideNumber++) {
                    presentation.addSlide(loadSlide((Element) slides.item(slideNumber)));
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

    /**
     * Create a slide item to add to a slide.
     * @param item node of the item to process.
     * @return A SlideItem.
     */
    private SlideItem loadSlideItem(Element item) {
        int level = 1; // default
        NamedNodeMap attributes = item.getAttributes();
        String levelText = attributes.getNamedItem(XMLFormat.LEVEL).getTextContent();
        if (levelText != null) {
            try {
                level = Integer.parseInt(levelText);
            }
            catch(NumberFormatException nfe) {
                nfe.printStackTrace();
            }
        }
        String type = attributes.getNamedItem(XMLFormat.KIND).getTextContent();
        SlideItem slideItem = slideItemFactory.createSlideItem(level, type);
        slideItem.setContent(item.getTextContent());

        return slideItem;
    }

    /**
     * Helper method to obtain the title of a slide.
     * @param element element to get the title from
     * @param tagName Tagname to read out.
     * @return String of the slide title.
     */
    private String getTitle(Element element, String tagName) {
        NodeList titles = element.getElementsByTagName(tagName);
        return titles.item(0).getTextContent();
    }
}
