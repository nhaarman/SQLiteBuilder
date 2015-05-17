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
import com.nhaarman.sqlitebuilder.UpdateValues;
import com.nhaarman.sqlitebuilder.UpdateWhere;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class UpdateValuesImpl extends BaseFinishedUpdateStatement implements UpdateValues {

  @NotNull
  private final Object[] mValues;

  @NotNull
  private final SqlPart mPrevious;

  UpdateValuesImpl(@NotNull final Object[] values, @NotNull final SetImpl previous) {
    if (values.length != previous.getColumnCount()) {
      throw new IllegalArgumentException(String.format("Invalid number of values. Supplied %d columns and %d values.", previous.getColumnCount(), values.length));
    }
    mValues = values;
    mPrevious = previous;
  }

  UpdateValuesImpl(@NotNull final Object[] values, @NotNull final SqlPart previous) {
    mValues = values;
    mPrevious = previous;
  }

  @NotNull
  @Override
  public UpdateWhere where(@NotNull final String expression, @NotNull final Object... arguments) {
    return new UpdateWhereImpl(expression, arguments, this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    /* no-op */
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }

  @Nullable
  @Override
  public Object[] getArguments() {
    return mValues.clone();
  }
}
