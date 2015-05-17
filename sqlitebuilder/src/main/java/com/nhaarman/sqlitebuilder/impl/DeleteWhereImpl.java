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

import com.nhaarman.sqlitebuilder.DeleteWhere;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class DeleteWhereImpl extends BaseFinishedDeleteStatement implements DeleteWhere {

  @NotNull
  private final String mExpression;

  @NotNull
  private final Object[] mArguments;

  @NotNull
  private final SqlPart mPrevious;

  DeleteWhereImpl(@NotNull final String expression, @NotNull final Object[] arguments, @NotNull final SqlPart previous) {
    mExpression = expression;
    mArguments = arguments;
    mPrevious = previous;
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend(mExpression).prepend("WHERE ");
  }

  @Nullable
  @Override
  public Object[] getArguments() {
    return mArguments.clone();
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }
}
