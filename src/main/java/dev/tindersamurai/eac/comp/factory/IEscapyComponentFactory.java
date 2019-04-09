package dev.tindersamurai.eac.comp.factory;

import java.util.Map;

public interface IEscapyComponentFactory {

	interface FactoryListener {
		boolean beforeCreateComponent(String name, Map<String, Object> arguments);
	}

	<T> T createComponent(String name, Map<String, Object> arguments);

	void setFactoryListener(FactoryListener factoryListener);

	String getNameSpaceSeparator();

	Object getFactory(String name);
}