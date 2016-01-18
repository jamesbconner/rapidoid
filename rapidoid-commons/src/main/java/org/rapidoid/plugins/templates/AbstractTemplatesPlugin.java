package org.rapidoid.plugins.templates;

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

import org.rapidoid.annotation.Authors;
import org.rapidoid.annotation.Since;
import org.rapidoid.plugins.AbstractPlugin;

@Authors("Nikolche Mihajlovski")
@Since("4.1.0")
public abstract class AbstractTemplatesPlugin extends AbstractPlugin implements TemplatesPlugin {

	public AbstractTemplatesPlugin(String name) {
		super(name);
	}

	@Override
	public ITemplate fromFile(String filename) {
		throw new AbstractMethodError("Not implemented!");
	}

	@Override
	public ITemplate fromString(String template) {
		throw new AbstractMethodError("Not implemented!");
	}

}
