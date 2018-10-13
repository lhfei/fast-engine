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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
public class LineitemResource extends AbstractResource {
	
	@RequestMapping(value = "/lineitems", method = GET)
	public List<String> getFiles(@RequestParam Integer limit) throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT * FROM benchmark.lineitem LIMIT ?";
		List<String> result = new ArrayList<>();
		
		jdbcTemplate.query(sql, new Object[] {limit}, new RowMapper<List<String>>() {

			@Override
			public List<String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				while(rs.next()){
					result.add(rs.getString(1));
				}
				return result;
			}
		});
		
		return result;
	}
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
}
