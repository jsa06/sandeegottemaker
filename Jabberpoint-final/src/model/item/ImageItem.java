package model.item;

import view.strategies.DrawStrategy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
    protected void drawContent(DrawStrategy strategy) {
        strategy.draw(this.itemStyle, bufferedImage);
    }
}
