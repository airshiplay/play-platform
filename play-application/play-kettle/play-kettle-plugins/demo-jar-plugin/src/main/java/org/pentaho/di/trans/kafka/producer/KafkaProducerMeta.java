package org.pentaho.di.trans.kafka.producer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.pentaho.di.core.CheckResult;
import org.pentaho.di.core.CheckResultInterface;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.database.DatabaseMeta;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.exception.KettleXMLException;
import org.pentaho.di.core.row.RowMetaInterface;
import org.pentaho.di.core.variables.VariableSpace;
import org.pentaho.di.core.xml.XMLHandler;
import org.pentaho.di.repository.ObjectId;
import org.pentaho.di.repository.Repository;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.pentaho.di.trans.step.BaseStepMeta;
import org.pentaho.di.trans.step.StepDataInterface;
import org.pentaho.di.trans.step.StepInterface;
import org.pentaho.di.trans.step.StepMeta;
import org.pentaho.di.trans.step.StepMetaInterface;
import org.pentaho.metastore.api.IMetaStore;
import org.w3c.dom.Node;

import org.pentaho.di.core.annotations.Step;

/**
 * Kafka Producer step definitions and serializer to/from XML and to/from Kettle
 * repository.
 *
 * @author Michael Spector
 */
@Step(	
		id = "KafkaProducer",
		image = "org/pentaho/di/trans/kafka/producer/resources/kafka_producer.png",
		i18nPackageName="org.pentaho.di.trans.kafka.producer",
		name="KafkaProducerDialog.Shell.Title",
		description = "KafkaProducerDialog.Shell.Tooltip",
		categoryDescription="i18n:org.pentaho.di.trans.step:BaseStep.Category.Output")
public class KafkaProducerMeta extends BaseStepMeta implements StepMetaInterface {

	public static final String[] KAFKA_PROPERTIES_NAMES = new String[] { "bootstrap.servers", "client.id",
			"acks", "retries", "timeout.ms", "batch.size","linger.ms","buffer.memory",
            "key.serializer","value.serializer"
    };

	public static final Map<String, String> KAFKA_PROPERTIES_DEFAULTS = new HashMap<>();
	static {
		KAFKA_PROPERTIES_DEFAULTS.put("bootstrap.servers", "localhost:9092");
        KAFKA_PROPERTIES_DEFAULTS.put("client.id", "0");
		KAFKA_PROPERTIES_DEFAULTS.put("acks", "1");
        KAFKA_PROPERTIES_DEFAULTS.put("retries", "0");
        KAFKA_PROPERTIES_DEFAULTS.put("batch.size", "16384");
        KAFKA_PROPERTIES_DEFAULTS.put("linger.ms", "1");
        KAFKA_PROPERTIES_DEFAULTS.put("buffer.memory", "33554432");//32M
        KAFKA_PROPERTIES_DEFAULTS.put("timeout.ms", "30000");
        KAFKA_PROPERTIES_DEFAULTS.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		KAFKA_PROPERTIES_DEFAULTS.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	}

	private Properties kafkaProperties = new Properties();
	private String topic;
	private String keyField;
        
	public Properties getKafkaProperties() {
		return kafkaProperties;
	}

	/**
	 * @return Kafka topic name
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            Kafka topic name
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return Target key field name in Kettle stream
	 */
	public String getKeyField() {
		return keyField;
	}

	/**
	 * @param field
	 *            Target key field name in Kettle stream
	 */
	public void setKeyField(String field) {
		this.keyField = field;
	}


	public void check(List<CheckResultInterface> remarks, TransMeta transMeta, StepMeta stepMeta, RowMetaInterface prev,
					  String input[], String output[], RowMetaInterface info, VariableSpace space, Repository repository, IMetaStore metaStore) {

		if (isEmpty(topic)) {
			remarks.add(new CheckResult(CheckResultInterface.TYPE_RESULT_ERROR,
					Messages.getString("KafkaProducerMeta.Check.InvalidTopic"), stepMeta));
		}

		try {
			new org.apache.kafka.clients.producer.KafkaProducer(kafkaProperties);
		} catch (IllegalArgumentException e) {
			remarks.add(new CheckResult(CheckResultInterface.TYPE_RESULT_ERROR, e.getMessage(), stepMeta));
		}
	}

