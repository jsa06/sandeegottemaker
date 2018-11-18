package factories;

import model.ItemStyle;
import model.item.ImageItem;
import model.item.SlideItem;
import model.item.TextItem;

/**
 * Created by ggo01
 * Generate correct slide item type based on given type.
 * Currently only Image and Text are supported.
 */
public class SlideItemFactory {

    private static SlideItemFactory instance;

    private ItemStylePool itemStylePool;

    public static SlideItemFactory getInstance() {
        if (instance == null) {
            instance = new SlideItemFactory();
        }
        return instance;
    }

    private SlideItemFactory() {
        this.itemStylePool = ItemStylePool.getInstance();
    }

    /**
     * Generate the correct slide item at the correct level.
     * Style is also linked to the item on creation.
     * @param level
     * @param type
     * @return Complete SlideItem.
     */
    public SlideItem createSlideItem(int level, String type) {
        ItemStyle itemStyle = itemStylePool.getItemStyle(level);
        SlideItem slideItem;
        if (SlideItem.TEXT.equals(type)) {
            slideItem = new TextItem();
        } else {
            slideItem = new ImageItem();
        }

        slideItem.setLevel(level);
        slideItem.setItemStyle(itemStyle);
        return slideItem;
    }
}
