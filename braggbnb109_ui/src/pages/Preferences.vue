<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <preference-table
            v-if="preferences && preferences.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:preferences="preferences"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-preferences="getAllPreferences"
             >

            </preference-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PreferenceTable from "@/components/PreferenceTable";
import PreferenceService from "../services/PreferenceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PreferenceTable,
  },
  data() {
    return {
      preferences: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPreferences(sortBy='preferenceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PreferenceService.getAllPreferences(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.preferences.length) {
					this.preferences = response.data.preferences;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching preferences:", error);
        }
        
      } catch (error) {
        console.error("Error fetching preference details:", error);
      }
    },
  },
  mounted() {
    this.getAllPreferences();
  },
  created() {
    this.$root.$on('searchQueryForPreferencesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPreferences();
    })
  }
};
</script>
<style></style>
