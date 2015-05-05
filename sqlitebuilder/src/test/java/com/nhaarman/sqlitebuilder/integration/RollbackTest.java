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
import com.nhaarman.sqlitebuilder.impl.Statements;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("HardCodedStringLiteral")
public class RollbackTest extends IntegrationTestBase {

  @Test
  public void rollback() {
    /* When */
    FinishedStatement statement = Statements.rollback();

    /* Then */
    assertThat(toSql(statement), is("ROLLBACK"));
  }

  @Test
  public void rollbackTransaction() {
    /* When */
    FinishedStatement statement = Statements.rollback().transaction();

    /* Then */
    assertThat(toSql(statement), is("ROLLBACK TRANSACTION"));
  }

  @Test
  public void rollbackToSavepoint() {
    /* When */
    FinishedStatement statement = Statements.rollback().to().savePoint("savepointname");

    /* Then */
    assertThat(toSql(statement), is("ROLLBACK TO SAVEPOINT savepointname"));
  }

  @Test
  public void rollbackTo() {
    /* When */
    FinishedStatement statement = Statements.rollback().to("savepointname");

    /* Then */
    assertThat(toSql(statement), is("ROLLBACK TO SAVEPOINT savepointname"));
  }

  @Test
  public void rollbackTransactionTo() {
    /* When */
    FinishedStatement statement = Statements.rollback().transaction().to("savepointname");

    /* Then */
    assertThat(toSql(statement), is("ROLLBACK TRANSACTION TO SAVEPOINT savepointname"));
  }

  @Test
  public void rollbackTransactionToSavepoint() {
    /* When */
    FinishedStatement statement = Statements.rollback().transaction().to().savePoint("savepointname");

    /* Then */
    assertThat(toSql(statement), is("ROLLBACK TRANSACTION TO SAVEPOINT savepointname"));
  }
}
