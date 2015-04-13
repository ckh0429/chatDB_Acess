package chintan.khetiya.sqlite.database;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 1503002 on 2015/4/13.
 */
public class AbChatContactModule {
    public void getContact(JSONObject jsob) throws JSONException {
        createLogin();
    }


    public JSONObject createLogin() throws JSONException {
        JSONObject jResult = null;
        jResult.put("exten", "");
        jResult.put("action", "login");
        jResult.put("checkcode", "");
        jResult.put("deviceid", "");
        jResult.put("os", "");
        jResult.put("osVersion", "");
        jResult.put("pntoken", "");
        jResult.put("mobile", "");
        jResult.put("maker", "");
        jResult.put("model", "");
        return jResult;
    }

}
