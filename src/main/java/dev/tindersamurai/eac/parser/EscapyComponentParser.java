package dev.tindersamurai.eac.parser;


import dev.tindersamurai.eac.comp.factory.IEscapyComponentFactory;
import dev.tindersamurai.eac.obj.IEscapyObjectFactory;

import java.io.File;
import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public interface EscapyComponentParser {

	String PREFIX_COMPONENT = "c";
	String PREFIX_OBJECT = "o";
	String PREFIX_METHOD = "m";

	String ATTR_NAME = "name";
	String ATTR_CLASS = "class";

	String OBJECT_NEW = "new";

	void setComponentFactory(IEscapyComponentFactory factory);

	void setObjectFactory(IEscapyObjectFactory factory);

	String getRootPath();

	void setRootPath(String path);

	<T> T parseComponent(String file) throws NoSuchFileException;

	<T> T parseComponent(InputStream inputStream, String contextRootPath);

	default <T> T parseComponent(InputStream inputStream) {
		return parseComponent(inputStream, System.getProperty("user.dir"));
	}

	default <T> T parseComponent(File file) throws NoSuchFileException {
		return parseComponent(file.getAbsolutePath());
	}

	default <T> T parseComponent(Path path) throws NoSuchFileException {
		return parseComponent(path.toFile());
	}
}