package ind.igor.jtattoolf;


import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import org.netbeans.swing.plaf.LFCustoms;

public class JTattoLFCustoms extends LFCustoms {

    @Override
    public Object[] createLookAndFeelCustomizationKeysAndValues() {
	Object[] result;
		int uiFontSize = 12;
		Font controlFont = new Font("Sans", Font.PLAIN, uiFontSize);
		result = new Object[] {
			"Button.font", controlFont,
			"Tree.font", controlFont,
			"ToggleButton.font", controlFont,
			"Menu.font", controlFont,
			"MenuBar.font", controlFont,
			"MenuItem.font", controlFont,
			"CheckBoxMenuItem.font", controlFont,
			"RadioButtonMenuItem.font", controlFont,
			"PopupMenu.font", controlFont,
			"List.font", controlFont,
			"Label.font", controlFont,
			"ComboBox.font", controlFont,
		};
        return result;
    }

	@Override
	public Object[] createApplicationSpecificKeysAndValues() {
        Color borderColor = new Color(162, 162, 162);
		Object[] result = {
			/*
            "NbTabControl.focusedTabBackground", new Color(135,189,255),
            "NbTabControl.selectedTabBrighterBackground", new Color(252,252,252),
            "NbTabControl.selectedTabDarkerBackground", new Color(226,226,226),
            "NbTabControl.mouseoverTabBrighterBackground", new Color(194,194,194),
            "NbTabControl.mouseoverTabDarkerBackground", new Color(156,156,156),
            "NbTabControl.inactiveTabBrighterBackground", new Color(220,220,200),
            "NbTabControl.inactiveTabDarkerBackground", new Color(178,178,178),
			*/
			/*
            "NbTabControl.borderColor", new Color(162, 162, 162),
            "NbTabControl.borderShadowColor", new Color(178,178,178),
            "NbTabControl.borderDarkShadowColor", new Color(121,121,121),
            "NbTabControl.editorBorderShadowColor", new Color(121,121,121),
            "NbTabControl.editorTabBackground", new Color(101,101,101),
			*
			*/
             
			TAB_ACTIVE_SELECTION_FOREGROUND, Color.BLACK,
			TAB_SELECTION_FOREGROUND, new Color(80, 80, 200),
			TAB_ACTIVE_SELECTION_BACKGROUND, new Color(226,226,226),
			TAB_SELECTION_FOREGROUND, new Color(255, 0, 0),
			TAB_SELECTION_BACKGROUND, new Color(226,226,226),
            
            "jtattoo.borderColor", borderColor,
            "jtattoo.borderInactiveTabInnerBorderColor", new Color(237, 237, 237),
            "jtattoo.borderInactiveTabGradientTopColor", new Color(226, 226, 226),
            "jtattoo.borderInactiveTabGradientBottomColor", new Color(205, 205, 205),
            "jtattoo.borderActiveTabGradientTopColor", new Color(252, 252, 252),
            "jtattoo.borderActiveTabGradientBottomColor", new Color(238, 238, 238),
            "jtattoo.htmlNavigatorColor", new Color(201, 210, 225),
            "jtattoo.htmlNavigatorBorderColor", new Color(146, 156, 172),

            EDITOR_TAB_OUTER_BORDER, BorderFactory.createLineBorder(borderColor),
            VIEW_TAB_OUTER_BORDER, BorderFactory.createLineBorder(borderColor),

            "NbTabControl.borderColor", borderColor,
            "NbTabControl.borderShadowColor", borderColor,
            "NbTabControl.borderDarkShadowColor", borderColor,

            "PanelUI", "ind.igor.jtattoolf.plaf.JTattooPanelUI",
            TOOLBAR_UI, "ind.igor.jtattoolf.plaf.JTattooToolbarUI",
			EDITOR_TOOLBAR_BORDER, new EditorToolbarBorder(),
			EDITOR_TAB_DISPLAYER_UI, "ind.igor.jtattoolf.tabcontrol.plaf.JTattooEditorTabDisplayerUI",
            VIEW_TAB_DISPLAYER_UI, "ind.igor.jtattoolf.tabcontrol.plaf.JTattooViewTabDisplayerUI",
        };
		return result;
	}
}
