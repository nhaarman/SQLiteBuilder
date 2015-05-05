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
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

@SuppressWarnings("HardCodedStringLiteral")
public class AnalyzeImplTest {

  @Test
  public void appendEmptyAnalyze_appendsProperSql() {
    /* Given */
    AnalyzeImpl analyze = new AnalyzeImpl();
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    analyze.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("ANALYZE"));
  }

  @Test
  public void appendAnalyzeDatabase_appendsProperSql() {
    /* Given */
    AnalyzeImpl analyze = new AnalyzeImpl().database("database");
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    analyze.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("ANALYZE database"));
  }

  @Test
  public void appendAnalyzeTableOrIndex_appendsProperSql() {
    /* Given */
    AnalyzeImpl analyze = new AnalyzeImpl().tableOrIndex("table");
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    analyze.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("ANALYZE table"));
  }

  @Test
  public void appendAnalyzeDatabaseAndTableOrIndex_appendsProperSql() {
    /* Given */
    AnalyzeImpl analyze = new AnalyzeImpl().databaseAndTableOrIndex("database", "table");
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    analyze.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("ANALYZE database.table"));
  }

  @Test
  public void previous_returnsNull() {
    /* Given */
    AnalyzeImpl analyze = new AnalyzeImpl();

    /* When */
    SqlPart previous = analyze.previous();

    /* Then */
    assertThat(previous, is(nullValue()));
  }
}