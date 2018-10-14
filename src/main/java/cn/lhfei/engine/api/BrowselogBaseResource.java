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

package cn.lhfei.engine.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created 10月 13, 2018
 */
@RestController
@RequestMapping("/")
public class BrowselogBaseResource extends AbstractResource {
	

/*DESCRIBE TABLE ck_flow_browselog_base_d

┌─name─────────────────┬─type──────────┬─default_type─┬─default_expression─┐
│ firstSourceName      │ String        │              │                    │
│ flagId               │ String        │              │                    │
│ browserUniqId        │ String        │              │                    │
│ dateTime             │ String        │              │                    │
│ secondSourceName     │ String        │              │                    │
│ flagUrl              │ String        │              │                    │
│ terminalId           │ String        │              │                    │
│ virtualId            │ Array(String) │              │                    │
│ firstSourceId        │ String        │              │                    │
│ userLogAcct          │ String        │              │                    │
│ thirdSourceId        │ String        │              │                    │
│ secondSourceId       │ String        │              │                    │
│ thirdIndId           │ String        │              │                    │
│ userSiteProvinceName │ String        │              │                    │
│ brandId              │ String        │              │                    │
│ shopId               │ String        │              │                    │
│ flagType             │ String        │              │                    │
│ flagName             │ String        │              │                    │
│ thirdSourceName      │ String        │              │                    │
│ sessionRt            │ Int64         │              │                    │
│ skuId                │ String        │              │                    │
│ flagValueStatus      │ String        │              │                    │
│ shopType             │ String        │              │                    │
└──────────────────────┴───────────────┴──────────────┴────────────────────┘

23 rows in set. Elapsed: 0.008 sec. */

	
	@RequestMapping(value = "/export", method = GET)
	public List<String> getFiles(@RequestParam Integer size) throws ClassNotFoundException, SQLException {
		List<String> result = new ArrayList<>();
		
		int start = 0;
		int steps = 0;
		int limit = 5000;
		StringBuilder sb = new StringBuilder("");
		String sql = "SELECT * FROM bc_online.ck_flow_browselog_base_d LIMIT ?, ?";
		
		steps = (size % limit == 0) ? (size / limit) : (size / limit + 1);
		
		
		for (int i = 0; i < steps; i++) {
			start = limit * i;
			
			jdbcTemplate.query(sql, new Object[] {start, limit}, new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					ResultSetMetaData meta = rs.getMetaData();
					while(rs.next()){
						sb.append(rs.getString(1));
						sb.append(",");
						sb.append(rs.getString(2));
						sb.append(",");
						sb.append(rs.getString(3));
						sb.append(",");
						sb.append(rs.getString(4));
						sb.append(",");
						sb.append(rs.getString(5));
						sb.append(",");
						sb.append(rs.getString(6));
						sb.append(",");
						sb.append(rs.getString(7));
						sb.append(",");
						
						sb.append(rs.getArray(8));
						sb.append(",");
						
						sb.append(rs.getString(9));
						sb.append(",");
						sb.append(rs.getString(10));
						sb.append(",");
						sb.append(rs.getString(11));
						sb.append(",");
						sb.append(rs.getString(12));
						sb.append(",");
						sb.append(rs.getString(13));
						sb.append(",");
						sb.append(rs.getString(14));
						sb.append(",");
						sb.append(rs.getString(15));
						sb.append(",");
						sb.append(rs.getString(16));
						sb.append(",");
						sb.append(rs.getString(17));
						sb.append(",");
						sb.append(rs.getString(18));
						sb.append(",");
						sb.append(rs.getString(19));
						sb.append(",");
						
						sb.append(rs.getLong(20));
						sb.append(",");
						
						sb.append(rs.getString(21));
						sb.append(",");
						sb.append(rs.getString(22));
						sb.append(",");
						sb.append(rs.getString(23));
						sb.append("\n");
						
					}
				}
				
			});
			
			try {
				this.write(sb.toString(), (i == steps - 1));
			} catch (IOException e) {
				LOG.error("Appent file has an error. Step is {}", i);
			}
			
		}
		
		
		
		
		return result;
	}
	
	public void write(String content, boolean isEnd) throws IOException {
		String fileName = "/export/app_workspaces/ck_flow_browselog_base_d.csv";
		
		//Specify the file name and path here
    	File file =new File(fileName);

    	/* This logic is to create the file if the
    	 * file is not already present
    	 */
    	if(!file.exists()){
    	   file.createNewFile();
    	}
    	
    	//Here true is to append the content to file
    	FileWriter fw = new FileWriter(file,true);
    	//BufferedWriter writer give better performance
    	BufferedWriter bw = new BufferedWriter(fw);
    	bw.write(content);
    	
    	//Closing BufferedWriter Stream
    	bw.flush();
		
    	if(isEnd) {
    		bw.close();
    	}
	}
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
}
