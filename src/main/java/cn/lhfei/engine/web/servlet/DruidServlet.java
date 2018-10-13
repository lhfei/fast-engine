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

package cn.lhfei.engine.web.servlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @Created on Jun 28, 2018
 */
@WebServlet(
		urlPatterns = "/druid/*", initParams = { @WebInitParam(name = "allow", value = ""),
		@WebInitParam(name = "deny", value = ""),
		@WebInitParam(name = "loginUsername", value = "admin"),
		@WebInitParam(name = "loginPassword", value = "admin")
})
public class DruidServlet extends StatViewServlet {
	private static final long serialVersionUID = 8027953395786181836L;

}
