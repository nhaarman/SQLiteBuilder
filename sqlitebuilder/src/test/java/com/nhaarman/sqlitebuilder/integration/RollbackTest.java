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

import static com.nhaarman.sqlitebuilder.impl.Statements.rollback;

@SuppressWarnings("HardCodedStringLiteral")
public class RollbackTest extends IntegrationTestBase {

  @Test
  public void testRollback() {
    /* When */
    rollback()
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("ROLLBACK");
  }

  @Test
  public void rollbackTransaction() {
    /* When */
    rollback().transaction()
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("ROLLBACK TRANSACTION");
  }

  @Test
  public void rollbackToSavepoint() {
    /* When */
    rollback().to().savePoint("savepointname")
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("ROLLBACK TO SAVEPOINT savepointname");
  }

  @Test
  public void rollbackTo() {
    /* When */
    rollback().to("savepointname")
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("ROLLBACK TO SAVEPOINT savepointname");
  }

  @Test
  public void rollbackTransactionTo() {
    /* When */
    rollback().transaction().to("savepointname")
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("ROLLBACK TRANSACTION TO SAVEPOINT savepointname");
  }

  @Test
  public void rollbackTransactionToSavepoint() {
    /* When */
    rollback().transaction().to().savePoint("savepointname")
        .executeOn(mStatementExecutor);

    /* Then */
    verifyStatementExecuted("ROLLBACK TRANSACTION TO SAVEPOINT savepointname");
  }
}
