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

import static com.nhaarman.sqlitebuilder.impl.Statements.replace;

@SuppressWarnings("HardCodedStringLiteral")
public class ReplaceTest extends IntegrationTestBase {

  @Test
  public void replaceIntoDatabaseTable() {
    /* When */
    replace()
        .into("my_database", "my_table")
        .columns("a", "b")
        .values(1, "test")
        .executeOn(getStatementExecutor());

    /* Then */
    verifyStatementExecuted("REPLACE INTO my_database.my_table (a,b) VALUES (?,?)", 1, "test");
  }
}
