package be.ibridge.kettle.dummy;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.ui.core.PropsUI;

/**
 *
 * VM Option -XstartOnFirstThread
 * Created by lig on 17/8/11.
 */
public class DummyPluginDialogTest {

    @Test
    public void testOpen() throws KettleException {
//        KettleEnvironment.init(false);

        Display display =new Display();
        PropsUI.init(display,1);
        Shell shell = new Shell(display);
        DummyPluginDialog dialog = new DummyPluginDialog(shell, new DummyPluginMeta(), new TransMeta(), "");
        dialog.open();
    }
}
