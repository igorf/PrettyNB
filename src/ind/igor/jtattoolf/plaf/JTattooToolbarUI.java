
package ind.igor.jtattoolf.plaf;


import com.jtattoo.plaf.mint.MintToolBarUI;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ComponentUI;

public class JTattooToolbarUI extends MintToolBarUI implements ContainerListener {
        
    
    public JTattooToolbarUI() {
    }
    
    public static ComponentUI createUI(JComponent c) {
        return new JTattooToolbarUI();
    }
    
    @Override
    public void installUI( JComponent c ) {
        super.installUI(c);
        c.addContainerListener(this);
        boolean isEditorToolbar = "editorToolbar".equals (c.getName());
        installButtonUIs (c, isEditorToolbar);
    }
    
    @Override
    public void uninstallUI (JComponent c) {
        super.uninstallUI (c);
        c.setBorder (null);
        c.removeContainerListener(this);
    }
    
    @Override
    public void setFloating(boolean b, Point p) {
        //nobody wants this
    }
    
    private void installButtonUI (Component c, boolean isEditorToolbar) {
        if (c instanceof AbstractButton) {
            ((AbstractButton) c).setUI(isEditorToolbar ? buttonui : mainButtonui);
        }
    }
    
    private void installButtonUIs (Container parent, boolean isEditorToolbar) {
        Component[] c = parent.getComponents();
        for (int i=0; i < c.length; i++) {
            installButtonUI(c[i], isEditorToolbar);
        }
    }

	//TODO: Создать UI для различных типов кнопок
    private static final ButtonUI mainButtonui = new com.jtattoo.plaf.mint.MintButtonUI();
    private static final ButtonUI buttonui = new com.jtattoo.plaf.mint.MintButtonUI();

    @Override
    public void componentAdded(ContainerEvent e) {
        Container c = (Container) e.getSource();
        boolean isEditorToolbar = "editorToolbar".equals (c.getName());
        installButtonUI (e.getChild(), isEditorToolbar);
        if (isEditorToolbar) {
            Dimension min = new Dimension (32, 34);
            ((JComponent)e.getContainer()).setPreferredSize(min);
        }
    }
    
    @Override
    public void componentRemoved(ContainerEvent e) {
        //do nothing
    }
}
