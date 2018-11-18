package model.item;

import view.strategies.DrawStrategy;

/**
 * Created by ggo01
 * Basic textitem to show in a presentation.
 */
public class TextItem extends SlideItem {

    private String text;

    @Override
    public void setContent(String content) {
        this.text = content;
    }

    public String getContent() {
        return this.text;
    }

    @Override
    protected void drawContent(DrawStrategy strategy) {
        strategy.draw(this.itemStyle, text);
    }
}
