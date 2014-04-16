package demo.vicshady.coreshopping.app;


public class AppConstants {

	public interface URLS
	{
		/*
		 * Testing Environment
		 */
		public static final String BASE_URL =  "http://www.kada.in/secure_json/v11/getJson.php?";
		public static final String CATEGORY_URL =  "http://www.kada.in/secure_json/v11/getJson.php?method=showMainCategory";
		public static final String SUBCATEGORY_URL =  "http://www.kada.in/secure_json/v11/getJson.php?method=showSubCategory&intCategoryId=";
		public static final String PRODUCT_LIST_URL =  "http://www.kada.in/secure_json/v11/getJson.php?method=getSubCategory&catid=";
		public static final String PRODUCT_DETAIL_URL =  "http://www.kada.in/secure_json/v11/getJson.php?method=showProductDetails&intProductId=";
		public static final String PRODUCT_SEARCH_URL = "http://www.kada.in/secure_json/v11/getJson.php?method=showSearchResults&key=";
		public static final String PRODUCT_FEATURE_URL = "http://www.kada.in/secure_json/v11/getJson.php?method=";
		/*
		 * Production Environment
		 */
	}
	
	public interface JSONTAG
	{
		public interface CategoryTag
		{
			public static String CATEGORY_ID="catId";
			public static String CATEGORY_NAME="catName";
			public static String PARENT_CATEGORY_ID="parentId";
			public static String CATEGORY_IMAGE_PATH="imagePath";
		}
		public interface ProductDetailTag
		{
			public static String PRODUCT_ID="catId";
			public static String PRODUCT_NAME="catName";
			public static String PRODUCT_VERSION_ID="parentId";
		}
	}
	
	public interface RESPONSES
	{
		public interface LoginResponse
		{
			public static String STATUS="success";
			public static String VID="user_id";
			public static String USERNAME="email";
			public static String LINFO="info";
		}
		public interface QueryResponse
		{
	
			public static String QSTATUS="success";
			public static String QUID="question_id";
			public static String QRES="info";
		}
		public interface TimeTableResponse
		{
			public static String TSTATUS="success";
			public static String TUID="batch";
			public static String TINFO="info";
		}
	}
	public static final String NETWORK_NOT_AVAILABLE = "Network not available";
	public static final String EXTENSION = ".png";
	public static final String GCM_SENDER_ID = "494660405194";
	
	
	public static final boolean DEBUG = false;
	
	public static final String fontStyle = "fonts/RobotoRegular.ttf"; 
//	public static final String fontStyle = "fonts/RobotoCondensedRegular.ttf";
	
	public static final String res = "{\"success\":true,\"tables\":{\"service\":[\"1\",\"2\"],\"expense\":[\"1\",\"2\"],\"expense_image\":[\"1\",\"2\",\"3\",\"4\"],\"service_audio\":[\"1\",\"2\",\"3\"],\"location\":[\"1\",\"2\"]},\"lov\":{\"bank\":[\"ICICI\",\"HDFC\"],\"location\":[\"Lilavati\",\"Rehja\"],\"expense_category\":[\"Food Expense\",\"Office Expense\",\"Bill Payment\",\"Stationary\"]\"patient_type\":[\"Regular\",\"Occasional\"],\"payment_mode\":[\"Net Banking\",\"Cash\",\"Online Transfer\",\"Cheque\"],\"procedure\":[\"Procedure 1\",\"Procedure 2\"],\"referred_by\":[\"Jaykishan Parikh\",\"Mahendra Nagar\"],\"start_time\":[\"Morning\",\"Evening\"],\"surgery_level\":[\"Level 1\",\"Level 2\"],\"team_member\":[\"Rakesh Pratap\",\"Milan Shah\"],\"ward\":[\"General Ward\",\"Emergency Ward\"]}}";
	
