import http from "../http-common"; 

class PropertyTypeService {
  getAllPropertyTypes(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/propertyType/propertyTypes`, searchDTO);
  }

  get(propertyTypeId) {
    return this.getRequest(`/propertyType/${propertyTypeId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/propertyType?field=${matchData}`, null);
  }

  addPropertyType(data) {
    return http.post("/propertyType/addPropertyType", data);
  }

  update(data) {
  	return http.post("/propertyType/updatePropertyType", data);
  }
  
  uploadImage(data,propertyTypeId) {
  	return http.postForm("/propertyType/uploadImage/"+propertyTypeId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new PropertyTypeService();
