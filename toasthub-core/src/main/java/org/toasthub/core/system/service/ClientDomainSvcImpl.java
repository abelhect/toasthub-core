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

package org.toasthub.core.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.toasthub.core.common.UtilSvc;
import org.toasthub.core.general.handler.ServiceProcessor;
import org.toasthub.core.general.model.GlobalConstant;
import org.toasthub.core.general.model.RestRequest;
import org.toasthub.core.general.model.RestResponse;
import org.toasthub.core.system.model.ClientDomain;
import org.toasthub.core.system.repository.ClientDomainDao;

@Service("ClientDomainSvc")
public class ClientDomainSvcImpl implements ServiceProcessor, ClientDomainSvc {

	@Autowired
	@Qualifier("ClientDomainDao")
	protected ClientDomainDao clientDomainDao;
	
	@Autowired 
	protected UtilSvc utilSvc;
	
	public void process(RestRequest request, RestResponse response) {
		String action = (String) request.getParams().get(GlobalConstant.ACTION);
		
		Long count = 0l;
		switch (action) {
		case "LIST":
			itemCount(request, response);
			count = (Long) response.getParam(GlobalConstant.ITEMCOUNT);
			if (count != null && count > 0){
				items(request, response);
			}
			break;
		case "SHOW":
			this.item(request, response);
			break;
		default:
			utilSvc.addStatus(RestResponse.INFO, RestResponse.ACTIONNOTEXIST, "Action not available", response);
			break;
		}
	}

	@Override
	public void itemCount(RestRequest request, RestResponse response) {
		try {
			clientDomainDao.itemCount(request, response);
		} catch (Exception e) {
			utilSvc.addStatus(RestResponse.ERROR, RestResponse.ACTIONFAILED, "Count failed", response);
			e.printStackTrace();
		}
	}
	
	@Override
	public void item(RestRequest request, RestResponse response) {
		try {
			clientDomainDao.item(request, response);
		} catch (Exception e) {
			utilSvc.addStatus(RestResponse.ERROR, RestResponse.ACTIONFAILED, "Item failed", response);
			e.printStackTrace();
		}
	}

	@Override
	public void items(RestRequest request, RestResponse response) {
		try {
			clientDomainDao.items(request, response);
		} catch (Exception e) {
			utilSvc.addStatus(RestResponse.ERROR, RestResponse.ACTIONFAILED, "List failed", response);
			e.printStackTrace();
		}
	}

	@Override
	public ClientDomain getClientDomain(String url) {
		ClientDomain cdomain = null;
		try {
			cdomain = clientDomainDao.getClientDomain(url);
		} catch (Exception e) {
			//eat error for now.
		}
		return cdomain;
	}

	@Override
	public List<ClientDomain> loadCache() {
		List<ClientDomain> clientDomains = null;
		try {
			clientDomains = clientDomainDao.loadCache();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientDomains;
	}


}
