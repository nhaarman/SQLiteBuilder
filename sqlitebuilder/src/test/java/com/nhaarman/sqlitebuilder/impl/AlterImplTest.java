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

import com.nhaarman.sqlitebuilder.AlterTable;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

@SuppressWarnings("HardCodedStringLiteral")
public class AlterImplTest {

  private AlterImpl mAlter;

  @Before
  public void setUp() {
    mAlter = new AlterImpl();
  }

  @Test
  public void previousIsNull() {
    /* When */
    SqlPart previous = mAlter.previous();

    /* Then */
    assertThat(previous, is(nullValue()));
  }

  @Test
  public void prependedSql_isProper() {
    /* Given */
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    mAlter.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("ALTER"));
  }

  @Test
  public void tableWithOnlyTableName_returnsTableInstance() {
    /* When */
    AlterTable alterTable = mAlter.table("table");

    /* Then */
    assertThat(alterTable, is(notNullValue()));
  }

  @Test
  public void tableWithDatabaseAndTableName_returnsTableInstance() {
    /* When */
    AlterTable alterTable = mAlter.table("table");

    /* Then */
    assertThat(alterTable, is(notNullValue()));
  }
}