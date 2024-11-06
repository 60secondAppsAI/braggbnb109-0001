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
            <propertyType-table
            v-if="propertyTypes && propertyTypes.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:propertyTypes="propertyTypes"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-property-types="getAllPropertyTypes"
             >

            </propertyType-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PropertyTypeTable from "@/components/PropertyTypeTable";
import PropertyTypeService from "../services/PropertyTypeService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PropertyTypeTable,
  },
  data() {
    return {
      propertyTypes: [],
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
    async getAllPropertyTypes(sortBy='propertyTypeId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PropertyTypeService.getAllPropertyTypes(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.propertyTypes.length) {
					this.propertyTypes = response.data.propertyTypes;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching propertyTypes:", error);
        }
        
      } catch (error) {
        console.error("Error fetching propertyType details:", error);
      }
    },
  },
  mounted() {
    this.getAllPropertyTypes();
  },
  created() {
    this.$root.$on('searchQueryForPropertyTypesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPropertyTypes();
    })
  }
};
</script>
<style></style>
