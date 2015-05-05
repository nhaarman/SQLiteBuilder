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
import com.nhaarman.sqlitebuilder.SelectWhere;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class SelectFromImplTest {

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    SelectFromImpl from = new SelectFromImpl("my_table", mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    from.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("FROM my_table"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    SelectFromImpl from = new SelectFromImpl("my_table", sqlPart);

    /* When */
    SqlPart result = from.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void where_returnsNotNullValue() {
    /* Given */
    SelectFromImpl from = new SelectFromImpl("my_table", mock(SqlPart.class));

    /* When */
    SelectWhere result = from.where("a=?", 1);

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void groupBy_returnsNotNullValue() {
    /* Given */
    SelectFromImpl from = new SelectFromImpl("my_table", mock(SqlPart.class));

    /* When */
    GroupBy result = from.groupBy("a");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void orderBy_returnsNotNullValue() {
    /* Given */
    SelectFromImpl from = new SelectFromImpl("my_table", mock(SqlPart.class));

    /* When */
    OrderBy result = from.orderBy("a");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void limit_returnsNotNullValue() {
    /* Given */
    SelectFromImpl from = new SelectFromImpl("my_table", mock(SqlPart.class));

    /* When */
    Limit result = from.limit(5);

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}