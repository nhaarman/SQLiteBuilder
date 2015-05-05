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
import com.nhaarman.sqlitebuilder.SelectFrom;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@SuppressWarnings("HardCodedStringLiteral")
public class SelectImplTest {

  @Test
  public void prependTo_withoutColumns_prependsProperSql() {
    /* Given */
    SelectImpl select = new SelectImpl();
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    select.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("SELECT *"));
  }

  @Test
  public void prependTo_withOneColumn_prependsProperSql() {
    /* Given */
    String[] columns = {"a"};
    SelectImpl select = new SelectImpl(columns);
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    select.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("SELECT a"));
  }

  @Test
  public void prependTo_withMultipleColumns_prependsProperSql() {
    /* Given */
    String[] columns = {"a", "b", "c"};
    SelectImpl select = new SelectImpl(columns);
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    select.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("SELECT a,b,c"));
  }

  @Test
  public void previous_returnsNull() {
    /* Given */
    SelectImpl select = new SelectImpl();

    /* When */
    SqlPart result = select.previous();

    /* Then */
    assertThat(result, is(nullValue()));
  }

  @Test
  public void from_returnsNotNullValue() {
    /* Given */
    SelectImpl select = new SelectImpl();

    /* When */
    SelectFrom result = select.from("my_table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}