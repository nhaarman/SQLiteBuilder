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

import com.nhaarman.sqlitebuilder.CreateColumns;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class CreateTableIfNotExistsImplTest {

  @Test
  public void prependTo_withOnlyTableName_prependsProperSql() {
    /* Given */
    CreateTableIfNotExistsImpl createTableIfNotExists = new CreateTableIfNotExistsImpl(null, "table", mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    createTableIfNotExists.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("TABLE IF NOT EXISTS table"));
  }

  @Test
  public void prependTo_withDatabaseAndTableName_prependsProperSql() {
    /* Given */
    CreateTableIfNotExistsImpl createTableIfNotExists = new CreateTableIfNotExistsImpl("database", "table", mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    createTableIfNotExists.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("TABLE IF NOT EXISTS database.table"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    CreateTableIfNotExistsImpl createTableIfNotExists = new CreateTableIfNotExistsImpl(null, "table", sqlPart);

    /* When */
    SqlPart result = createTableIfNotExists.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }

  @Test
  public void columns_returnsNotNullValue() {
    /* Given */
    CreateTableIfNotExistsImpl createTableIfNotExists = new CreateTableIfNotExistsImpl(null, "table", mock(SqlPart.class));

    /* When */
    CreateColumns result = createTableIfNotExists.columns();

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}