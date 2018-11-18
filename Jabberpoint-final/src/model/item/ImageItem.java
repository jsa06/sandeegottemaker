package model.item;

import view.strategies.DrawStrategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by ggo01
 * Image Items use the filename to load the image into a Buffered Image.
 * The image file name can still be returned for saving purposes.
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
    protected void drawContent(DrawStrategy strategy) {
        strategy.draw(this.itemStyle, bufferedImage);
    }
}
