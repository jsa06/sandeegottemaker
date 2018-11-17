package filehandlers.encoder;

import model.Presentation;

import filehandlers.format.XMLFormat;
import model.Slide;
import model.item.ImageItem;
import model.item.SlideItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *  created by: jsa06
 */
public class XMLFileEncoder extends FileEncoder {
    private Document dom;

    private Element addSlideItem(Element xmlslide, SlideItem item, int level) {
        Element xmlitem = dom.createElement(XMLFormat.ITEM);
        if(item instanceof ImageItem) {
            xmlitem.setAttribute(XMLFormat.KIND, XMLFormat.IMAGE);
        } else {
            xmlitem.setAttribute(XMLFormat.KIND, XMLFormat.TEXT);
        }
        xmlitem.setAttribute(XMLFormat.LEVEL, String.valueOf(level));
        xmlitem.appendChild(dom.createTextNode(item.getContent()));
        xmlslide.appendChild(xmlitem);
        if(item.getChildren().isEmpty() == false) {
            for (SlideItem child : item.getChildren()) {
                xmlslide = addSlideItem(xmlslide, child, level + 1);
            }
        }
        return xmlslide;
    }

    private Element addSlide(Slide slide) {
        Element xmlslide = dom.createElement(XMLFormat.SLIDE);
        xmlslide.setAttribute(XMLFormat.SLIDETITLE, slide.getTitle());

        if(slide.getSlideItems().isEmpty() == false) {
            for (SlideItem item : slide.getSlideItems()) {
                xmlslide = addSlideItem(xmlslide, item, 1);
            }
        }
        return xmlslide;
    }

    public Boolean saveFile(Presentation presentation, String filename) {
        Boolean success = true;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element presentationRoot = dom.createElement(XMLFormat.PRESENTATION);

            if (presentation.getSlides().isEmpty() == false) {
                for (Slide slide : presentation.getSlides()) {
                    presentationRoot.appendChild(addSlide(slide));
                }
            }
            dom.appendChild(presentationRoot);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "jabberpoint.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(filename)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
                success = false;
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
                success = false;
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
            success = false;
        }
        return success;
    }
}
