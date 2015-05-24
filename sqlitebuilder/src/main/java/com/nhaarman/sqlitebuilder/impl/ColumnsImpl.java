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

import com.nhaarman.sqlitebuilder.CreateColumns;
import com.nhaarman.sqlitebuilder.FinishedColumn;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import com.nhaarman.sqlitebuilder.WithoutRowId;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class ColumnsImpl extends BaseFinishedStatement implements CreateColumns {

  @NotNull
  private final FinishedColumn[] mColumns;

  @NotNull
  private final SqlPart mPrevious;

  ColumnsImpl(@NotNull final FinishedColumn[] columns, @NotNull final SqlPart previous) {
    mColumns = columns;
    mPrevious = previous;
  }

  @NotNull
  @Override
  public WithoutRowId withoutRowId() {
    return new WithoutRowIdImpl(this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend(')');

    for (int i = mColumns.length - 1; i >= 0; i--) {
      prependColumn(builder, mColumns[i]);

      if (i > 0) {
        builder.prepend(',');
      }
    }

    builder.prepend('(');
  }

  private void prependColumn(@NotNull final RawSqlBuilder builder, @NotNull final FinishedColumn column) {
    for (Iterator<SqlPart> iterator = column.iterator(); iterator.hasNext(); ) {
      SqlPart sqlPart = iterator.next();
      sqlPart.prependTo(builder);
      if (iterator.hasNext()) {
        builder.prepend(' ');
      }
    }
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }
}
