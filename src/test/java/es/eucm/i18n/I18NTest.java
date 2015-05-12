/**
 * Copyright (C) 2015 e-UCM Research Group (e-adventure-dev@e-ucm.es)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.eucm.i18n;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class I18NTest {

	private I18N i18N;

	@Before
	public void setUp() {
		i18N = new I18N();
		assertTrue(i18N.addMessages(ClassLoader
				.getSystemResourceAsStream("messages.properties")));
	}

	@Test
	public void test() {
		assertEquals("Hello world!", i18N.m("hello"));
		assertEquals("missing key", i18N.m("missing key"));
		assertEquals("1 2 3", i18N.m("args", 1, 2, 3));
		assertEquals("1 object", i18N.m(1, "object", "objects", 1));
		assertEquals("2 objects", i18N.m(2, "object", "objects", 2));
		assertEquals("this argument has another thing",
				i18N.m("this {} has another {}", "argument", "thing"));
		assertEquals("extra arguments 1 1 1 repeat",
				i18N.m("extra arguments {} {} {} repeat", 1));

		assertFalse(i18N.addMessages(new File("ñor")));
	}

	@Test
	public void testVariables() {
		i18N.setVariable("variable1", 2);
		assertEquals("missing key 2, , 2",
				i18N.m("missing key ${variable1}, ${variable2}, ${variable1}"));
		assertTrue(i18N.addVariables(ClassLoader
				.getSystemResourceAsStream("variables.properties")));

		assertEquals("My name is Ángel Serrano-Laguna!",
				i18N.m("My name is ${name} ${lastname}!"));

		assertEquals("Arg: 1, Variable: Ángel, Yeah!",
				i18N.m("Arg: {}, Variable: ${name}, Yeah!", 1));
	}
}
