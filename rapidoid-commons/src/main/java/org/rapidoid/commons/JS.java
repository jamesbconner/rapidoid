package org.rapidoid.commons;

/*
 * #%L
 * rapidoid-commons
 * %%
 * Copyright (C) 2014 - 2016 Nikolche Mihajlovski and contributors
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Map;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.u.U;

@Authors("Nikolche Mihajlovski")
@Since("5.0.4")
public class JS {

	public static <T> T eval(String js) throws ScriptException {
		return eval(js, null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T eval(String js, Map<String, ?> bindings) throws ScriptException {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		if (bindings != null) {
			Map<String, Object> map = U.cast(bindings);
			return (T) engine.eval(js, new SimpleBindings(map));
		} else {
			return (T) engine.eval(js);
		}
	}

	public static CompiledScript compile(String js) throws ScriptException {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");

		U.must(engine instanceof Compilable, "The JavaScript engine cannot compile!");

		Compilable compilable = (Compilable) engine;
		return compilable.compile(js);
	}

}
