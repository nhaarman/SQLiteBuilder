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
import com.nhaarman.sqlitebuilder.UpdateValues;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class SetImplTest {

  @Test
  public void prependTo_withSingleColumn_prependsProperSql() {
    /* Given */
    SetImpl set = new SetImpl(new String[] {"a"}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    set.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("SET a=?"));
  }

  @Test
  public void prependTo_withMultipleColumns_prependsProperSql() {
    /* Given */
    SetImpl set = new SetImpl(new String[] {"a", "b", "c"}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    set.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("SET a=?,b=?,c=?"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    SetImpl set = new SetImpl(new String[] {"a"}, sqlPart);

    /* When */
    SqlPart result = set.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void values_returnsNotNullValue() {
    /* Given */
    SetImpl set = new SetImpl(new String[] {"a"}, mock(SqlPart.class));

    /* When */
    UpdateValues result = set.values(2);

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}