	public static final String vendorPro = "[{\"pid\":\"175\",\"project\":\"Philips\",\"company\":\"Philips\"},{\"pid\":\"149\",\"project\":\"Vodafone UP\",\"company\":\"Vodafone\"},{\"pid\":\"148\",\"project\":\"Bajaj Allianz UP Phase 2\",\"company\":\"Bajaj Allianz\"},{\"pid\":\"142\",\"project\":\"Philips MP\",\"company\":\"Philips\"},{\"pid\":\"128\",\"project\":\"HDFC\",\"company\":\"HDFC\"},{\"pid\":\"122\",\"project\":\"Hindustan Unilever\",\"company\":\"Hindustan Unilever\"},{\"pid\":\"83\",\"project\":\"Makers-Raymond\",\"company\":\"Makers-Raymond\"},{\"pid\":\"79\",\"project\":\"HDFC\",\"company\":\"HDFC\"},{\"pid\":\"56\",\"project\":\"Vodafone\",\"company\":\"Vodafone\"},{\"pid\":\"50\",\"project\":\"CASA_Sarita\",\"company\":\"HDFC\"},{\"pid\":\"36\",\"project\":\"Vodafone\",\"company\":\"Vodafone\"},{\"pid\":\"35\",\"project\":\"Gold Loan_Sukirti\",\"company\":\"HDFC\"},{\"pid\":\"30\",\"project\":\"Gold Loan_Sarita\",\"company\":\"HDFC\"}]";
	public static final String preImg = "[{\"project\":\"Gold Loan_Sarita\",\"work_title\":\"Wall Painting\",\"image\":\"http:\\/\\/adwallz.co\\/admin\\/images\\/projects\\/allworks\\/1295257286DSC06495.JPG\",\"address\":\"HDFC Bank,Unnao\",\"city\":\"UNNAO\",\"state\":\"Uttar Pradesh\",\"size\":\"15.00 x 11.50\"},{\"project\":\"Gold Loan_Sarita\",\"work_title\":\"Wall Painting\",\"image\":\"http:\\/\\/adwallz.co\\/admin\\/images\\/projects\\/allworks\\/1295257396DSC06496.JPG\",\"address\":\"Unnao ,Near Lucknow Road.\",\"city\":\"UNNAO\",\"state\":\"Uttar Pradesh\",\"size\":\"26.00 x 8.50\"}]";
	public static final String proListByVendor = "[{\"pid\":\"148\",\"project_name\":\"Bajaj Allianz UP Phase 2\",\"client_name\":\"Bajaj Allianz\"},{\"pid\":\"50\",\"project_name\":\"CASA_Sarita\",\"client_name\":\"HDFC\"},{\"pid\":\"30\",\"project_name\":\"Gold Loan_Sarita\",\"client_name\":\"HDFC\"},{\"pid\":\"35\",\"project_name\":\"Gold Loan_Sukirti\",\"client_name\":\"HDFC\"},{\"pid\":\"79\",\"project_name\":\"HDFC\",\"client_name\":\"HDFC\"},{\"pid\":\"128\",\"project_name\":\"HDFC\",\"client_name\":\"HDFC\"},{\"pid\":\"122\",\"project_name\":\"Hindustan Unilever\",\"client_name\":\"Hindustan Unilever\"},{\"pid\":\"83\",\"project_name\":\"Makers-Raymond\",\"client_name\":\"Makers-Raymond\"},{\"pid\":\"175\",\"project_name\":\"Philips\",\"client_name\":\"Philips\"},{\"pid\":\"142\",\"project_name\":\"Philips MP\",\"client_name\":\"Philips\"},{\"pid\":\"36\",\"project_name\":\"Vodafone\",\"client_name\":\"Vodafone\"},{\"pid\":\"56\",\"project_name\":\"Vodafone\",\"client_name\":\"Vodafone\"},{\"pid\":\"149\",\"project_name\":\"Vodafone UP\",\"client_name\":\"Vodafone\"}]";
	public static final String projectsByVendorToAdd = "[{\"pid\":\"148\",\"project_name\":\"Bajaj Allianz UP Phase 2\",\"client_name\":\"Bajaj Allianz\"},{\"pid\":\"50\",\"project_name\":\"CASA_Sarita\",\"client_name\":\"HDFC\"},{\"pid\":\"30\",\"project_name\":\"Gold Loan_Sarita\",\"client_name\":\"HDFC\"},{\"pid\":\"35\",\"project_name\":\"Gold Loan_Sukirti\",\"client_name\":\"HDFC\"},{\"pid\":\"79\",\"project_name\":\"HDFC\",\"client_name\":\"HDFC\"},{\"pid\":\"128\",\"project_name\":\"HDFC\",\"client_name\":\"HDFC\"},{\"pid\":\"122\",\"project_name\":\"Hindustan Unilever\",\"client_name\":\"Hindustan Unilever\"},{\"pid\":\"83\",\"project_name\":\"Makers-Raymond\",\"client_name\":\"Makers-Raymond\"},{\"pid\":\"175\",\"project_name\":\"Philips\",\"client_name\":\"Philips\"},{\"pid\":\"142\",\"project_name\":\"Philips MP\",\"client_name\":\"Philips\"},{\"pid\":\"36\",\"project_name\":\"Vodafone\",\"client_name\":\"Vodafone\"},{\"pid\":\"56\",\"project_name\":\"Vodafone\",\"client_name\":\"Vodafone\"},{\"pid\":\"149\",\"project_name\":\"Vodafone UP\",\"client_name\":\"Vodafone\"}]";
	public static final String paintingType = "[\"highway\",\"wall\",\"shop\"]";
}
