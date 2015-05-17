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
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class ValuesImplTest {

  @Test
  public void prependTo_singleItem_prependsProperSql() {
    /* Given */
    ValuesImpl values = new ValuesImpl(new Object[] {"a"}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    values.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("VALUES (?)"));
  }

  @Test
  public void prependTo_multipleItems_prependsProperSql() {
    /* Given */
    ValuesImpl values = new ValuesImpl(new Object[] {"a", "b", "c"}, mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    values.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("VALUES (?,?,?)"));
  }

  @Test
  public void getArguments_singleItem_returnsProperArguments() {
    /* Given */
    ValuesImpl values = new ValuesImpl(new Object[] {"a"}, mock(SqlPart.class));

    /* When */
    Object[] arguments = values.getArguments();

    /* Then */
    assertThat(arguments, is(arrayContaining((Object) "a")));
  }

  @Test
  public void getArguments_multipleItems_returnsProperArguments() {
    /* Given */
    ValuesImpl values = new ValuesImpl(new Object[] {"a", "b", "c"}, mock(SqlPart.class));

    /* When */
    Object[] arguments = values.getArguments();

    /* Then */
    assertThat(arguments, is(arrayContaining((Object) "a", "b", "c")));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    ValuesImpl values = new ValuesImpl(new Object[] {"a"}, sqlPart);

    /* When */
    SqlPart result = values.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }
}