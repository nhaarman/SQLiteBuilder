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

import com.nhaarman.sqlitebuilder.As;
import com.nhaarman.sqlitebuilder.FinishedSelectStatement;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class AsImpl extends BaseFinishedStatement implements As {

  @NotNull
  private final FinishedSelectStatement mSelectStatement;

  @NotNull
  private final SqlPart mPrevious;

  AsImpl(@NotNull final FinishedSelectStatement selectStatement, @NotNull final SqlPart previous) {
    mSelectStatement = selectStatement;
    mPrevious = previous;
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    for (SqlPart sqlPart : mSelectStatement) {
      sqlPart.prependTo(builder);
      builder.prepend(' ');
    }

    builder.prepend("AS");
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return mPrevious;
  }
}
