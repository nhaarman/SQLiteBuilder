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

import com.nhaarman.sqlitebuilder.Into;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class ReplaceImplTest {

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    ReplaceImpl replace = new ReplaceImpl();
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    replace.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("REPLACE"));
  }

  @Test
  public void previous_returnsNullValue() {
    /* Given */
    ReplaceImpl replace = new ReplaceImpl();

    /* When */
    SqlPart result = replace.previous();

    /* Then */
    assertThat(result, is(nullValue()));
  }

  @Test
  public void intoTable_returnsNotNullValue() {
    /* Given */
    ReplaceImpl replace = new ReplaceImpl();

    /* When */
    Into result = replace.into("my_table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }

  @Test
  public void intoDatabaseTable_returnsNotNullValue() {
    /* Given */
    ReplaceImpl replace = new ReplaceImpl();

    /* When */
    Into result = replace.into("my_database", "my_table");

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}