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

import static com.nhaarman.sqlitebuilder.impl.Statements.update;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.is;

@SuppressWarnings("HardCodedStringLiteral")
public class SetTest extends IntegrationTestBase {

  @Test
  public void updateSetWhere() {
    /* When */
    FinishedStatement statement = update("my_table")
        .set("a")
        .values(1)
        .where("b=?", 2);

    /* Then */
    assertThat(toSql(statement), is("UPDATE my_table SET a=? WHERE b=?"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2)));
  }

  @Test
  public void updateSetWhereMultiple() {
     /* When */
    FinishedStatement statement = update("my_table")
        .set("a", "b")
        .values(1, 2)
        .where("c=? OR c=?", 3, 4);

    /* Then */
    assertThat(toSql(statement), is("UPDATE my_table SET a=?,b=? WHERE c=? OR c=?"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2, 3, 4)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void updateSetWhereMultiple_missingValues_throwsException() {
     /* When */
    update("my_table")
        .set("a", "b")
        .values(1)
        .where("c=? OR c=?", 3, 4);
  }
}
