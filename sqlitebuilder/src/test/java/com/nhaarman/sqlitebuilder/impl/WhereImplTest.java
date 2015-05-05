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

import com.nhaarman.sqlitebuilder.GroupBy;
import com.nhaarman.sqlitebuilder.Limit;
import com.nhaarman.sqlitebuilder.OrderBy;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class WhereImplTest {

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    WhereImpl where = new WhereImpl("a=?", new Object[] {1}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    where.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("WHERE a=?"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    WhereImpl where = new WhereImpl("a=?", new Object[] {1}, sqlPart);

    /* When */
    SqlPart result = where.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void getArguments_returnsProperArguments() {
    /* Given */
    WhereImpl where = new WhereImpl("a=?", new Object[] {1}, mock(SqlPart.class));

    /* When */
    Object[] arguments = where.getArguments();

    /* Then */
    assertThat(arguments, is(arrayContaining((Object) 1)));
  }

  @Test
  public void groupBy_returnsNotNullValue() {
    /* Given */
    WhereImpl where = new WhereImpl("a=?", new Object[] {1}, mock(SqlPart.class));

    /* When */
    GroupBy result = where.groupBy("a");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void orderBy_returnsNotNullValue() {
    /* Given */
    WhereImpl where = new WhereImpl("a=?", new Object[] {1}, mock(SqlPart.class));

    /* When */
    OrderBy result = where.orderBy("a");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void limit_returnsNotNullValue() {
    /* Given */
    WhereImpl where = new WhereImpl("a=?", new Object[] {1}, mock(SqlPart.class));

    /* When */
    Limit result = where.limit(5);

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}