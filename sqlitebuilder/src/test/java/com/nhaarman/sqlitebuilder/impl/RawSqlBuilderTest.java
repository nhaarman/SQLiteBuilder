/*
 *  Copyright 2015 Niek Haarman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;

@SuppressWarnings("HardCodedStringLiteral")
public class RawSqlBuilderTest {

  private RawSqlBuilder mBuilder;

  @Before
  public void setUp() {
    mBuilder = new RawSqlBuilderImpl();
  }

  @Test
  public void prependingNothing_returnsEmptyString() {
    assertThat(mBuilder.toString(), isEmptyString());
  }

  @Test
  public void prependingString_returnsString() {
    /* When */
    mBuilder.prepend("test");

    /* Then */
    assertThat(mBuilder.toString(), is("test"));
  }

  @Test
  public void prependingChar_returnsChar() {
    /* When */
    mBuilder.prepend('c');

    /* Then */
    assertThat(mBuilder.toString(), is("c"));
  }

  @Test
  public void prependingStringChar_returnsCharString() {
    /* When */
    mBuilder.prepend("test").prepend('c');

    /* Then */
    assertThat(mBuilder.toString(), is("ctest"));
  }
}