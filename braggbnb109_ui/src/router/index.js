import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Propertys from  '@/pages/Propertys.vue';
import PropertyDetail from  '@/pages/PropertyDetail.vue';
import Hosts from  '@/pages/Hosts.vue';
import HostDetail from  '@/pages/HostDetail.vue';
import Guests from  '@/pages/Guests.vue';
import GuestDetail from  '@/pages/GuestDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Amenitys from  '@/pages/Amenitys.vue';
import AmenityDetail from  '@/pages/AmenityDetail.vue';
import Photos from  '@/pages/Photos.vue';
import PhotoDetail from  '@/pages/PhotoDetail.vue';
import Cancellations from  '@/pages/Cancellations.vue';
import CancellationDetail from  '@/pages/CancellationDetail.vue';
import Availabilitys from  '@/pages/Availabilitys.vue';
import AvailabilityDetail from  '@/pages/AvailabilityDetail.vue';
import Locations from  '@/pages/Locations.vue';
import LocationDetail from  '@/pages/LocationDetail.vue';
import Transactions from  '@/pages/Transactions.vue';
import TransactionDetail from  '@/pages/TransactionDetail.vue';
import Messages from  '@/pages/Messages.vue';
import MessageDetail from  '@/pages/MessageDetail.vue';
import SupportTickets from  '@/pages/SupportTickets.vue';
import SupportTicketDetail from  '@/pages/SupportTicketDetail.vue';
import PropertyTypes from  '@/pages/PropertyTypes.vue';
import PropertyTypeDetail from  '@/pages/PropertyTypeDetail.vue';
import Rules from  '@/pages/Rules.vue';
import RuleDetail from  '@/pages/RuleDetail.vue';
import Preferences from  '@/pages/Preferences.vue';
import PreferenceDetail from  '@/pages/PreferenceDetail.vue';
import Offers from  '@/pages/Offers.vue';
import OfferDetail from  '@/pages/OfferDetail.vue';
import Experiences from  '@/pages/Experiences.vue';
import ExperienceDetail from  '@/pages/ExperienceDetail.vue';
import SecurityDeposits from  '@/pages/SecurityDeposits.vue';
import SecurityDepositDetail from  '@/pages/SecurityDepositDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/propertys',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/propertys',
		name: 'Propertys',
		layout: DefaultLayout,
		component: Propertys,
	},
	{
	    path: '/property/:propertyId', 
	    name: 'PropertyDetail',
		layout: DefaultLayout,
	    component: PropertyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/hosts',
		name: 'Hosts',
		layout: DefaultLayout,
		component: Hosts,
	},
	{
	    path: '/host/:hostId', 
	    name: 'HostDetail',
		layout: DefaultLayout,
	    component: HostDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/guests',
		name: 'Guests',
		layout: DefaultLayout,
		component: Guests,
	},
	{
	    path: '/guest/:guestId', 
	    name: 'GuestDetail',
		layout: DefaultLayout,
	    component: GuestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/amenitys',
		name: 'Amenitys',
		layout: DefaultLayout,
		component: Amenitys,
	},
	{
	    path: '/amenity/:amenityId', 
	    name: 'AmenityDetail',
		layout: DefaultLayout,
	    component: AmenityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/photos',
		name: 'Photos',
		layout: DefaultLayout,
		component: Photos,
	},
	{
	    path: '/photo/:photoId', 
	    name: 'PhotoDetail',
		layout: DefaultLayout,
	    component: PhotoDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cancellations',
		name: 'Cancellations',
		layout: DefaultLayout,
		component: Cancellations,
	},
	{
	    path: '/cancellation/:cancellationId', 
	    name: 'CancellationDetail',
		layout: DefaultLayout,
	    component: CancellationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/availabilitys',
		name: 'Availabilitys',
		layout: DefaultLayout,
		component: Availabilitys,
	},
	{
	    path: '/availability/:availabilityId', 
	    name: 'AvailabilityDetail',
		layout: DefaultLayout,
	    component: AvailabilityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/locations',
		name: 'Locations',
		layout: DefaultLayout,
		component: Locations,
	},
	{
	    path: '/location/:locationId', 
	    name: 'LocationDetail',
		layout: DefaultLayout,
	    component: LocationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/transactions',
		name: 'Transactions',
		layout: DefaultLayout,
		component: Transactions,
	},
	{
	    path: '/transaction/:transactionId', 
	    name: 'TransactionDetail',
		layout: DefaultLayout,
	    component: TransactionDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/messages',
		name: 'Messages',
		layout: DefaultLayout,
		component: Messages,
	},
	{
	    path: '/message/:messageId', 
	    name: 'MessageDetail',
		layout: DefaultLayout,
	    component: MessageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/supportTickets',
		name: 'SupportTickets',
		layout: DefaultLayout,
		component: SupportTickets,
	},
	{
	    path: '/supportTicket/:supportTicketId', 
	    name: 'SupportTicketDetail',
		layout: DefaultLayout,
	    component: SupportTicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/propertyTypes',
		name: 'PropertyTypes',
		layout: DefaultLayout,
		component: PropertyTypes,
	},
	{
	    path: '/propertyType/:propertyTypeId', 
	    name: 'PropertyTypeDetail',
		layout: DefaultLayout,
	    component: PropertyTypeDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/rules',
		name: 'Rules',
		layout: DefaultLayout,
		component: Rules,
	},
	{
	    path: '/rule/:ruleId', 
	    name: 'RuleDetail',
		layout: DefaultLayout,
	    component: RuleDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/preferences',
		name: 'Preferences',
		layout: DefaultLayout,
		component: Preferences,
	},
	{
	    path: '/preference/:preferenceId', 
	    name: 'PreferenceDetail',
		layout: DefaultLayout,
	    component: PreferenceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/offers',
		name: 'Offers',
		layout: DefaultLayout,
		component: Offers,
	},
	{
	    path: '/offer/:offerId', 
	    name: 'OfferDetail',
		layout: DefaultLayout,
	    component: OfferDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/experiences',
		name: 'Experiences',
		layout: DefaultLayout,
		component: Experiences,
	},
	{
	    path: '/experience/:experienceId', 
	    name: 'ExperienceDetail',
		layout: DefaultLayout,
	    component: ExperienceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/securityDeposits',
		name: 'SecurityDeposits',
		layout: DefaultLayout,
		component: SecurityDeposits,
	},
	{
	    path: '/securityDeposit/:securityDepositId', 
	    name: 'SecurityDepositDetail',
		layout: DefaultLayout,
	    component: SecurityDepositDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
