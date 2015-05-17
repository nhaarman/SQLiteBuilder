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

package com.nhaarman.sqlitebuilder.impl;

import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.Values;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class ValuesImpl extends BaseFinishedInsertStatement implements Values {

  @NotNull
  private final Object[] mExpressions;

  @NotNull
  private final SqlPart mPrevious;

  ValuesImpl(@NotNull final Object[] expressions, @NotNull final InsertColumnsImpl previous) {
    if (expressions.length != previous.getColumnCount()) {
      throw new IllegalArgumentException(String.format("Invalid number of expressions. Supplied %d columns and %d expressions.", previous.getColumnCount(), expressions.length));
    }

    mExpressions = expressions;
    mPrevious = previous;
  }

  ValuesImpl(@NotNull final Object[] expressions, @NotNull final SqlPart previous) {
    mExpressions = expressions;
    mPrevious = previous;
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend(')');

    for (int i = mExpressions.length - 1; i >= 0; i--) {
      builder.prepend('?');
      if (i > 0) {
        builder.prepend(",");
      }
    }

    builder.prepend("VALUES (");
  }

  @Nullable
  @Override
  public Object[] getArguments() {
    return mExpressions.clone();
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }
}
