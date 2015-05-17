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
import com.nhaarman.sqlitebuilder.Select;
import com.nhaarman.sqlitebuilder.SelectFrom;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SelectImpl extends BaseSqlPart implements Select {

  @NotNull
  private final String[] mColumns;

  SelectImpl(@NotNull final String... columns) {
    mColumns = columns.length == 0 ? new String[] {"*"} : columns;
  }

  @NotNull
  @Override
  public SelectFrom from(@NotNull final String tableName) {
    return new SelectFromImpl(tableName, this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    for (int i = mColumns.length - 1; i >= 0; i--) {
      builder.prepend(mColumns[i]);
      if (i > 0) {
        builder.prepend(',');
      }
    }

    builder.prepend("SELECT ");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return null;
  }
}
