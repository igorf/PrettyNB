package ind.igor.jtattoolf.tabcontrol.plaf;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import org.netbeans.swing.tabcontrol.TabDisplayer;
import org.netbeans.swing.tabcontrol.plaf.*;

/**
 * Tab displayer UI for JTattoo look and feel
 *
 * @author Tim Boudreau
 */
public final class JTattooEditorTabDisplayerUI extends BasicScrollingTabDisplayerUI {
    
    public static final Color BORDER_COLOR = new Color(162, 162, 162);
    
    private Rectangle scratch = new Rectangle();
    private static Map<Integer, String[]> buttonIconPaths;

    public JTattooEditorTabDisplayerUI(TabDisplayer displayer) {
        super(displayer);
    }

	@Override
    protected TabCellRenderer createDefaultRenderer() {
        return new JTattooEditorTabCellRenderer();
    }

    public static ComponentUI createUI(JComponent c) {
        return new JTattooEditorTabDisplayerUI((TabDisplayer) c);
    }

	@Override
    public Dimension getMinimumSize(JComponent c) {
        return new Dimension (80, 28);
    }
    
	@Override
    public Dimension getPreferredSize(JComponent c) {
        int prefHeight = 28;
        Graphics g = BasicScrollingTabDisplayerUI.getOffscreenGraphics();
        if (g != null) {
            FontMetrics fm = g.getFontMetrics(displayer.getFont());
            Insets ins = getTabAreaInsets();
            prefHeight = fm.getHeight() + ins.top + ins.bottom + 9;
        }
        return new Dimension(displayer.getWidth(), prefHeight);
    }

	@Override
    protected int createRepaintPolicy () {
        return TabState.REPAINT_ALL_TABS_ON_ACTIVATION_CHANGE
                | TabState.REPAINT_ALL_TABS_ON_SELECTION_CHANGE
                | TabState.REPAINT_ON_MOUSE_PRESSED
                | TabState.REPAINT_ON_CLOSE_BUTTON_PRESSED
                | TabState.REPAINT_ON_MOUSE_ENTER_CLOSE_BUTTON;
    }


	@Override
    public Insets getTabAreaInsets() {
        Insets results = super.getTabAreaInsets();
        results.bottom += 4;
        results.right += 3;
        return results;
    }

    @Override
    protected void paintBackground(Graphics g) {
        g.setColor(new Color(193, 193, 193));
        g.fillRect(0, 0, displayer.getWidth(), displayer.getHeight());
    }

	@Override
    public void install() {
        super.install();
        displayer.setBackground(UIManager.getColor("control")); //NOI18N
    }

	@Override
    protected void paintAfterTabs(Graphics g) {
        Rectangle r = new Rectangle();
        getTabsVisibleArea(r);
        r.width = displayer.getWidth();

		g.setColor(new Color(238,238,238));
        Insets ins = getTabAreaInsets();
        g.fillRect(r.x, r.y + r.height, r.x + r.width,
                   displayer.getHeight() - (r.y + r.height));
		g.setColor(BORDER_COLOR);
		g.drawLine (0, displayer.getHeight() - 1, displayer.getWidth(), displayer.getHeight() - 1);

        g.setColor(UIManager.getColor("controlHighlight")); //NOI18N

        int selEnd = 0;
        int i = selectionModel.getSelectedIndex();
        if (i != -1) {
            getTabRect(i, scratch);
            if (scratch.width != 0) {
                if (r.x < scratch.x) {
                    g.drawLine(r.x, displayer.getHeight() - ins.bottom, scratch.x - 1, displayer.getHeight() - ins.bottom);
                }
                if (scratch.x + scratch.width < r.x + r.width) {
                    selEnd = scratch.x + scratch.width;
                    g.drawLine(selEnd, displayer.getHeight() - ins.bottom, r.x + r.width, displayer.getHeight() - ins.bottom);
                }
            } else {
                g.drawLine (0, displayer.getHeight() - ins.bottom, displayer.getWidth(), displayer.getHeight() - ins.bottom);
            }
            g.setColor(new Color(162,162,162));
            g.drawLine(
					selEnd,
					displayer.getHeight() - 5,
					displayer.getWidth(),
                    displayer.getHeight() - 5
			);
        }
    }

