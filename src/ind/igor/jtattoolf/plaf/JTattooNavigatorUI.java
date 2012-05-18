package ind.igor.jtattoolf.plaf;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicPanelUI;

public class JTattooNavigatorUI extends BasicPanelUI{
    @Override
    public void update(Graphics g, JComponent c) {
        super.update(g, c);
        if (c.getBackground() instanceof ColorUIResource) {
            if (c.isOpaque()) {
                g.setColor(UIManager.getColor("jtattoo.htmlNavigatorColor"));
                g.fillRect(0, 0, c.getWidth(), c.getHeight() - 1);
                g.setColor(UIManager.getColor("jtattoo.htmlNavigatorBorderColor"));
                g.drawLine(0, c.getHeight() - 1, c.getWidth(), c.getHeight() - 1);
            }
        }
    }    
}
