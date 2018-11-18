package factories;

import model.ItemStyle;

import java.awt.*;

/**
 * Created by ggo01
 * The program has 5 fixed Styles which was loaded into memory once.
 * This singleton allows access to these styles based on the level set in an item.
 */
public class ItemStylePool {

    private static ItemStylePool instance;

    private ItemStyle[] itemStyles;

    public static ItemStylePool getInstance() {
        if (instance == null) {
            instance = new ItemStylePool();
        }
        return instance;
    }

    private ItemStylePool() {
        itemStyles = new ItemStyle[5];
        // De styles zijn vast ingecodeerd.
        itemStyles[0] = new ItemStyle(0, Color.red,   48, 20);	    // style voor item-level 0
        itemStyles[1] = new ItemStyle(20, Color.blue,  40, 10);	// style voor item-level 1
        itemStyles[2] = new ItemStyle(50, Color.black, 36, 10);	// style voor item-level 2
        itemStyles[3] = new ItemStyle(70, Color.black, 30, 10);	// style voor item-level 3
        itemStyles[4] = new ItemStyle(90, Color.black, 24, 10);	// style voor item-level 4
    }

    public ItemStyle getItemStyle(int level) {
        if (level >= itemStyles.length) {
            level = itemStyles.length -1;
        }

        return itemStyles[level];
    }
}
