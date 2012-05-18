package ind.igor.jtattoolf.plaf;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

public class JTattooPanelUI extends BasicPanelUI{
    private static JTattooPanelUI panelUI = null;
    private static JTattooNavigatorUI navUI = null;
    private static JTattooToolbarBumpUI tbBumpUI = null;
    private static JTattooToolbarRowUI tbRowUI = null;

    public static ComponentUI createUI(JComponent c) {
        Logger log = Logger.getLogger("PanelUI::createUI");
        log.log(Level.INFO, c.getClass().getName());
        
        if (c.getClass().getName().equals("org.netbeans.modules.html.editor.NavigationSideBar")) {
            return getNavigatorUI();
        }
        if (c.getClass().getName().equals("org.netbeans.core.windows.view.ui.toolbars.ToolbarContainer$ToolbarBump")) {
            return getToolbarBumpUI();
        }
        if (c.getClass().getName().equals("org.netbeans.core.windows.view.ui.toolbars.ToolbarRow")) {
            return getToolbarRowUI();
        }
        
        return getDefaultUI();
    }
    
    private static ComponentUI getNavigatorUI() {
        if (navUI == null) {
            navUI = new JTattooNavigatorUI();
        }
        return navUI;
    }

    private static ComponentUI getToolbarBumpUI() {
        if (tbBumpUI == null) {
            tbBumpUI = new JTattooToolbarBumpUI();
        }
        return tbBumpUI;
    }

	private static ComponentUI getToolbarRowUI() {
        if (tbRowUI == null) {
            tbRowUI = new JTattooToolbarRowUI();
        }
        return tbRowUI;
    }
    
    private static ComponentUI getDefaultUI() {
        if (panelUI == null) {
            panelUI = new JTattooPanelUI();
        }
        return panelUI;
    }
}
