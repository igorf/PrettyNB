package ind.igor.jtattoolf.tabcontrol.plaf;

import java.awt.*;
import javax.swing.Icon;
import javax.swing.JComponent;
import org.netbeans.swing.tabcontrol.plaf.AbstractTabCellRenderer;
import org.netbeans.swing.tabcontrol.plaf.TabControlButtonFactory;
import org.netbeans.swing.tabcontrol.plaf.TabPainter;


public class JTattooEditorTabCellRenderer extends AbstractTabCellRenderer {
    private static final JTattooTabPainter JTattooborder = new JTattooTabPainter();
    private static final JTattooRightClippedTabPainter rightBorder = new JTattooRightClippedTabPainter();
    private static final JTattooLeftClippedTabPainter leftBorder = new JTattooLeftClippedTabPainter();
	
	static final Color TAB_BORDER_COLOR = new Color(162, 162, 162);
	static final Color INACTIVE_TAB_INNER_BORDER_COLOR = new Color(237, 237, 237);
	static final Color INACTIVE_TAB_GRADIENT_TOP = new Color(226, 226, 226);
	static final Color INACTIVE_TAB_GRADIENT_BOTTOM = new Color(205, 205, 205);
	static final Color ACTIVE_TAB_GRADIENT_TOP = new Color(252, 252, 252);
	static final Color ACTIVE_TAB_GRADIENT_BOTTOM = new Color(238, 238, 238);

    static final Color ATTENTION_COLOR = new Color(255, 238, 120);
	static final Color ACTIVE_TAB_COLOR = new Color(238,238,238);
	static final Color INACTIVE_TAB_COLOR = new Color(238, 238, 238);
	static final Color ACTIVE_TAB_TOP_COLOR = new Color(70, 120, 200);

	public JTattooEditorTabCellRenderer() {
        super(leftBorder, JTattooborder, rightBorder, new Dimension(34, 29));
        setBorder(JTattooborder);
    }

	@Override
    protected int getCaptionYAdjustment() {
        return 0;
    }

	@Override
    public Dimension getPadding() {
        Dimension d = super.getPadding();
        d.width = isShowCloseButton() && !Boolean.getBoolean("nb.tabs.suppressCloseButton") ? 34 : 24;
        return d;
    }

	protected Insets getDefaultTabInsets() {
		return new Insets(3, 8, 3, 0);
	}

    private static class JTattooTabPainter implements TabPainter {
		@Override
        public Insets getBorderInsets(Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
            return mtr.getDefaultTabInsets();
        }

		@Override
        public boolean supportsCloseButton(JComponent renderer) {
            return ((AbstractTabCellRenderer) renderer).isShowCloseButton();
        }

		@Override
        public boolean isBorderOpaque() {
            return true;
        }

		@Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;

			g.setColor(TAB_BORDER_COLOR);
			g.drawLine(x + width -1, y + 2, x + width - 1, y + height - 1);
			g.drawLine(x, y + 2, x + width - 1, y + 2);
			if (mtr.isSelected()) {
				g.setColor(ACTIVE_TAB_COLOR);
			}
			g.drawLine(x, y + height - 1, x + width - 2, y + height - 1);
			if (!mtr.isSelected()) {
				int ix0 = x;
				int ix1 = x + width - 2;
				int iy0 = y + 3;
				int iy1 = y + height - 2;
				g.setColor(INACTIVE_TAB_INNER_BORDER_COLOR);
				g.drawLine(ix0, iy1, ix1, iy1);
				g.drawLine(ix0, iy0, ix1, iy0);
				g.drawLine(ix0, iy1, ix0, iy0);
				g.drawLine(ix1, iy1, ix1, iy0);
			}
        }

		@Override
        public Polygon getInteriorPolygon(Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;

			Insets ins = getBorderInsets(c);
            Polygon p = new Polygon();
            int x = mtr.isLeftmost() ? -1 : 0;
            int y = 3;

            int width = mtr.isLeftmost() ? c.getWidth() + 1 : c.getWidth();
            int height = c.getHeight() - 1;

            p.addPoint(x, y);
            p.addPoint(x, y + height);
            p.addPoint(x + width, y + height);
            p.addPoint(x + width, y);
            return p;

        }

