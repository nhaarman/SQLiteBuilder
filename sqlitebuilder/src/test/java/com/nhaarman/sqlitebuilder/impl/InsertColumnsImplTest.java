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
 *  limitations under the License.
 */

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.Values;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class InsertColumnsImplTest {

  @Test
  public void prependTo_singleColumn_prependsProperSql() {
    /* Given */
    InsertColumnsImpl insertColumns = new InsertColumnsImpl(new String[] {"a"}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    insertColumns.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("(a)"));
  }

  @Test
  public void prependTo_multipleColumns_prependsProperSql() {
    /* Given */
    InsertColumnsImpl insertColumns = new InsertColumnsImpl(new String[] {"a", "b", "c"}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    insertColumns.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("(a,b,c)"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    InsertColumnsImpl insertColumns = new InsertColumnsImpl(new String[] {"a", "b", "c"}, sqlPart);

    /* When */
    SqlPart result = insertColumns.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void values_returnsNotNullValue() {
    /* Given */
    InsertColumnsImpl insertColumns = new InsertColumnsImpl(new String[] {"a", "b", "c"}, mock(SqlPart.class));

    /* When */
    Values result = insertColumns.values(1, 2, 3);

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}