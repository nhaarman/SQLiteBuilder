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

import com.nhaarman.sqlitebuilder.Analyze;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class AnalyzeImpl extends BaseFinishedStatement implements Analyze {

  @Nullable
  private String mDatabaseName;

  @Nullable
  private String mTableOrIndexName;

  @Override
  public AnalyzeImpl database(@NotNull final String databaseName) {
    mDatabaseName = databaseName;
    return this;
  }

  @Override
  public AnalyzeImpl tableOrIndex(@NotNull final String tableOrIndexName) {
    mTableOrIndexName = tableOrIndexName;
    return this;
  }

  @Override
  public AnalyzeImpl databaseAndTableOrIndex(@NotNull final String databaseName, @NotNull final String tableOrIndexName) {
    mDatabaseName = databaseName;
    mTableOrIndexName = tableOrIndexName;
    return this;
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    if (mTableOrIndexName != null) {
      builder.prepend(mTableOrIndexName);
      builder.prepend(mDatabaseName == null ? ' ' : '.');
    }

    if (mDatabaseName != null) {
      builder.prepend(mDatabaseName).prepend(' ');
    }

    builder.prepend("ANALYZE");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return null;
  }
}