		@Override
        public void paintInterior(Graphics g, Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
            Polygon pl = getInteriorPolygon(c);
			int x = pl.xpoints[0];
			int y = pl.ypoints[0];
			int width = pl.xpoints[3] - pl.xpoints[0];
			int height = pl.ypoints[1] - pl.ypoints[0];

			Graphics2D g2d = (Graphics2D) g;
			Paint p = g2d.getPaint();
			if( mtr.isSelected() ) {
				g2d.setPaint(
						ColorUtil.getGradientPaint(
							x, y, ACTIVE_TAB_GRADIENT_TOP,
							x, y+height/2, ACTIVE_TAB_GRADIENT_BOTTOM
						)
				);
			} else {
				g2d.setPaint( 
						ColorUtil.getGradientPaint(
							x, y, INACTIVE_TAB_GRADIENT_TOP,
							x, y+height/2, INACTIVE_TAB_GRADIENT_BOTTOM
						)
				);
			}
			g2d.fillRect(x, y, width, height);
			g2d.setPaint(p);

            //Get the close button bounds, more or less
            Rectangle r = new Rectangle();
            getCloseButtonRectangle(mtr, r, new Rectangle(0, 0,
                                                          mtr.getWidth(),
                                                          mtr.getHeight()));

            if (!g.hitClip(r.x, r.y, r.width, r.height)) {
                return;
            }
            paintCloseButton( g, (JComponent)c );
        }

		@Override
        public void getCloseButtonRectangle(JComponent jc, final Rectangle rect, Rectangle bounds) {
            if (!((AbstractTabCellRenderer) jc).isShowCloseButton()) {
                rect.x = -100;
                rect.y = -100;
                rect.width = 0;
                rect.height = 0;
                return;
            }
            String iconPath = findIconPath((JTattooEditorTabCellRenderer) jc);
            Icon icon = TabControlButtonFactory.getIcon(iconPath);
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            rect.x = bounds.x + bounds.width - iconWidth - 5;
            rect.y = bounds.y + (Math.max(0, bounds.height / 2 - iconHeight / 2))+2;
            rect.width = iconWidth;
            rect.height = iconHeight;
        }

        private void paintCloseButton(Graphics g, JComponent c) {
            if (((AbstractTabCellRenderer) c).isShowCloseButton()) {

                Rectangle r = new Rectangle(0, 0, c.getWidth(), c.getHeight());
                Rectangle cbRect = new Rectangle();
                getCloseButtonRectangle((JComponent) c, cbRect, r);

                //paint close button
                String iconPath = findIconPath( (JTattooEditorTabCellRenderer)c );
                Icon icon = TabControlButtonFactory.getIcon( iconPath );
                icon.paintIcon(c, g, cbRect.x, cbRect.y);
            }
        }

