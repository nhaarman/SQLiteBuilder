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

package com.nhaarman.sqlitebuilder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Create extends SqlPart {

  @NotNull
  Temporary temp();

  @NotNull
  Temporary temporary();

  @NotNull
  CreateTable table(@NotNull final String tableName);

  @NotNull
  CreateTable table(@Nullable final String databaseName, @NotNull final String tableName);

  @NotNull
  CreateTableIfNotExists tableIfNotExists(@NotNull final String tableName);

  @NotNull
  CreateTableIfNotExists tableIfNotExists(@Nullable final String databaseName, @NotNull final String tableName);
}
