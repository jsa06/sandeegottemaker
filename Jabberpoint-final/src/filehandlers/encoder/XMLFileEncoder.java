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
 *  XML File Encoder uses DocumentBuilder to generate the proper object and save it to a file.
 *  Filename is assumed to be without file extension.
 */
public class XMLFileEncoder extends FileEncoder {
    private Document dom;

    /**
     * Create an XML node that represents a slideitem. Since slides can have children these are looped through recursively.
     * However they are all added to the slide without any nesting in the XML files.
     * @param xmlslide xml slide
     * @param item item to process
     * @param level level of the item
     * @return Element node that represents the parent slide of the item.
     */
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
        if(!item.getChildren().isEmpty()) {
            for (SlideItem child : item.getChildren()) {
                xmlslide = addSlideItem(xmlslide, child, level + 1);
            }
        }
        return xmlslide;
    }

    /**
     * Create an XML node that represents a slide with all items in it.
     * @param slide slide element to add.
     * @return Element nod that represents a slide.
     */
    private Element addSlide(Slide slide) {
        Element xmlslide = dom.createElement(XMLFormat.SLIDE);
        xmlslide.setAttribute(XMLFormat.SLIDETITLE, slide.getTitle());

        if(!slide.getSlideItems().isEmpty()) {
            for (SlideItem item : slide.getSlideItems()) {
                xmlslide = addSlideItem(xmlslide, item, 1);
            }
        }
        return xmlslide;
    }

    /**
     * Write the file to a *.xml file.
     * @param filename filename without extension
     * @return True if successful, False is saving fails.
     */
    private Boolean writeFile(String filename) {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "jabberpoint.dtd");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            tr.transform(new DOMSource(dom),
                    new StreamResult(new FileOutputStream(filename + ".xml")));
            return true;
        } catch (TransformerException te) {
            System.out.println(te.getMessage());
            return false;
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            return false;
        }
    }

    /**
     * Primary save function. Creates a DocumentBuilder and sets the Root presentation element.
     * @param presentation Presentation to save.
     * @param filename filename without extension
     * @return True if successful, false if saving fails.
     */
    public Boolean saveFile(Presentation presentation, String filename) {
        Boolean success = true;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element presentationRoot = dom.createElement(XMLFormat.PRESENTATION);

            if (!presentation.getSlides().isEmpty()) {
                for (Slide slide : presentation.getSlides()) {
                    presentationRoot.appendChild(addSlide(slide));
                }
            }
            dom.appendChild(presentationRoot);
            success = writeFile(filename);

        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
            success = false;
        }
        return success;
    }
}
