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

import com.nhaarman.sqlitebuilder.InsertColumns;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.Values;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

public class IntoImplTest {

  @Test
  public void prependTo_table_prependsProperSql() {
    /* Given */
    IntoImpl into = new IntoImpl(null, "table", mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    into.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("INTO table"));
  }

  @Test
  public void prependTo_databaseTable_prependsProperSql() {
    /* Given */
    IntoImpl into = new IntoImpl("database", "table", mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    into.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("INTO database.table"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    IntoImpl into = new IntoImpl(null, "table", sqlPart);

    /* When */
    SqlPart result = into.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void columns_returnsNotNullValue() {
     /* Given */
    IntoImpl into = new IntoImpl(null, "table", mock(SqlPart.class));

    /* When */
    InsertColumns result = into.columns("a", "b");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void values_returnsNotNullValue() {
     /* Given */
    IntoImpl into = new IntoImpl(null, "table", mock(SqlPart.class));

    /* When */
    Values result = into.values("a", "b");

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}