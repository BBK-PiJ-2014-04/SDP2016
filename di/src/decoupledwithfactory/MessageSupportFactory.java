package decoupledwithfactory;

import java.io.FileInputStream;
import java.util.Properties;

public class MessageSupportFactory {

	private static MessageSupportFactory instance = null;
	private Properties props = null;
	private MessageRenderer renderer = null;
	private MessageProvider provider = null;

	//we don't want people to instantiate but we want to use the static method
	private MessageSupportFactory() {
		props = new Properties(); //Hashmap structure

		try {
			props.load(new FileInputStream("/Users/keith/Courses/sdp/2016/SDP2016/di/src/decoupledwithfactory/bean.properties"));

			// get the implementation classes
			String rendererClass = props.getProperty("renderer.class");
			String providerClass = props.getProperty("provider.class");

			renderer = (MessageRenderer) Class.forName(rendererClass)
					.newInstance();
			provider = (MessageProvider) Class.forName(providerClass)
					.newInstance();
		} catch (Exception ex) {
			System.err.println("Failed to create factory methods");
			ex.printStackTrace();
		}
	}

	static {
		instance = new MessageSupportFactory();
	}

	public static MessageSupportFactory getInstance() {
		return instance;
	} //this returns in fact an instance of this class, as instance is a MessageSupportFactory instance. Since we are inside the class, we can actually instantiate a new class.
	//but everytime this method is call, the same reference is returned.

	public MessageRenderer getMessageRenderer() {
		return renderer;
	}

	public MessageProvider getMessageProvider() {
		return provider;
	}

}