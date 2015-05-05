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
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

@SuppressWarnings("HardCodedStringLiteral")
public class DropTableIfExistsImplTest {

  @Test
  public void prependTo_withOnlyTableName_prependsProperSql() {
    /* Given */
    DropTableIfExistsImpl dropTable = new DropTableIfExistsImpl(null, "table", mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    dropTable.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("TABLE IF EXISTS table"));
  }

  @Test
  public void prependTo_withDatabaseAndTableName_prependsProperSql() {
    /* Given */
    DropTableIfExistsImpl dropTable = new DropTableIfExistsImpl("database", "table", mock(SqlPart.class));
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    dropTable.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("TABLE IF EXISTS database.table"));
  }

  @Test
  public void previous_returnsProperItem() {
    /* Given */
    SqlPart sqlPart = mock(SqlPart.class);
    DropTableIfExistsImpl dropTable = new DropTableIfExistsImpl(null, "table", sqlPart);

    /* When */
    SqlPart result = dropTable.previous();

    /* Then */
    assertThat(result, is(sqlPart));
  }
}