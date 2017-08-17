package org.pentaho.di.trans.kafka.producer;

import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.row.ValueMetaInterface;
import org.pentaho.di.trans.step.BaseStepData;
import org.pentaho.di.trans.step.StepDataInterface;

/**
 * Holds data processed by this step
 * 
 * @author Michael
 */
public class KafkaProducerData extends BaseStepData implements StepDataInterface {

	org.apache.kafka.clients.producer.KafkaProducer<Object, Object> producer;
	RowMetaInterface outputRowMeta;
	int keyFieldNr;
	boolean keyIsString;
	ValueMetaInterface keyFieldMeta;
}
