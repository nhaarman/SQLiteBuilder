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

package com.nhaarman.sqlitebuilder.integration;

import org.junit.Test;

import static com.nhaarman.sqlitebuilder.impl.Statements.column;
import static com.nhaarman.sqlitebuilder.impl.Statements.create;
import static com.nhaarman.sqlitebuilder.impl.Statements.select;

@SuppressWarnings("HardCodedStringLiteral")
public class CreateTableTest extends IntegrationTestBase {

  @Test
  public void createTableWithThreeColumns() {
    /* When */
    create()
        .table("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("CREATE TABLE my_table (a,b,c)");
  }

  @Test
  public void createTableWithThreeColumnsWithoutRowId() {
    /* When */
    create()
        .table("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId()
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("CREATE TABLE my_table (a,b,c) WITHOUT ROWID");
  }

  @Test
  public void createTempTableWithThreeColumnsWithoutRowId() {
    /* When */
    create()
        .temp()
        .table("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId()
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("CREATE TEMPORARY TABLE my_table (a,b,c) WITHOUT ROWID");
  }

  @Test
  public void createTableIfNotExistsWithThreeColumnsWithoutRowId() {
    /* When */
    create()
        .tableIfNotExists("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId()
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("CREATE TABLE IF NOT EXISTS my_table (a,b,c) WITHOUT ROWID");
  }

  @Test
  public void createTempTableIfNotExistsWithThreeColumnsWithoutRowId() {
    /* When */
    create()
        .temp()
        .tableIfNotExists("my_table")
        .columns(
            column("a"),
            column("b"),
            column("c")
        )
        .withoutRowId()
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("CREATE TEMPORARY TABLE IF NOT EXISTS my_table (a,b,c) WITHOUT ROWID");
  }

  @Test
  public void createTableAs() {
    /* When */
    create()
        .table("my_table")
        .as(
            select("a", "b")
                .from("my_other_table")
        )
        .executeOn(mStatementExecutor);

    /* Then */

    verifyStatementExecuted("CREATE TABLE my_table AS SELECT a,b FROM my_other_table");
  }
}
