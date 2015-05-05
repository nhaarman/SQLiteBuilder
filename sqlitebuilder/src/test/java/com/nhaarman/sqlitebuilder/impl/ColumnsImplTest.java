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

import com.nhaarman.sqlitebuilder.Column;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.WithoutRowId;
import org.junit.Test;

import static com.nhaarman.sqlitebuilder.impl.Statements.column;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class ColumnsImplTest {

  @Test
  public void prependToWithoutColumns_prependsProperSql() {
    /* Given */
    ColumnsImpl columns = new ColumnsImpl(new Column[0], mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    columns.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("()"));
  }

  @Test
  public void prependToWithOneColumn_prependsProperSql() {
    /* Given */
    ColumnsImpl columns = new ColumnsImpl(new Column[] {new ColumnImpl("a")}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    columns.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("(a)"));
  }

  @Test
  public void prependToWithMultipleColumns_prependsProperSql() {
    /* Given */
    Column[] cs = {column("a"), column("b"), column("c")};
    ColumnsImpl columns = new ColumnsImpl(cs, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    columns.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("(a,b,c)"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    ColumnsImpl columns = new ColumnsImpl(new Column[0], sqlPart);

    /* When */
    SqlPart result = columns.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void withoutRowId_returnsNotNullValue() {
    /* Given */
    ColumnsImpl columns = new ColumnsImpl(new Column[0], mock(SqlPart.class));

    /* When */
    WithoutRowId result = columns.withoutRowId();

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}