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

import static com.nhaarman.sqlitebuilder.impl.Statements.select;

@SuppressWarnings("HardCodedStringLiteral")
public class SelectTest extends IntegrationTestBase {

  @Test
  public void selectStar() {
    /* When */
    select()
        .from("my_table")
        .executeOn(mStatementExecutor);

    /* Then */
    verifySelectStatementExecuted("SELECT * FROM my_table");
  }

  @Test
  public void selectColumns() {
    /* When */
    select("a", "b")
        .from("my_table")
        .executeOn(mStatementExecutor);

    /* Then */
    verifySelectStatementExecuted("SELECT a,b FROM my_table");
  }

  @Test
  public void selectFromWhereGroupByOrderByLimit() {
    /* When */
    select("a", "b")
        .from("my_table")
        .where("a=? AND b=?", 1, 2)
        .groupBy("a")
        .orderBy("a", "b")
        .limit(5)
        .executeOn(mStatementExecutor);

    /* Then */
    verifySelectStatementExecuted("SELECT a,b FROM my_table WHERE a=? AND b=? GROUP BY a ORDER BY a,b LIMIT 5", 1, 2);
  }
}
