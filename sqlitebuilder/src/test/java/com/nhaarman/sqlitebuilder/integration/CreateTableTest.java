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

package com.nhaarman.sqlitebuilder.integration;

import com.nhaarman.sqlitebuilder.FinishedStatement;
import org.junit.Test;

import static com.nhaarman.sqlitebuilder.impl.Statements.column;
import static com.nhaarman.sqlitebuilder.impl.Statements.create;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("HardCodedStringLiteral")
public class CreateTableTest extends IntegrationTestBase {

  @Test
  public void createTableWithThreeColumns() {
    /* When */
    FinishedStatement statement = create()
        .table("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        );

    /* Then */
    assertThat(toSql(statement), is("CREATE TABLE my_table (a,b,c)"));
  }

  @Test
  public void createTableWithThreeColumnsWithoutRowId() {
    /* When */
    FinishedStatement statement = create()
        .table("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId();

    /* Then */
    assertThat(toSql(statement), is("CREATE TABLE my_table (a,b,c) WITHOUT ROWID"));
  }

  @Test
  public void createTempTableWithThreeColumnsWithoutRowId() {
    /* When */
    FinishedStatement statement = create()
        .temp()
        .table("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId();

    /* Then */
    assertThat(toSql(statement), is("CREATE TEMPORARY TABLE my_table (a,b,c) WITHOUT ROWID"));
  }

  @Test
  public void createTableIfNotExistsWithThreeColumnsWithoutRowId() {
    /* When */
    FinishedStatement statement = create()
        .tableIfNotExists("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId();

    /* Then */
    assertThat(toSql(statement), is("CREATE TABLE IF NOT EXISTS my_table (a,b,c) WITHOUT ROWID"));
  }

  @Test
  public void createTempTableIfNotExistsWithThreeColumnsWithoutRowId() {
    /* When */
    FinishedStatement statement = create()
        .temp()
        .tableIfNotExists("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId();

    /* Then */
    assertThat(toSql(statement), is("CREATE TEMPORARY TABLE IF NOT EXISTS my_table (a,b,c) WITHOUT ROWID"));
  }

  @Test
  public void createTableAs() {
    /* When */
    FinishedStatement statement = create()
        .table("my_table")
        .as()
        .select("a", "b")
        .from("my_other_table");

    /* Then */
    assertThat(toSql(statement), is("CREATE TABLE my_table AS SELECT a,b FROM my_other_table"));
  }
}
