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

import com.nhaarman.sqlitebuilder.CreateTable;
import com.nhaarman.sqlitebuilder.CreateTableIfNotExists;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.Temporary;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@SuppressWarnings("HardCodedStringLiteral")
public class CreateImplTest {

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    CreateImpl create = new CreateImpl();
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    create.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("CREATE"));
  }

  @Test
  public void previous_returnsNull() {
    /* Given */
    CreateImpl create = new CreateImpl();

    /* When */
    SqlPart result = create.previous();

    /* Then */
    assertThat(result, is(nullValue()));
  }

  @Test
  public void temp_returnsNotNullValue() {
     /* Given */
    CreateImpl create = new CreateImpl();

    /* When */
    Temporary result = create.temp();

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void temporary_returnsNotNullValue() {
     /* Given */
    CreateImpl create = new CreateImpl();

    /* When */
    Temporary result = create.temporary();

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void table_table_returnsNotNullValue() {
     /* Given */
    CreateImpl create = new CreateImpl();

    /* When */
    CreateTable result = create.table("table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void table_databaseAndTable_returnsNotNullValue() {
     /* Given */
    CreateImpl create = new CreateImpl();

    /* When */
    CreateTable result = create.table("database", "table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void tableifNotExists_table_returnsNotNullValue() {
     /* Given */
    CreateImpl create = new CreateImpl();

    /* When */
    CreateTableIfNotExists result = create.tableIfNotExists("table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void tableIfNotExists_databaseAndTable_returnsNotNullValue() {
     /* Given */
    CreateImpl create = new CreateImpl();

    /* When */
    CreateTableIfNotExists result = create.tableIfNotExists("database", "table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}