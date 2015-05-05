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
import com.nhaarman.sqlitebuilder.UpdateValues;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SetImpl extends BaseFinishedStatement implements Set {

  @NotNull
  private final String[] mColumnNames;

  @NotNull
  private final SqlPart mPrevious;

  SetImpl(@NotNull final String[] columnNames, @NotNull final SqlPart previous) {
    mColumnNames = columnNames;
    mPrevious = previous;
  }

  @NotNull
  @Override
  public UpdateValues values(@NotNull final Object... values) {
    //noinspection RedundantCast --  The cast is here for the overloaded Constructor.
    return new UpdateValuesImpl(values, (SetImpl) this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    for (int i = mColumnNames.length - 1; i >= 0; i--) {
      builder.prepend("=?").prepend(mColumnNames[i]);

      if (i > 0) {
        builder.prepend(',');
      }
    }

    builder.prepend("SET ");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }

  public int getColumnCount() {
    return mColumnNames.length;
  }
}
