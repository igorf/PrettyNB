package ind.igor.jtattoolf.border;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;

public class MainToolbarRowBorder extends AbstractBorder {
    private static final Insets insets = new Insets(1, 0, 1, 0);

	@Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        g.setColor(UIManager.getColor("jtattoo.borderColor"));
        g.drawLine(x, y + h - 1, x + w, y + h - 1);
        g.drawLine(x, y, x + w, y);
    }

	@Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }
}