    private static void initIcons() {
        if( null == buttonIconPaths ) {
            buttonIconPaths = new HashMap<Integer, String[]>(7);
            
            //left button
            String[] iconPaths = new String[4];
            iconPaths[TabControlButton.STATE_DEFAULT] = "org/netbeans/swing/tabcontrol/resources/mac_scrollleft_enabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_DISABLED] = "org/netbeans/swing/tabcontrol/resources/mac_scrollleft_disabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_ROLLOVER] = "org/netbeans/swing/tabcontrol/resources/mac_scrollleft_rollover.png"; // NOI18N
            iconPaths[TabControlButton.STATE_PRESSED] = "org/netbeans/swing/tabcontrol/resources/mac_scrollleft_pressed.png"; // NOI18N
            buttonIconPaths.put( TabControlButton.ID_SCROLL_LEFT_BUTTON, iconPaths );
            
            //right button
            iconPaths = new String[4];
            iconPaths[TabControlButton.STATE_DEFAULT] = "org/netbeans/swing/tabcontrol/resources/mac_scrollright_enabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_DISABLED] = "org/netbeans/swing/tabcontrol/resources/mac_scrollright_disabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_ROLLOVER] = "org/netbeans/swing/tabcontrol/resources/mac_scrollright_rollover.png"; // NOI18N
            iconPaths[TabControlButton.STATE_PRESSED] = "org/netbeans/swing/tabcontrol/resources/mac_scrollright_pressed.png"; // NOI18N
            buttonIconPaths.put( TabControlButton.ID_SCROLL_RIGHT_BUTTON, iconPaths );
            
            //drop down button
            iconPaths = new String[4];
            iconPaths[TabControlButton.STATE_DEFAULT] = "org/netbeans/swing/tabcontrol/resources/mac_popup_enabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_DISABLED] = "org/netbeans/swing/tabcontrol/resources/mac_popup_disabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_ROLLOVER] = "org/netbeans/swing/tabcontrol/resources/mac_popup_rollover.png"; // NOI18N
            iconPaths[TabControlButton.STATE_PRESSED] = "org/netbeans/swing/tabcontrol/resources/mac_popup_pressed.png"; // NOI18N
            buttonIconPaths.put( TabControlButton.ID_DROP_DOWN_BUTTON, iconPaths );
            
            //maximize/restore button
            iconPaths = new String[4];
            iconPaths[TabControlButton.STATE_DEFAULT] = "org/netbeans/swing/tabcontrol/resources/mac_maximize_enabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_DISABLED] = "org/netbeans/swing/tabcontrol/resources/mac_maximize_disabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_ROLLOVER] = "org/netbeans/swing/tabcontrol/resources/mac_maximize_rollover.png"; // NOI18N
            iconPaths[TabControlButton.STATE_PRESSED] = "org/netbeans/swing/tabcontrol/resources/mac_maximize_pressed.png"; // NOI18N
            buttonIconPaths.put( TabControlButton.ID_MAXIMIZE_BUTTON, iconPaths );
            
            iconPaths = new String[4];
            iconPaths[TabControlButton.STATE_DEFAULT] = "org/netbeans/swing/tabcontrol/resources/mac_restore_enabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_DISABLED] = "org/netbeans/swing/tabcontrol/resources/mac_restore_disabled.png"; // NOI18N
            iconPaths[TabControlButton.STATE_ROLLOVER] = "org/netbeans/swing/tabcontrol/resources/mac_restore_rollover.png"; // NOI18N
            iconPaths[TabControlButton.STATE_PRESSED] = "org/netbeans/swing/tabcontrol/resources/mac_restore_pressed.png"; // NOI18N
            buttonIconPaths.put( TabControlButton.ID_RESTORE_BUTTON, iconPaths );
        }
    }

	@Override
    public Icon getButtonIcon(int buttonId, int buttonState) {
        Icon res = null;
        initIcons();
        String[] paths = buttonIconPaths.get( buttonId );
        if( null != paths && buttonState >=0 && buttonState < paths.length ) {
            res = TabControlButtonFactory.getIcon( paths[buttonState] );
        }
        return res;
    }
    
	@Override
    protected Rectangle getControlButtonsRectangle( Container parent ) {
        Component c = getControlButtons();
        return new Rectangle( parent.getWidth()-c.getWidth()-3, 3, c.getWidth(), c.getHeight() );
    }
}
