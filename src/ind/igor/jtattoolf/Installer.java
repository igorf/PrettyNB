package ind.igor.jtattoolf;

import com.jtattoo.plaf.smart.SmartLookAndFeel;
import java.awt.Font;
import java.util.Properties;
import javax.swing.UIManager;
import org.openide.modules.ModuleInstall;


public class Installer extends ModuleInstall {
	@Override
	public void restored() {
		preInit();
		if (isJTattooInstalled()) {
			installJTattoLFCustoms();
		}
	}

	protected void preInit() {
		try {
			SmartLookAndFeel.setTheme(getThemeProperties());
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			int uiFontSize = 12;
			setUIFont (new javax.swing.plaf.FontUIResource(new Font("DroidSans", Font.PLAIN, uiFontSize)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean isJTattooInstalled() {
		return true;
	}

	protected void installJTattoLFCustoms() {
		JTattoLFCustoms customs = new JTattoLFCustoms();
        UIManager.getDefaults().putDefaults(customs.createApplicationSpecificKeysAndValues());
        UIManager.getDefaults().putDefaults(customs.createGuaranteedKeysAndValues());
        UIManager.getDefaults().putDefaults(customs.createLookAndFeelCustomizationKeysAndValues());
	}

	private static void setUIFont(javax.swing.plaf.FontUIResource f) {
		java.util.Enumeration keys = UIManager.getDefaults().keys();
		while (keys.hasMoreElements())
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof javax.swing.plaf.FontUIResource)
			{
				UIManager.put(key, f);
			}
		}
	}

	private Properties getThemeProperties() {
		Properties props = new Properties();
		props.put("logoString", "NetBeans");
		props.put("windowDecoration", "off");

		return props;
	}
}
