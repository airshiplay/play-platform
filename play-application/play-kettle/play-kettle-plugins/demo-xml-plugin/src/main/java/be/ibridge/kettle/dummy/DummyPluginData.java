/**
 * Created by lig on 17/8/9.
 */

package be.ibridge.kettle.dummy;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

/**
 *
 *
 * @author Matt
 * @since  24-mrt-2005
 */
public class DummyPluginData extends BaseStepData implements StepDataInterface
{
    public RowMetaInterface outputRowMeta;

    public DummyPluginData()
    {
        super();
    }
}