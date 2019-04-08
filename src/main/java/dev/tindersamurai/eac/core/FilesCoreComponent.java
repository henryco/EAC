package dev.tindersamurai.eac.core;

import lombok.extern.java.Log;
import dev.tindersamurai.eac.comp.annotation.EscapyComponent;
import dev.tindersamurai.eac.comp.annotation.EscapyComponentFactory;
import dev.tindersamurai.eac.parser.EscapyComponentParser;
import dev.tindersamurai.eac.parser.EscapyComponentParserProvider;

import java.nio.file.NoSuchFileException;

@EscapyComponentFactory("f") @Log// l for Loader
public final class FilesCoreComponent implements EscapyComponentParserProvider {

	private EscapyComponentParser parser;

	@Override
	public void provideParser(EscapyComponentParser parser) {
		log.info("PROVIDE PARSER: " + parser);
		// TODO FIXME NOT INJECTED IN ROOT LEVEL
		this.parser = parser;
	}

	@EscapyComponent("include")
	public <T> T external(String file) {
		try {
			log.info("::EACU:: Load external file: " + file + " | " + parser.hashCode());
			T o = parser.parseComponent(file);
			log.info("External instance: " + o);
			return o;
		} catch (NoSuchFileException e) {
			log.throwing(this.getClass().getName(), "external", e);
			e.printStackTrace();
			System.exit(42);
			return null;
		}
	}

	@EscapyComponent("home")
	public String homePath() {
		return System.getProperty("user.home");
	}

	@EscapyComponent("work")
	public String workPath() {
		return System.getProperty("user.dir");
	}

	@EscapyComponent("root")
	public String contextRootPath() {
		return parser.getRootPath();
	}
}