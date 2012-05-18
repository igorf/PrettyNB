package ind.igor.jtattoolf.plaf;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicPanelUI;

public class JTattooToolbarBumpUI extends BasicPanelUI {

	@Override
	public void installUI(JComponent c) {
		c.setVisible(false);
	}

    @Override
    public void update(Graphics g, JComponent c) {
    }    
 
    @Override
    public void paint(Graphics g, JComponent c) {
    }
    
}
