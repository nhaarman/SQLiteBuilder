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

import static com.nhaarman.sqlitebuilder.impl.Statements.select;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.is;

public class SelectTest extends IntegrationTestBase {

  @Test
  public void selectStar() {
    /* When */
    FinishedStatement statement =
        select()
            .from("my_table");

    /* Then */
    assertThat(toSql(statement), is("SELECT * FROM my_table"));
    assertThat(retrieveArguments(statement), is(emptyArray()));
  }

  @Test
  public void selectColumns() {
    /* When */
    FinishedStatement statement =
        select("a", "b")
            .from("my_table");

    /* Then */
    assertThat(toSql(statement), is("SELECT a,b FROM my_table"));
    assertThat(retrieveArguments(statement), is(emptyArray()));
  }

  @Test
  public void selectFromWhereGroupByOrderByLimit() {
    /* When */
    FinishedStatement statement =
        select("a", "b")
            .from("my_table")
            .where("a=? AND b=?", 1, 2)
            .groupBy("a")
            .orderBy("a", "b")
            .limit(5);

    /* Then */
    assertThat(toSql(statement), is("SELECT a,b FROM my_table WHERE a=? AND b=? GROUP BY a ORDER BY a,b LIMIT 5"));
    assertThat(retrieveArguments(statement), is(arrayContaining((Object) 1, 2)));
  }
}
