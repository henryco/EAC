package dev.tindersamurai.eac.core;

import lombok.extern.java.Log;
import dev.tindersamurai.eac.comp.annotation.Arg;
import dev.tindersamurai.eac.comp.annotation.EscapyComponent;
import dev.tindersamurai.eac.comp.annotation.EscapyComponentFactory;
import dev.tindersamurai.eac.comp.annotation.NotNull;
import dev.tindersamurai.eac.comp.factory.EscapyComponentFactoryProvider;
import dev.tindersamurai.eac.comp.factory.IEscapyComponentFactory;
import dev.tindersamurai.eac.util.ExpressionProcessor;

import java.util.HashMap;
import java.util.Map;

@Log @EscapyComponentFactory("v")
public final class VariablesCoreComponent implements EscapyComponentFactoryProvider {

	private static final String OPEN_TOKEN = "\\$\\{";
	private static final String CLOSING_TOKEN = "}";

	private IEscapyComponentFactory componentFactory;
	private final Map<Object, Object> variables;

	public VariablesCoreComponent() {
		this.variables = new HashMap<>();
	}

	@Override
	public void provideComponentFactory(IEscapyComponentFactory factory) {
		this.componentFactory = factory;
	}

	@EscapyComponent("store")
	public final <T> T store (
			@NotNull @Arg("name") String name,
			@NotNull @Arg("arg") T arg
	) {
		variables.put(name, arg);
		return arg;
	}

	@EscapyComponent("get")
	public final <T> T get(@NotNull String name) {
		//noinspection unchecked
		return (T) variables.get(name);
	}

	@EscapyComponent("exp")
	public final Object expression (@NotNull String exp) {
		// ${ [resources.conf] }/animations/wow.png
		// TODO
		return null;
	}

	@EscapyComponent("string")
	public final String string(@NotNull String arg) {
		// ${resources.config-path}/animations/mco.eacxml
		return ExpressionProcessor.replace(OPEN_TOKEN, CLOSING_TOKEN, arg,
				token -> componentFactory.createComponent(token, new HashMap<>())
		);
	}
}