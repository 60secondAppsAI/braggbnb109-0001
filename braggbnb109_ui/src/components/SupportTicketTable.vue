
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Support Tickets</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalSupportTickets = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalSupportTickets">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add SupportTicket</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="SupportTicketId" type="text" placeholder="Enter SupportTicketId" v-model="supportTicketToAdd.supportTicketId"></base-input>
  <base-input label="IssueDescription" type="text" placeholder="Enter IssueDescription" v-model="supportTicketToAdd.issueDescription"></base-input>
  <base-input label="ResolutionStatus" type="text" placeholder="Enter ResolutionStatus" v-model="supportTicketToAdd.resolutionStatus"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="supportTickets" :row-key="record => record.SupportTicketId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <SupportTicketPictureView :supportTickets="supportTickets" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import SupportTicketService from "../services/SupportTicketService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import SupportTicketPictureView from './SupportTicketPictureView.vue';


const supportTicketsColumns = [
  "supportTicketId",
  "year",
  "date",
  "competitionId",
  "supportTicketId"
]

export default {
  props: {
    supportTickets: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    SupportTicketPictureView
  },

  data() {
    return {
      modalSupportTickets: false,
        isTableView: true,

      columns: [
        {
          title: 'Support Ticket Id',
		dataIndex: 'supportTicketId',
          visible: true,
          scopedSlots: { customRender: 'supportTicketId' },
          sorter: true
          //sorter: (a, b) => a.supportTicketId - b.supportTicketId,
          //sorter: (a, b) => a.supportTicketId.localeCompare(b.supportTicketId),
        },
        {
          title: 'Issue Description',
		dataIndex: 'issueDescription',
          visible: true,
          scopedSlots: { customRender: 'issueDescription' },
          sorter: true
          //sorter: (a, b) => a.issueDescription - b.issueDescription,
          //sorter: (a, b) => a.issueDescription.localeCompare(b.issueDescription),
        },
        {
          title: 'Resolution Status',
		dataIndex: 'resolutionStatus',
          visible: true,
          scopedSlots: { customRender: 'resolutionStatus' },
          sorter: true
          //sorter: (a, b) => a.resolutionStatus - b.resolutionStatus,
          //sorter: (a, b) => a.resolutionStatus.localeCompare(b.resolutionStatus),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} supportTickets`,
      },

      supportTickets: [],
      supportTicketToAdd: {},

      supportTicketsTable: {
        columns: [...supportTicketsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'supportTicketId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderSupportTicketsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let supportTicketsTableData = [];
      for (let i = 0; i < this.supportTickets.length; i++) {
        supportTicketsTableData.push({
          id: i,
          supportTicketId: this.supportTickets[i].supportTicketId,
          year: this.supportTickets[i].year,
          date: this.supportTickets[i].date,
          competitionId: this.supportTickets[i].competitionId,
          supportTicketId: this.supportTickets[i].supportTicketId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-support-tickets',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToPropertyDetail(id) {
      this.$router.push({ name: 'PropertyDetail', params: { propertyId: id.toString() }})
    },
    routingToHostDetail(id) {
      this.$router.push({ name: 'HostDetail', params: { hostId: id.toString() }})
    },
    routingToGuestDetail(id) {
      this.$router.push({ name: 'GuestDetail', params: { guestId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToPaymentDetail(id) {
      this.$router.push({ name: 'PaymentDetail', params: { paymentId: id.toString() }})
    },
    routingToReviewDetail(id) {
      this.$router.push({ name: 'ReviewDetail', params: { reviewId: id.toString() }})
    },
    routingToAmenityDetail(id) {
      this.$router.push({ name: 'AmenityDetail', params: { amenityId: id.toString() }})
    },
    routingToPhotoDetail(id) {
      this.$router.push({ name: 'PhotoDetail', params: { photoId: id.toString() }})
    },
    routingToCancellationDetail(id) {
      this.$router.push({ name: 'CancellationDetail', params: { cancellationId: id.toString() }})
    },
    routingToAvailabilityDetail(id) {
      this.$router.push({ name: 'AvailabilityDetail', params: { availabilityId: id.toString() }})
    },
    routingToLocationDetail(id) {
      this.$router.push({ name: 'LocationDetail', params: { locationId: id.toString() }})
    },
    routingToTransactionDetail(id) {
      this.$router.push({ name: 'TransactionDetail', params: { transactionId: id.toString() }})
    },
    routingToMessageDetail(id) {
      this.$router.push({ name: 'MessageDetail', params: { messageId: id.toString() }})
    },
    routingToSupportTicketDetail(id) {
      this.$router.push({ name: 'SupportTicketDetail', params: { supportTicketId: id.toString() }})
    },
    routingToPropertyTypeDetail(id) {
      this.$router.push({ name: 'PropertyTypeDetail', params: { propertyTypeId: id.toString() }})
    },
    routingToRuleDetail(id) {
      this.$router.push({ name: 'RuleDetail', params: { ruleId: id.toString() }})
    },
    routingToPreferenceDetail(id) {
      this.$router.push({ name: 'PreferenceDetail', params: { preferenceId: id.toString() }})
    },
    routingToOfferDetail(id) {
      this.$router.push({ name: 'OfferDetail', params: { offerId: id.toString() }})
    },
    routingToExperienceDetail(id) {
      this.$router.push({ name: 'ExperienceDetail', params: { experienceId: id.toString() }})
    },
    routingToSecurityDepositDetail(id) {
      this.$router.push({ name: 'SecurityDepositDetail', params: { securityDepositId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForSupportTicketsChanged', this.searchQuery);
		//this.renderSupportTicketsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalSupportTickets = false;

      const currentDate = new Date().getTime();
      this.supportTicketToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.supportTicketToAdd);
      console.log(jsonData);
      
      const res = await SupportTicketService.addSupportTicket(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderSupportTicketsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
