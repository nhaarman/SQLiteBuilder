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

import com.nhaarman.sqlitebuilder.Deferred;
import com.nhaarman.sqlitebuilder.Exclusive;
import com.nhaarman.sqlitebuilder.Immediate;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.Transaction;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class BeginImplTest {

  @Test
  public void transaction_returnsNotNull() {
    /* Given */
    BeginImpl begin = new BeginImpl();

    /* When */
    Transaction result = begin.transaction();

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void deferred_returnsNotNull() {
    /* Given */
    BeginImpl begin = new BeginImpl();

    /* When */
    Deferred result = begin.deferred();

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void exclusive_returnsNotNull() {
    /* Given */
    BeginImpl begin = new BeginImpl();

    /* When */
    Exclusive result = begin.exclusive();

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void immediate_returnsNotNull() {
    /* Given */
    BeginImpl begin = new BeginImpl();

    /* When */
    Immediate result = begin.immediate();

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    BeginImpl begin = new BeginImpl();
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    begin.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("BEGIN"));
  }

  @Test
  public void previous_returnsNull() {
    /* Given */
    BeginImpl begin = new BeginImpl();

    /* When */
    SqlPart result = begin.previous();

    /* Then */
    assertThat(result, is(nullValue()));
  }
}