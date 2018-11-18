package view;

import view.strategies.DrawStrategy;

/**
 * Created by ggo01
 * Required interface for Presentation to set up the correct drawing strategy.
 */
public interface JabberDrawable {

    void draw(DrawStrategy strategy);
}
