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

import static com.nhaarman.sqlitebuilder.impl.Statements.insert;
import static com.nhaarman.sqlitebuilder.impl.Statements.insertOrAbort;
import static com.nhaarman.sqlitebuilder.impl.Statements.insertOrFail;
import static com.nhaarman.sqlitebuilder.impl.Statements.insertOrIgnore;
import static com.nhaarman.sqlitebuilder.impl.Statements.insertOrReplace;
import static com.nhaarman.sqlitebuilder.impl.Statements.insertOrRollback;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

public class InsertTest extends IntegrationTestBase {

  @Test
  public void insertIntoColumnsValues() {
    /* When */
    FinishedStatement statement =
        insert()
            .into("my_table")
            .columns("a", "b", "c")
            .values(1, 2, 3);

    /* Then */
    assertThat(toSql(statement), is("INSERT INTO my_table (a,b,c) VALUES (?,?,?)"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2, 3)));
  }

  @Test
  public void insertOrAbortIntoValues() {
    /* When */
    FinishedStatement statement =
        insertOrAbort()
            .into("my_database", "my_table")
            .values(1, 2, 3);

    /* Then */
    assertThat(toSql(statement), is("INSERT OR ABORT INTO my_database.my_table VALUES (?,?,?)"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2, 3)));
  }

  @Test
  public void insertOrFailIntoValues() {
    /* When */
    FinishedStatement statement =
        insertOrFail()
            .into("my_table")
            .values(1, 2, 3);

    /* Then */
    assertThat(toSql(statement), is("INSERT OR FAIL INTO my_table VALUES (?,?,?)"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2, 3)));
  }

  @Test
  public void insertOrIgnoreIntoColumnsValues() {
    /* When */
    FinishedStatement statement =
        insertOrIgnore()
            .into("my_table")
            .columns("a", "b", "c")
            .values(1, 2, 3);

    /* Then */
    assertThat(toSql(statement), is("INSERT OR IGNORE INTO my_table (a,b,c) VALUES (?,?,?)"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2, 3)));
  }

  @Test
  public void insertOrReplaceIntoColumnsValues() {
    /* When */
    FinishedStatement statement =
        insertOrReplace()
            .into("my_table")
            .columns("a", "b", "c")
            .values(1, 2, 3);

    /* Then */
    assertThat(toSql(statement), is("INSERT OR REPLACE INTO my_table (a,b,c) VALUES (?,?,?)"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2, 3)));
  }

  @Test
  public void insertOrRollbackIntoColumnsValues() {
    /* When */
    FinishedStatement statement =
        insertOrRollback()
            .into("my_table")
            .columns("a", "b", "c")
            .values(1, 2, 3);

    /* Then */
    assertThat(toSql(statement), is("INSERT OR ROLLBACK INTO my_table (a,b,c) VALUES (?,?,?)"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2, 3)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void insertIntoColumnsValues_withDifferentNumberOfValues_throwsException() {
    insert()
        .into("my_table")
        .columns("a", "b", "c")
        .values(1, 2);
  }
}