	public StepInterface getStep(StepMeta stepMeta, StepDataInterface stepDataInterface, int cnr, TransMeta transMeta,
			Trans trans) {
		return new KafkaProducer(stepMeta, stepDataInterface, cnr, transMeta, trans);
	}

	public StepDataInterface getStepData() {
		return new KafkaProducerData();
	}

	public void loadXML(Node stepnode, List<DatabaseMeta> databases, IMetaStore metaStore)
			throws KettleXMLException {

		try {
			topic = XMLHandler.getTagValue(stepnode, "TOPIC");
			keyField = XMLHandler.getTagValue(stepnode, "KEYFIELD");
			Node kafkaNode = XMLHandler.getSubNode(stepnode, "KAFKA");
			String[] kafkaElements = XMLHandler.getNodeElements(kafkaNode);
			if (kafkaElements != null) {
				for (String propName : kafkaElements) {
					String value = XMLHandler.getTagValue(kafkaNode, propName);
					if (value != null) {
						kafkaProperties.put(propName, value);
					}
				}
			}
		} catch (Exception e) {
			throw new KettleXMLException(Messages.getString("KafkaProducerMeta.Exception.loadXml"), e);
		}
	}

	public String getXML() throws KettleException {
		StringBuilder retval = new StringBuilder();
		if (topic != null) {
			retval.append("    ").append(XMLHandler.addTagValue("TOPIC", topic));
		}

		if (keyField != null) {
			retval.append("    ").append(XMLHandler.addTagValue("KEYFIELD", keyField));
		}
		retval.append("    ").append(XMLHandler.openTag("KAFKA")).append(Const.CR);
		for (String name : kafkaProperties.stringPropertyNames()) {
			String value = kafkaProperties.getProperty(name);
			if (value != null) {
				retval.append("      " + XMLHandler.addTagValue(name, value));
			}
		}
		retval.append("    ").append(XMLHandler.closeTag("KAFKA")).append(Const.CR);
		return retval.toString();
	}

	public void readRep(Repository rep, IMetaStore metaStore, ObjectId stepId, List<DatabaseMeta> databases)
			throws KettleException {
		try {
			topic = rep.getStepAttributeString(stepId, "TOPIC");
			keyField = rep.getStepAttributeString(stepId, "KEYFIELD");
			String kafkaPropsXML = rep.getStepAttributeString(stepId, "KAFKA");
			if (kafkaPropsXML != null) {
				kafkaProperties.loadFromXML(new ByteArrayInputStream(kafkaPropsXML.getBytes()));
			}
			// Support old versions:
			for (String name : KAFKA_PROPERTIES_NAMES) {
				String value = rep.getStepAttributeString(stepId, name);
				if (value != null) {
					kafkaProperties.put(name, value);
				}
			}
		} catch (Exception e) {
			throw new KettleException("KafkaProducerMeta.Exception.loadRep", e);
		}
	}

	public void saveRep(Repository rep, IMetaStore metaStore, ObjectId transformationId, ObjectId stepId) throws KettleException {
		try {
			if (topic != null) {
				rep.saveStepAttribute(transformationId, stepId, "TOPIC", topic);
			}

			if (keyField != null) {
				rep.saveStepAttribute(transformationId, stepId, "KEYFIELD", keyField);
			}
			ByteArrayOutputStream buf = new ByteArrayOutputStream();
			kafkaProperties.storeToXML(buf, null);
			rep.saveStepAttribute(transformationId, stepId, "KAFKA", buf.toString());
		} catch (Exception e) {
			throw new KettleException("KafkaProducerMeta.Exception.saveRep", e);
		}
	}

	public void setDefault() {
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}
