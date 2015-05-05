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

import com.nhaarman.sqlitebuilder.FinishedSqlPart;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.StatementConverter;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

class StatementConverterImpl implements StatementConverter {

  private static final Pattern DOUBLE_SPACE = Pattern.compile("  ");

  private static final StatementConverterImpl INSTANCE = new StatementConverterImpl();

  private StatementConverterImpl() {
  }

  @NotNull
  static StatementConverterImpl getInstance() {
    return INSTANCE;
  }

  @NotNull
  private static Object[] combine(@NotNull final Object[] a1, @NotNull final Object[] a2) {
    Object[] results = new Object[a1.length + a2.length];

    System.arraycopy(a1, 0, results, 0, a1.length);
    System.arraycopy(a2, 0, results, a1.length, a2.length);

    return results;
  }

  @Override
  @NotNull
  public String toSql(@NotNull final FinishedSqlPart finishedSqlPart) {
    RawSqlBuilder rawSqlBuilder = new RawSqlBuilderImpl();

    for (Iterator<SqlPart> iterator = finishedSqlPart.iterator(); iterator.hasNext(); ) {
      SqlPart sqlPart = iterator.next();
      sqlPart.prependTo(rawSqlBuilder);
      if (iterator.hasNext()) {
        rawSqlBuilder.prepend(' ');
      }
    }

    return DOUBLE_SPACE.matcher(rawSqlBuilder.toString()).replaceAll(" ");
  }

  @Override
  @NotNull
  public Object[] retrieveArguments(@NotNull final FinishedSqlPart finishedSqlPart) {
    Object[] results = new Object[0];
    for (SqlPart sqlPart : finishedSqlPart) {
      Object[] arguments = sqlPart.getArguments();
      if (arguments != null) {
        results = combine(arguments, results);
      }
    }
    return results;
  }
}
