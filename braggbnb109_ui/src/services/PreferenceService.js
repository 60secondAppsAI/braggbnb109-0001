import http from "../http-common"; 

class PreferenceService {
  getAllPreferences(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/preference/preferences`, searchDTO);
  }

  get(preferenceId) {
    return this.getRequest(`/preference/${preferenceId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/preference?field=${matchData}`, null);
  }

  addPreference(data) {
    return http.post("/preference/addPreference", data);
  }

  update(data) {
  	return http.post("/preference/updatePreference", data);
  }
  
  uploadImage(data,preferenceId) {
  	return http.postForm("/preference/uploadImage/"+preferenceId, data);
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

export default new PreferenceService();
