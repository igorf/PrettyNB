package ind.igor.jtattoolf;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 *
 * @author  David Simonek
 */
class EditorToolbarBorder extends AbstractBorder {
    private static final Insets insets = new Insets(0, 5, 0, 0);

    public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
        g.setColor(UIManager.getColor("InternalFrame.borderDarkShadow")); //NOI18N
        g.drawLine(x, y + h - 1, x + w - 1, y + h - 1);
    }

    public Insets getBorderInsets(Component c) {
        return insets;
    }
}