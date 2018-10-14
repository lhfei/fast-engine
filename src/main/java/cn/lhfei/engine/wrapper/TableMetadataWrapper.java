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

package cn.lhfei.engine.wrapper;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import cn.lhfei.engine.web.model.QueryResult;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 14, 2018
 */
public class TableMetadataWrapper {

	public static QueryResult warp(ResultSetMetaData metaData) throws SQLException {
		QueryResult queryResult = new QueryResult();
		int colsCount = metaData.getColumnCount();
		
		queryResult.getMetaData().setTableName(metaData.getTableName(0));
		queryResult.getMetaData().setSchemaName(metaData.getSchemaName(0));
		
		for(int i = 0; i < colsCount; i++) {
			queryResult.getMetaData().getColumnNames().add(metaData.getColumnName(i));
			queryResult.getMetaData().getColumnLabels().add(metaData.getColumnLabel(i));
		}
		
		return queryResult;
	
	}
}
