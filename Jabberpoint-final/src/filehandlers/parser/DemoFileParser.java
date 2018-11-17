package filehandlers.parser;

import model.Presentation;
import model.Slide;
import model.item.SlideItem;

/**
 * Created by ggo01
 */
public class DemoFileParser extends FileParser {

    @Override
    public Presentation parseFile(String unusedFilename) {
        Presentation presentation = presentationFactory.createPresentation();
        presentation.setTitle("Demo Presentation");

        Slide slide = slideFactory.createSlide();
        slide.setTitle(createSlideItem(1, SlideItem.TEXT,"JabberPoint"));
        slide.addItem(createSlideItem(1, SlideItem.TEXT, "Het Java Presentatie Tool"));
        slide.addItem(createSlideItem(2, SlideItem.TEXT, "Copyright (c) 1996-2000: Ian Darwin"));
        slide.addItem(createSlideItem(2, SlideItem.TEXT, "Copyright (c) 2000-now:"));
        slide.addItem(createSlideItem(2, SlideItem.TEXT, "Gert Florijn en Sylvia Stuurman"));
        slide.addItem(createSlideItem(4, SlideItem.TEXT, "JabberPoint aanroepen zonder bestandsnaam"));
        slide.addItem(createSlideItem(4, SlideItem.TEXT, "laat deze presentatie zien"));
        slide.addItem(createSlideItem(1, SlideItem.TEXT, "Navigeren:"));
        slide.addItem(createSlideItem(3, SlideItem.TEXT, "Volgende slide: PgDn of Enter"));
        slide.addItem(createSlideItem(3, SlideItem.TEXT, "Vorige slide: PgUp of up-arrow"));
        slide.addItem(createSlideItem(3, SlideItem.TEXT, "Stoppen: q or Q"));
        presentation.addSlide(slide);

        slide = slideFactory.createSlide();
        slide.setTitle(createSlideItem(1, SlideItem.TEXT, "Demonstratie van levels en stijlen"));
        slide.addItem(createSlideItem(1, SlideItem.TEXT, "Level 1"));
        slide.addItem(createSlideItem(2, SlideItem.TEXT, "Level 2"));
        slide.addItem(createSlideItem(1, SlideItem.TEXT, "Nogmaals level 1"));
        slide.addItem(createSlideItem(1, SlideItem.TEXT, "Level 1 heeft stijl nummer 1"));
        slide.addItem(createSlideItem(2, SlideItem.TEXT, "Level 2 heeft stijl nummer 2"));
        slide.addItem(createSlideItem(3, SlideItem.TEXT, "Zo ziet level 3 er uit"));
        slide.addItem(createSlideItem(4, SlideItem.TEXT, "En dit is level 4"));
        presentation.addSlide(slide);

        slide = slideFactory.createSlide();
        slide.setTitle(createSlideItem(1, SlideItem.TEXT,"De derde slide"));
        slide.addItem(createSlideItem(1, SlideItem.TEXT,  "Om een nieuwe presentatie te openen,"));
        slide.addItem(createSlideItem(2, SlideItem.TEXT,  "gebruik File->Open uit het menu."));
        slide.addItem(createSlideItem(1, SlideItem.TEXT,  " "));
        slide.addItem(createSlideItem(1, SlideItem.TEXT,  "Dit is het einde van de presentatie."));
        slide.addItem(createSlideItem(1, SlideItem.IMAGE, "JabberPoint.jpg"));
        presentation.addSlide(slide);

        return presentation;
    }

    private SlideItem createSlideItem(int level, String type, String content) {
        SlideItem slideItem = slideItemFactory.createSlideItem(level, type);
        slideItem.setContent(content);

        return slideItem;
    }
}
