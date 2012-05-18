package ind.igor.jtattoolf.plaf;

import ind.igor.jtattoolf.border.MainToolbarRowBorder;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.PanelUI;

public class JTattooToolbarRowUI extends PanelUI {

	private static MainToolbarRowBorder tbBorder = null;

    public JTattooToolbarRowUI() {
    }

	@Override
	public void installUI(JComponent c) {
        super.installUI(c);
		c.setBorder(getRowBorder());
    }

	private Border getRowBorder() {
		if (tbBorder == null) {
			tbBorder = new MainToolbarRowBorder();
		}
		return tbBorder;
	}

}