        private String findIconPath( JTattooEditorTabCellRenderer renderer ) {
            if( renderer.inCloseButton() && renderer.isPressed() ) {
                return "org/openide/awt/resources/mac_close_pressed.png"; // NOI18N
            }
            if( renderer.inCloseButton() ) {
				return "org/openide/awt/resources/mac_close_rollover.png"; // NOI18N
            }
            return "org/openide/awt/resources/mac_close_enabled.png"; // NOI18N
        }
    }

    private static class JTattooLeftClippedTabPainter implements TabPainter {

		@Override
        public Insets getBorderInsets(Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
            return mtr.getDefaultTabInsets();
        }

		@Override
        public boolean isBorderOpaque() {
            return true;
        }

		@Override
        public Polygon getInteriorPolygon(Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;

            Insets ins = getBorderInsets(c);
            Polygon p = new Polygon();
            //Ensure the left edge is out of bounds
            int x = -1;
            int y = 3;

            int width = c.getWidth() + 1;
            int height = c.getHeight();

            p.addPoint(x, y);
            p.addPoint(x + width, y);
            p.addPoint(x + width, y + height);
            p.addPoint(x, y + height);
            return p;
        }

		@Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
			g.setColor(TAB_BORDER_COLOR);
			g.drawLine(x + width -1, y + 2, x + width - 1, y + height - 1);
			g.drawLine(x, y + 2, x + width - 1, y + 2);
			if (mtr.isSelected()) {
				g.setColor(ACTIVE_TAB_COLOR);
			}
			g.drawLine(x, y + height - 1, x + width - 2, y + height - 1);
			if (!mtr.isSelected()) {
				int ix0 = x;
				int ix1 = x + width - 2;
				int iy0 = y + 3;
				int iy1 = y + height - 2;
				g.setColor(INACTIVE_TAB_INNER_BORDER_COLOR);
				g.drawLine(ix0, iy1, ix1, iy1);
				g.drawLine(ix0, iy0, ix1, iy0);
				g.drawLine(ix0, iy1, ix0, iy0);
				g.drawLine(ix1, iy1, ix1, iy0);
			}
        }

		@Override
        public void paintInterior(Graphics g, Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
            Polygon pl = getInteriorPolygon(c);
			int x = pl.xpoints[0];
			int y = pl.ypoints[0];
			int width = pl.xpoints[1] - pl.xpoints[0];
			int height = pl.ypoints[3] - pl.ypoints[0];

			Graphics2D g2d = (Graphics2D) g;
			Paint p = g2d.getPaint();
			if( mtr.isSelected() ) {
				g2d.setPaint(
						ColorUtil.getGradientPaint(
							x, y, ACTIVE_TAB_GRADIENT_TOP,
							x, y+height/2, ACTIVE_TAB_GRADIENT_BOTTOM
						)
				);
			} else {
				g2d.setPaint( 
						ColorUtil.getGradientPaint(
							x, y, INACTIVE_TAB_GRADIENT_TOP,
							x, y+height/2, INACTIVE_TAB_GRADIENT_BOTTOM
						)
				);
			}
			g2d.fillRect(x, y, width, height);
			g2d.setPaint(p);
        }

		@Override
        public void getCloseButtonRectangle(JComponent jc, Rectangle rect, Rectangle bounds) {
            bounds.setBounds(-20, -20, 0, 0);
        }

		@Override
        public boolean supportsCloseButton(JComponent renderer) {
            return false;
        }
    }

    private static class JTattooRightClippedTabPainter implements TabPainter {
		@Override
        public Insets getBorderInsets(Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
            return mtr.getDefaultTabInsets();
        }

		@Override
        public boolean isBorderOpaque() {
            return true;
        }

		@Override
        public Polygon getInteriorPolygon(Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
            Insets ins = getBorderInsets(c);
            Polygon p = new Polygon();

			int x = mtr.isLeftmost() ? 1 : 0;
            int y = 3;

            int width = c.getWidth();
            int height = c.getHeight();

            p.addPoint(x, y);
            p.addPoint(x, y + height);
            p.addPoint(x + width, y + height);
            p.addPoint(x + width, y);
            return p;
        }

		@Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
			g.setColor(TAB_BORDER_COLOR);
			g.drawLine(x + width -1, y + 2, x + width - 1, y + height - 1);
			g.drawLine(x, y + 2, x + width - 1, y + 2);
			if (mtr.isSelected()) {
				g.setColor(ACTIVE_TAB_COLOR);
			}
			g.drawLine(x, y + height - 1, x + width - 2, y + height - 1);
			if (!mtr.isSelected()) {
				int ix0 = x;
				int ix1 = x + width - 2;
				int iy0 = y + 3;
				int iy1 = y + height - 2;
				g.setColor(INACTIVE_TAB_INNER_BORDER_COLOR);
				g.drawLine(ix0, iy1, ix1, iy1);
				g.drawLine(ix0, iy0, ix1, iy0);
				g.drawLine(ix0, iy1, ix0, iy0);
				g.drawLine(ix1, iy1, ix1, iy0);
			}
        }

		@Override
        public void paintInterior(Graphics g, Component c) {
            JTattooEditorTabCellRenderer mtr = (JTattooEditorTabCellRenderer) c;
            Polygon pl = getInteriorPolygon(c);
			int x = pl.xpoints[0];
			int y = pl.ypoints[0];
			int width = pl.xpoints[3] - pl.xpoints[0];
			int height = pl.ypoints[1] - pl.ypoints[0];

			Graphics2D g2d = (Graphics2D) g;
			Paint p = g2d.getPaint();
			if( mtr.isSelected() ) {
				g2d.setPaint(
						ColorUtil.getGradientPaint(
							x, y, ACTIVE_TAB_GRADIENT_TOP,
							x, y+height/2, ACTIVE_TAB_GRADIENT_BOTTOM
						)
				);
			} else {
				g2d.setPaint( 
						ColorUtil.getGradientPaint(
							x, y, INACTIVE_TAB_GRADIENT_TOP,
							x, y+height/2, INACTIVE_TAB_GRADIENT_BOTTOM
						)
				);
			}
			g2d.fillRect(x, y, width, height);
			g2d.setPaint(p);
        }

		@Override
        public void getCloseButtonRectangle(JComponent jc, Rectangle rect, Rectangle bounds) {
            bounds.setBounds(-20, -20, 0, 0);
        }

		@Override
        public boolean supportsCloseButton(JComponent renderer) {
            return false;
        }
    }
}