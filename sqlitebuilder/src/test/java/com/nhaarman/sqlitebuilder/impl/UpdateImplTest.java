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

import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.Set;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

@SuppressWarnings("HardCodedStringLiteral")
public class UpdateImplTest {

  @Test
  public void prependTo_prependsProperSql() {
    /* Given */
    UpdateImpl update = new UpdateImpl("my_table");
    RawSqlBuilder builder = new RawSqlBuilderImpl();

    /* When */
    update.prependTo(builder);

    /* Then */
    assertThat(builder.toString(), is("UPDATE my_table"));
  }

  @Test
  public void previous_returnsNullValue() {
    /* Given */
    UpdateImpl update = new UpdateImpl("my_table");

    /* When */
    SqlPart result = update.previous();

    /* Then */
    assertThat(result, is(nullValue()));
  }

  @Test
  public void set_returnsNotNullValue() {
    /* Given */
    UpdateImpl update = new UpdateImpl("my_table");

    /* When */
    Set result = update.set("a=?");

    /* Then */
    assertThat(result, is(notNullValue()));
  }
}