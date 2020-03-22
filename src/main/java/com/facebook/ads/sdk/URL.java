/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.facebook.ads.utils.HttpMethods;
import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class URL extends APINode {
  @SerializedName("development_instant_article")
  private InstantArticle mDevelopmentInstantArticle = null;
  @SerializedName("engagement")
  private Object mEngagement = null;
  @SerializedName("id")
  private String mId = null;
  @SerializedName("instant_article")
  private InstantArticle mInstantArticle = null;
  @SerializedName("og_object")
  private Object mOgObject = null;
  @SerializedName("ownership_permissions")
  private Object mOwnershipPermissions = null;
  @SerializedName("scopes")
  private Object mScopes = null;
  protected static Gson gson = null;

  URL() {
  }

  public URL(Long id, APIContext context) {
    this(id.toString(), context);
  }

  public URL(String id, APIContext context) {
    this.mId = id;

    this.context = context;
  }

  public URL fetch() throws APIException{
    URL newInstance = fetchById(this.getPrefixedId().toString(), this.context);
    this.copyFrom(newInstance);
    return this;
  }

  public static URL fetchById(Long id, APIContext context) throws APIException {
    return fetchById(id.toString(), context);
  }

  public static ListenableFuture<URL> fetchByIdAsync(Long id, APIContext context) throws APIException {
    return fetchByIdAsync(id.toString(), context);
  }

  public static URL fetchById(String id, APIContext context) throws APIException {
    return
      new APIRequestGet(id, context)
      .requestAllFields()
      .execute();
  }

  public static ListenableFuture<URL> fetchByIdAsync(String id, APIContext context) throws APIException {
    return
      new APIRequestGet(id, context)
      .requestAllFields()
      .executeAsync();
  }

  public static APINodeList<URL> fetchByIds(List<String> ids, List<String> fields, APIContext context) throws APIException {
    return (APINodeList<URL>)(
      new APIRequest<URL>(context, "", "/", HttpMethods.GET, URL.getParser())
        .setParam("ids", APIRequest.joinStringList(ids))
        .requestFields(fields)
        .execute()
    );
  }

  public static ListenableFuture<APINodeList<URL>> fetchByIdsAsync(List<String> ids, List<String> fields, APIContext context) throws APIException {
    return
      new APIRequest(context, "", "/", HttpMethods.GET, URL.getParser())
        .setParam("ids", APIRequest.joinStringList(ids))
        .requestFields(fields)
        .executeAsyncBase();
  }

  private String getPrefixedId() {
    return getId();
  }

  public String getId() {
    return getFieldId().toString();
  }
  public static URL loadJSON(String json, APIContext context, String header) {
    URL url = getGson().fromJson(json, URL.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(url.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      }
    }
    url.context = context;
    url.rawValue = json;
    url.header = header;
    return url;
  }

  public static APINodeList<URL> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
    APINodeList<URL> urls = new APINodeList<URL>(request, json, header);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          urls.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
        };
        return urls;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                urls.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            urls.setPaging(previous, next);
            if (context.hasAppSecret()) {
              urls.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              urls.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  urls.add(loadJSON(entry.getValue().toString(), context, header));
                }
                break;
              }
            }
            if (!isRedownload) {
              urls.add(loadJSON(obj.toString(), context, header));
            }
          }
          return urls;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              urls.add(loadJSON(entry.getValue().toString(), context, header));
          }
          return urls;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              urls.add(loadJSON(value.toString(), context, header));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return urls;
          }

          // Sixth, check if it's pure JsonObject
          urls.clear();
          urls.add(loadJSON(json, context, header));
          return urls;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }

  public APIRequestGet get() {
    return new APIRequestGet(this.getPrefixedId().toString(), context);
  }

  public APIRequestUpdate update() {
    return new APIRequestUpdate(this.getPrefixedId().toString(), context);
  }


  public InstantArticle getFieldDevelopmentInstantArticle() {
    if (mDevelopmentInstantArticle != null) {
      mDevelopmentInstantArticle.context = getContext();
    }
    return mDevelopmentInstantArticle;
  }

  public Object getFieldEngagement() {
    return mEngagement;
  }

  public String getFieldId() {
    return mId;
  }

  public InstantArticle getFieldInstantArticle() {
    if (mInstantArticle != null) {
      mInstantArticle.context = getContext();
    }
    return mInstantArticle;
  }

  public Object getFieldOgObject() {
    return mOgObject;
  }

  public Object getFieldOwnershipPermissions() {
    return mOwnershipPermissions;
  }

  public Object getFieldScopes() {
    return mScopes;
  }



  public static class APIRequestGet extends APIRequest<URL> {

    URL lastResponse = null;
    @Override
    public URL getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
    };

    public static final String[] FIELDS = {
      "development_instant_article",
      "engagement",
      "id",
      "instant_article",
      "og_object",
      "ownership_permissions",
      "scopes",
    };

    @Override
    public URL parseResponse(String response, String header) throws APIException {
      return URL.parseResponse(response, getContext(), this, header).head();
    }

    @Override
    public URL execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public URL execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(), rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<URL> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<URL> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, URL>() {
           public URL apply(ResponseWrapper result) {
             try {
               return APIRequestGet.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestGet(String nodeId, APIContext context) {
      super(context, nodeId, "/", HttpMethods.GET, Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestGet setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestGet setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestGet requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestGet requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestGet requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestGet requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestGet requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

    public APIRequestGet requestDevelopmentInstantArticleField () {
      return this.requestDevelopmentInstantArticleField(true);
    }
    public APIRequestGet requestDevelopmentInstantArticleField (boolean value) {
      this.requestField("development_instant_article", value);
      return this;
    }
    public APIRequestGet requestEngagementField () {
      return this.requestEngagementField(true);
    }
    public APIRequestGet requestEngagementField (boolean value) {
      this.requestField("engagement", value);
      return this;
    }
    public APIRequestGet requestIdField () {
      return this.requestIdField(true);
    }
    public APIRequestGet requestIdField (boolean value) {
      this.requestField("id", value);
      return this;
    }
    public APIRequestGet requestInstantArticleField () {
      return this.requestInstantArticleField(true);
    }
    public APIRequestGet requestInstantArticleField (boolean value) {
      this.requestField("instant_article", value);
      return this;
    }
    public APIRequestGet requestOgObjectField () {
      return this.requestOgObjectField(true);
    }
    public APIRequestGet requestOgObjectField (boolean value) {
      this.requestField("og_object", value);
      return this;
    }
    public APIRequestGet requestOwnershipPermissionsField () {
      return this.requestOwnershipPermissionsField(true);
    }
    public APIRequestGet requestOwnershipPermissionsField (boolean value) {
      this.requestField("ownership_permissions", value);
      return this;
    }
    public APIRequestGet requestScopesField () {
      return this.requestScopesField(true);
    }
    public APIRequestGet requestScopesField (boolean value) {
      this.requestField("scopes", value);
      return this;
    }
  }

  public static class APIRequestUpdate extends APIRequest<URL> {

    URL lastResponse = null;
    @Override
    public URL getLastResponse() {
      return lastResponse;
    }
    public static final String[] PARAMS = {
      "blacklist",
      "hmac",
      "locale",
      "scopes",
      "ts",
    };

    public static final String[] FIELDS = {
    };

    @Override
    public URL parseResponse(String response, String header) throws APIException {
      return URL.parseResponse(response, getContext(), this, header).head();
    }

    @Override
    public URL execute() throws APIException {
      return execute(new HashMap<String, Object>());
    }

    @Override
    public URL execute(Map<String, Object> extraParams) throws APIException {
      ResponseWrapper rw = executeInternal(extraParams);
      lastResponse = parseResponse(rw.getBody(), rw.getHeader());
      return lastResponse;
    }

    public ListenableFuture<URL> executeAsync() throws APIException {
      return executeAsync(new HashMap<String, Object>());
    };

    public ListenableFuture<URL> executeAsync(Map<String, Object> extraParams) throws APIException {
      return Futures.transform(
        executeAsyncInternal(extraParams),
        new Function<ResponseWrapper, URL>() {
           public URL apply(ResponseWrapper result) {
             try {
               return APIRequestUpdate.this.parseResponse(result.getBody(), result.getHeader());
             } catch (Exception e) {
               throw new RuntimeException(e);
             }
           }
         }
      );
    };

    public APIRequestUpdate(String nodeId, APIContext context) {
      super(context, nodeId, "/", HttpMethods.POST, Arrays.asList(PARAMS));
    }

    @Override
    public APIRequestUpdate setParam(String param, Object value) {
      setParamInternal(param, value);
      return this;
    }

    @Override
    public APIRequestUpdate setParams(Map<String, Object> params) {
      setParamsInternal(params);
      return this;
    }


    public APIRequestUpdate setBlacklist (Boolean blacklist) {
      this.setParam("blacklist", blacklist);
      return this;
    }
    public APIRequestUpdate setBlacklist (String blacklist) {
      this.setParam("blacklist", blacklist);
      return this;
    }

    public APIRequestUpdate setHmac (String hmac) {
      this.setParam("hmac", hmac);
      return this;
    }

    public APIRequestUpdate setLocale (JsonArray locale) {
      this.setParam("locale", locale);
      return this;
    }
    public APIRequestUpdate setLocale (String locale) {
      this.setParam("locale", locale);
      return this;
    }

    public APIRequestUpdate setScopes (List<URL.EnumScopes> scopes) {
      this.setParam("scopes", scopes);
      return this;
    }
    public APIRequestUpdate setScopes (String scopes) {
      this.setParam("scopes", scopes);
      return this;
    }

    public APIRequestUpdate setTs (String ts) {
      this.setParam("ts", ts);
      return this;
    }

    public APIRequestUpdate requestAllFields () {
      return this.requestAllFields(true);
    }

    public APIRequestUpdate requestAllFields (boolean value) {
      for (String field : FIELDS) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestUpdate requestFields (List<String> fields) {
      return this.requestFields(fields, true);
    }

    @Override
    public APIRequestUpdate requestFields (List<String> fields, boolean value) {
      for (String field : fields) {
        this.requestField(field, value);
      }
      return this;
    }

    @Override
    public APIRequestUpdate requestField (String field) {
      this.requestField(field, true);
      return this;
    }

    @Override
    public APIRequestUpdate requestField (String field, boolean value) {
      this.requestFieldInternal(field, value);
      return this;
    }

  }

  public static enum EnumScopes {
      @SerializedName("NEWS_TAB")
      VALUE_NEWS_TAB("NEWS_TAB"),
      @SerializedName("NEWS_TAB_DEV_ENV")
      VALUE_NEWS_TAB_DEV_ENV("NEWS_TAB_DEV_ENV"),
      ;

      private String value;

      private EnumScopes(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
  }


  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public URL copyFrom(URL instance) {
    this.mDevelopmentInstantArticle = instance.mDevelopmentInstantArticle;
    this.mEngagement = instance.mEngagement;
    this.mId = instance.mId;
    this.mInstantArticle = instance.mInstantArticle;
    this.mOgObject = instance.mOgObject;
    this.mOwnershipPermissions = instance.mOwnershipPermissions;
    this.mScopes = instance.mScopes;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<URL> getParser() {
    return new APIRequest.ResponseParser<URL>() {
      public APINodeList<URL> parseResponse(String response, APIContext context, APIRequest<URL> request, String header) throws MalformedResponseException {
        return URL.parseResponse(response, context, request, header);
      }
    };
  }
}
