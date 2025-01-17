/*
 * Copyright (C) 2016 The ToastHub Project
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

package org.toasthub.core.category;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.toasthub.core.common.BaseDaoImpl;
import org.toasthub.core.general.model.Category;
import org.toasthub.core.general.model.RestRequest;
import org.toasthub.core.general.model.RestResponse;


@Repository("CategoryDao")
@Transactional("TransactionManagerData")
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {

	//@Authorize
	public void save(RestRequest request, RestResponse response) throws Exception {
		Category category = (Category) request.getParam("category");
		EntityManager multi = entityManagerDataSvc.getInstance();

		multi.merge(category);
	}
	
}
