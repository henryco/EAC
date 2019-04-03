package dev.tindersamurai.eac.comp.factory;

public interface EscapyComponentFactoryListener {

	boolean enterComponent(String name);

	Object leaveComponent(String name, Object instance);
}