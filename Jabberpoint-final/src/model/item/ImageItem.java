package model.item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ggo01
 */
public class ImageItem extends SlideItem {

    private BufferedImage bufferedImage;
    private String fileName;

    @Override
    public String getContent() {
        return fileName;
    }

    @Override
    public void setContent(String content) {
        fileName = content;
        try {
            bufferedImage = ImageIO.read(new File(content));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int drawContent(Graphics g, Rectangle area) {
        float scale = getScale(area);
        int width = area.x + (int) (this.itemStyle.getIndentation() * scale);
        int height = area.y + (int) (this.itemStyle.getLeading() * scale);
        g.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(null) * scale),
                (int) (bufferedImage.getHeight(null) * scale), null);

        return (int) (this.itemStyle.getLeading() * scale) + (int) (bufferedImage.getHeight(null) * scale);
    }
}
