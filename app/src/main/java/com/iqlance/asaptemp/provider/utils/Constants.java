package com.iqlance.asaptemp.provider.utils;

public interface Constants {
    boolean LOG_DEBUG = true;
    //intro screen
    String SKIP_INTRO = "SKIP_INTRO";
    String ISLOGIN = "ISLOGIN";
    String IS_SKIP_LOGIN = "IS_SKIP_LOGIN";
    String LOGINTOKEN = "LOGINTOKEN";//ic_placeholder-true 0-false
    int POPPINS_REGULAR =1,
            POPPINS_MEDIUM = 2,
            POPPINS_BOLD = 3,
            POPPINS_SEMIBOLD = 4,
            POPPINS_BLACK = 5;

    // for ImageSize.
    int REQ_WIDTH = 80;
    int REQ_HEIGHT = 80;

    String IS_PROVIDER = "IS_PROVIDER";
    String IS_FROM_FP = "IS_FROM_FP";

    //enter opt
    int OTPValidation = 1;
    int NORMAL = 2;

    //header key
    String DEVICE_TYPE = "Android";
    String DEVICE_ID = "DEVICE_ID";
    String SOCIAL_ID = "SOCIAL_ID";
    String SOCIAL_ID_TWITTER = "SOCIAL_ID_TWITTER";
    String LOGIN_TYPE_EMAIL = "Email";
    String LOGIN_TYPE_FACEBOOK = "Facebook";
    String LOGIN_TYPE_TWITTER = "Twitter";
    String CUSTOMER_TYPE = "Customer";
    String PROVIDER_TYPE = "Provider";
    String ACCESS_TOKEN = "ACCESS_TOKEN";
    String LOGGED_IN_USER_MODEL = "LOGGED_IN_USER_MODEL";
    String LOGGED_IN_USER_ID = "LOGGED_IN_USER_ID";
    String LOGGED_IN_USER_TYPE = "LOGGED_IN_USER_TYPE";
    String LOGGED_IN_USER_NAME = "LOGGED_IN_USER_NAME";
    String GUEST_USER_NAME = "Guest";
    String LOGGED_IN_USER_PROFILE_PHOTO = "LOGGED_IN_USER_PROFILE_PHOTO";
    String LOGGED_IN_USER_MOBILE = "LOGGED_IN_USER_MOBILE";
    String LOGGED_IN_USER_EMAIL = "LOGGED_IN_USER_EMAIL";
    String LOGGED_IN_USER_DOB = "LOGGED_IN_USER_DOB";
    String LOGGED_IN_USER_COUNTRY_CODE = "LOGGED_IN_USER_COUNTRY_CODE";
    String LOGGED_IN_USER_YEARS_OF_EXPERIENCE = "LOGGED_IN_USER_YEARS_OF_EXPERIENCE";
    String LOGGED_IN_USER_PROVIDER_PROFILE_URL = "LOGGED_IN_USER_PROVIDER_PROFILE_URL";
    String STATIC_API_KEY = "1989c1f81-7c55-4a6b-897c-0b8bdbee382a";
    int STATIC_USERID = -1;

    //ShredPref key
    String API_KEY = "API_KEY";


    //CHOOSE CATEGORY ID
    String SELECTED_SUB_CATEGORY_ID = "SELECTED_SUB_CATEGORY_ID";
    String SELECTED_SUB_CATEGORY_NAME = "SELECTED_SUB_CATEGORY_NAME";
    String SERVICE_PHOTO_LIST = "SERVICE_PHOTO_LIST";

    //job status
    String Pending = "Pending";
    String Accepted = "Accepted";
    String Rejected = "Rejected";
    String Completed = "Completed";

    //notification status
    String PaymentReceived = "PaymentReceived";
    String Invited = "Invited";
    String QuoteSent = "QuoteSent";
    String JobRequest = "JobRequest";
    String JobRequestAccepted = "JobRequestAccepted";
    String JobRequestApproved = "JobRequestApproved";
    String ProviderJobInitiated = "ProviderJobInitiated";
    String ProviderArrived = "ProviderArrived";
    String ProviderJobStarted = "ProviderJobStarted";
    String ProviderJobEnded = "ProviderJobEnded";
    String JobDeclined = "JobDeclined";

    //job status
    String None = "None";
    String InitiateJob = "InitiateJob"; //view provider detail detail
    String Arrived = "Arrived";
    String StartJob = "StartJob";
    String EndJob = "EndJob";
    String Review = "Review";

    //job priority
    String Normal = "Normal";
    String Emergency = "Emergency";

    //Firebase Table
    String Customer = "Customer";
    String Message = "Message";
    String Provider = "Provider";

    //sort by date
    String Today = "Today";
    String Yesterday = "Yesterday";
    String Last1Week = "Last1Week";
    String Last1Month = "Last1Month";
    String Last3Month = "Last3Month";
    String All = "All";

    // sort by
    String Nearest = "Nearest";
    String Latest = "Latest";
    String CostHighToLow = "CostHighToLow";
    String CostLowToHigh = "CostLowToHigh";

    //Profile pic send key for server
    String userAvatar = "userAvatar";
    String providerUserAvatar = "providerUserAvatar";
    String Server_image_path = "users/avatar/";

    //provider home
    String ACTION_REFRESH_HOME_REQUEST = "ACTION_REFRESH_HOME_REQUEST";
    String ACTIVE = "Active";

    int setInterval = (5 * 1000); // 10 seconds
    int setFastestInterval = (2 * 1000); // 5 seconds


}