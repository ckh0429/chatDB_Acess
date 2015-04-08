package chintan.khetiya.sqlite.database;

/**
 * Created by 1503002 on 2015/4/7.
 */
public class Table {
    // server user table name //
    public final static String USER_TABLE = "USER_TABLE";
    // server user table attribute //
    public final static String USER_ID = "USER_ID";
    public final static String USER_PW = "USER_PW";
    public final static String USER_NAME = "USER_NAME";
    public final static String USER_MOBILE = "USER_MOBILE";
    public final static String USER_EXT = "USER_EXT";
    public final static String USER_EMAIL = "USER_EMAIL";
    public final static String USER_DEPT = "USER_DEPT";
    public final static String USER_CONTACTS = "USER_CONTACTS";
    public final static String USER_INVITES = "USER_INVITES";
    public final static String USER_BE_INVITED = "USER_BE_INVITED";
    public final static String USER_GROUP = "USER_GROUP";
    public final static String USER_GROUP_BE_INVITED = "USER_GROUP_BE_INVITED";
    public final static String USER_IMEI = "USER_IMEI";
    public final static String USER_REGISTER_ID = "USER_REGISTER_ID";
    public final static String USER_PICTURE = "USER_PICTURE";
    public final static String USER_HOME_URI = "USER_HOME_URI";
    public final static String USER_DOMAIN = "USER_DOMAIN";
    
    // myTable name //
    public final static String MY_TABLE = "MY_TABLE";
    // myTable attribute //
    public final static String MY_ID = "MY_ID";
    public final static String MY_PW = "MY_PW";
    public final static String MY_NAME = "MY_NAME";
    public final static String MY_MOBILE = "MY_MOBILE";
    public final static String MY_EXT = "MY_EXT";
    public final static String MY_EMAIL = "MY_EMAIL";
    public final static String MY_DEPT = "MY_DEPT";
    public final static String MY_CONTACTS = "MY_CONTACTS";
    public final static String MY_INVITES = "MY_INVITES";
    public final static String MY_BE_INVITEd = "MY_BE_INVITEd";
    public final static String MY_GROUP = "MY_GROUP";
    public final static String MY_GROUP_BE_INVITEd = "MY_GROUP_BE_INVITEd";
    public final static String MY_IMEI = "MY_IMEI";
    public final static String MY_REGISTER_ID = "MY_REGISTER_ID";
    public final static String MY_PICTURE = "MY_PICTURE";
    public final static String MY_HOME_URI = "MY_HOME_URI";
    public final static String MY_DOMAIN = "MY_DOMAIN";

    // contactTable name //
    public final static String CONTACT_TABLE = "CONTACT_TABLE";
    // contactTable attribute //
    public final static String CONTACT_ID = "CONTACT_ID";
    public final static String CONTACT_NAME = "CONTACT_NAME";
    public final static String CONTACT_MOBILE = "CONTACT_MOBILE";
    public final static String CONTACT_EXTENSION = "CONTACT_EXTENSION";
    public final static String CONTACT_EMAIL = "CONTACT_EMAIL";
    public final static String CONTACT_TYPE = "CONTACT_TYPE";
    public final static String CONTACT_DEPT = "CONTACT_DEPT";
    public final static String CONTACT_HOME_URI = "CONTACT_HOME_URI";
    public final static String CONTACT_DOMAIN = "CONTACT_DOMAIN";
    public final static String IS_FAVORITE = "IS_FAVORITE";
    public final static String LAST_CHAT_CONTENT = "LAST_CHAT_CONTENT";
    public final static String LAST_CHAT_TIME = "LAST_CHAT_TIME";


    // groupTable name //
    public final static String GROUP_TABLE = "GROUP_TABLE";
    // groupTable attribute //
    public final static String GROUP_ID ="GROUP_ID";
    public final static String WHO_CREATE ="WHO_CREATE";
    public final static String GROUP_NAME ="GROUP_NAME";
    public final static String GROUP_MEMBERS ="GROUP_MEMBERS";
    public final static String GROUP_ADMINS ="GROUP_ADMINS";
    public final static String GROUP_NOT_ACCEPT ="GROUP_NOT_ACCEPT";
    public final static String GROUP_PIC ="GROUP_PIC";
    public final static String GROUP_LAST_CHAT_CONTENT ="GROUP_LAST_CHAT_CONTENT";
    public final static String GROUP_LAST_CHAT_TIME ="GROUP_LAST_CHAT_TIME";


    // table 4 name and attribute which display group meeting record //
    public final static String CHAT_TABLE = "CHAT_TABLE";
    public final static String CHAT_ID ="CHAT_ID";
    public final static String CHAT_CONTENT ="CHAT_CONTENT";
    public final static String CHAT_TIME ="CHAT_TIME";
    public final static String CHAT_STATUS ="CHAT_STATUS";
    public final static String CHAT_FROM_WHO ="CHAT_FROM_WHO";
    public final static String CHAT_TO_WHO ="CHAT_TO_WHO";
    public final static String IS_GROUP ="IS_GROUP";
    public final static String IS_POST_ID ="IS_POST_ID";


    // table 5 name and attribute which display group meeting record //
    public final static String POST_TABLE = "POST_TABLE";
    public final static String POST_ID ="POST_ID";
    public final static String POST_SUBJECT ="POST_SUBJECT";
    public final static String POST_CONTENT ="POST_CONTENT";
    public final static String POST_TIME ="POST_TIME";
    public final static String POST_STATUS ="POST_STATUS";
    public final static String POST_FROM_WHO ="POST_FROM_WHO";
    public final static String POST_TO_WHO ="POST_TO_WHO";


    // table 6 name and attribute which display group meeting record //
    public final static String MEETING_TABLE = "MEETING_TABLE";
    public final static String MEETING_ID ="MEETING_ID";
    public final static String MEETING_IS_OPEN ="MEETING_IS_OPEN";
    public final static String MEETING_SUBJECT ="MEETING_SUBJECT";
    public final static String MEETING_NOTES ="MEETING_NOTES";
    public final static String MEETING_DETAILS ="MEETING_DETAILS";
    public final static String MEETING_START_TIME ="MEETING_START_TIME";
    public final static String MEETING_END_TIME ="MEETING_END_TIME";
    public final static String MEETING_FROM_WHO ="MEETING_FROM_WHO";
    public final static String MEETING_TO_WHO ="MEETING_TO_WHO";
    public final static String MEETING_STATUS ="MEETING_STATUS";
    public final static String MEETING_WHO_ACCEPT ="MEETING_WHO_ACCEPT";
    public final static String MEETING_WHO_REJECT ="MEETING_WHO_REJECT";


}
