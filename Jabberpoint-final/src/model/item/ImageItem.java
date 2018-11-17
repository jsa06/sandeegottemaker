package model.item;

import view.JabberPointComponent;
import view.JabberPointFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Created by ggo01
 */
public class ImageItem extends SlideItem {

    private BufferedImage bufferedImage;

    @Override
    public String getContent() {
        return bufferedImage.toString();
    }

    @Override
    public void setContent(String content) {
        try {
            bufferedImage = ImageIO.read(new File(content));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void drawContent(Graphics g, Rectangle area) {
        float scale = getScale(area);
        int width = area.x + (int) (this.itemStyle.getIndentation() * scale);
        int height = area.y + (int) (this.itemStyle.getLeading() * scale);
        g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(null) * scale),
                (int) (bufferedImage.getHeight(null) * scale), null);
    }

    @Override
    protected int getHeight(Graphics g, Rectangle area) {
        return 20; //TODO berekenen
    }
}
