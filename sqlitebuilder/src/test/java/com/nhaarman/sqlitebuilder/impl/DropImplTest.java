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

import com.nhaarman.sqlitebuilder.DropTable;
import com.nhaarman.sqlitebuilder.DropTableIfExists;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@SuppressWarnings("HardCodedStringLiteral")
public class DropImplTest {

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    DropImpl drop = new DropImpl();
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    drop.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("DROP"));
  }

  @Test
  public void previous_returnsNullValue() {
    /* Given */
    DropImpl drop = new DropImpl();

    /* When */
    SqlPart result = drop.previous();

    /* Then */
    assertThat(result, is(nullValue()));
  }

  @Test
  public void table_tableName_returnsNotNullValue() {
    /* Given */
    DropImpl drop = new DropImpl();

    /* When */
    DropTable result = drop.table("my_table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void table_databaseNameAndTableName_returnsNotNullValue() {
    /* Given */
    DropImpl drop = new DropImpl();

    /* When */
    DropTable result = drop.table("my_database", "my_table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void tableIfExists_tableName_returnsNotNullValue() {
    /* Given */
    DropImpl drop = new DropImpl();

    /* When */
    DropTableIfExists result = drop.tableIfExists("my_table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void tableIfExists_databaseNameAndTableName_returnsNotNullValue() {
    /* Given */
    DropImpl drop = new DropImpl();

    /* When */
    DropTableIfExists result = drop.tableIfExists("my_database", "my_table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}