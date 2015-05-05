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
import com.nhaarman.sqlitebuilder.SavePoint;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.To;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class RollbackTransactionImplTest {

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    RollbackTransactionImpl rollbackTransaction = new RollbackTransactionImpl(mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    rollbackTransaction.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("TRANSACTION"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    RollbackTransactionImpl rollbackTransaction = new RollbackTransactionImpl(sqlPart);

    /* When */
    SqlPart result = rollbackTransaction.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void to_returnsNotNull() {
    /* Given */
    RollbackTransactionImpl rollbackTransaction = new RollbackTransactionImpl(mock(SqlPart.class));

    /* When */
    To result = rollbackTransaction.to();

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void to_savePointName_returnsNotNull() {
    /* Given */
    RollbackTransactionImpl rollbackTransaction = new RollbackTransactionImpl(mock(SqlPart.class));

    /* When */
    SavePoint result = rollbackTransaction.to("savePointName");

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}