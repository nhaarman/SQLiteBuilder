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

import com.nhaarman.sqlitebuilder.Column;
import com.nhaarman.sqlitebuilder.ColumnBlob;
import com.nhaarman.sqlitebuilder.ColumnInteger;
import com.nhaarman.sqlitebuilder.ColumnReal;
import com.nhaarman.sqlitebuilder.ColumnText;
import com.nhaarman.sqlitebuilder.RawSqlBuilder;
import com.nhaarman.sqlitebuilder.SqlPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class ColumnImpl extends BaseFinishedSqlPart implements Column {

  @NotNull
  private final String mNewColumnName;

  ColumnImpl(@NotNull final String newColumnName) {
    mNewColumnName = newColumnName;
  }

  @Override
  public ColumnInteger integer() {
    return new ColumnIntegerImpl(this);
  }

  @Override
  public ColumnReal real() {
    return new ColumnRealImpl(this);
  }

  @Override
  public ColumnText text() {
    return new ColumnTextImpl(this);
  }

  @Override
  public ColumnBlob blob() {
    return new ColumnBlobImpl(this);
  }

  @Override
  public void prependTo(@NotNull final RawSqlBuilder builder) {
    builder.prepend(mNewColumnName);
  }

  @Nullable
  @Override
  public SqlPart previous() {
    return null;
  }
}
