/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.engine.web.model;

import java.util.Collection;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 14, 2018
 */

public class TableMetaDataModel {

	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Collection<String> getColumnLabels() {
		return columnLabels;
	}
	public void setColumnLabels(Collection<String> columnLabels) {
		this.columnLabels = columnLabels;
	}
	public Collection<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(Collection<String> columnNames) {
		this.columnNames = columnNames;
	}
	private String schemaName;
	private String tableName;
	private Collection<String> columnLabels;
	private Collection<String> columnNames;
}
