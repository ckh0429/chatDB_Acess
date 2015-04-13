package chintan.khetiya.sqlite.database;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 1503002 on 2015/4/13.
 */
public class AbChatLoginModule {
    public void login(JSONObject jsob) throws JSONException {
        createLogin();
    }

    public void verifyPW(JSONObject jsob) throws JSONException {
        createVerifyPW();
    }

    public void startAccount(JSONObject jsob) throws JSONException {
        createStartAccount();
    }

    public void changePW(JSONObject jsob) throws JSONException {
        createChangePW();
    }

    public void sentForgetPWVerifyCode(JSONObject jsob) throws JSONException {
        createSentForgetPWVerifyCode();
    }

    public void forgetPW(JSONObject jsob) throws JSONException {
        createChangePW();
    }

    public void logout(JSONObject jsob) throws JSONException {
        createLogout();
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

    public JSONObject createVerifyPW() throws JSONException{
        JSONObject jResult = null;
        jResult.put("exten", "");
        jResult.put("action", "checkpwd");
        jResult.put("checkcode", "");
        return jResult;
    }

    public JSONObject createStartAccount() throws JSONException{
        JSONObject jResult = null;
        jResult.put("action", "enable");
        jResult.put("usedid", "");
        jResult.put("code", "");
        jResult.put("deviceid", "");
        return jResult;
    }

    public JSONObject createChangePW() throws JSONException{
        JSONObject jResult = null;
        jResult.put("action", "changepwd");
        jResult.put("usedid", "");
        jResult.put("oldpwd", "");
        jResult.put("newpwd", "");
        return jResult;
    }

    public JSONObject createSentForgetPWVerifyCode() throws JSONException{
        JSONObject jResult = null;
        jResult.put("action", "forgotpwd");
        jResult.put("usedid", "");
        return jResult;
    }
    public JSONObject createForgetPW() throws JSONException{
        JSONObject jResult = null;
        jResult.put("action", "resetpwd");
        jResult.put("usedid", "");
        jResult.put("code", "");
        jResult.put("newpwd", "");
        return jResult;
    }
    public JSONObject createLogout() throws JSONException{
        JSONObject jResult = null;
        jResult.put("action", "logout");
        return jResult;
    }
}
