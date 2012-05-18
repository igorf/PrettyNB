package ind.igor.jtattoolf.border;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.UIManager;
import javax.swing.border.AbstractBorder;

public class TopEditorToolbarBorder extends AbstractBorder {
    private static final Insets insets = new Insets(0, 5, 1, 0);

	@Override
    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        g.setColor(UIManager.getColor("InternalFrame.borderDarkShadow"));
        g.drawLine(x, y + h - 1, x + w, y + h - 1);
    }

	@Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }
}