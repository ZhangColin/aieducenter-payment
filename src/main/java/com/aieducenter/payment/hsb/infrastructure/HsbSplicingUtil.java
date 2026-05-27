package com.aieducenter.payment.hsb.infrastructure;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.util.SortedMap;
import java.util.TreeMap;

public class HsbSplicingUtil {

    public static String createSign(String json) {
        return createSign(json, false);
    }

    public static String createSign(String json, boolean isNotification) {
        String signStr = splicingSign(json, isNotification);
        if (signStr.endsWith("&")) {
            signStr = signStr.substring(0, signStr.length() - 1);
        }
        return signStr;
    }

    private static String splicingSign(String json, boolean isNotification) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        SortedMap<String, Object> sortedMap = new TreeMap<>();

        for (String key : jsonObject.keySet()) {
            Object value = jsonObject.get(key);

            if (shouldExclude(key, isNotification)) {
                continue;
            }

            if (value instanceof JSONArray jsonArray) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < jsonArray.size(); i++) {
                    sb.append(splicingSign(jsonArray.getJSONObject(i).toJSONString(), isNotification));
                }
                sortedMap.put(key, sb.toString());
            } else if (value instanceof JSONObject jsonObj) {
                String nestedSign = splicingSign(jsonObj.toJSONString(), isNotification);
                sortedMap.put(key, nestedSign);
            } else {
                String strValue = value == null ? "" : value.toString();
                if (!strValue.isBlank()) {
                    sortedMap.put(key, strValue);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (SortedMap.Entry<String, Object> entry : sortedMap.entrySet()) {
            result.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return result.toString();
    }

    private static boolean shouldExclude(String key, boolean isNotification) {
        String upperKey = key.toUpperCase();
        if ("SIGN_INF".equals(upperKey)) return true;
        if (!isNotification) {
            return "SVC_RSP_ST".equals(upperKey) || "SVC_RSP_CD".equals(upperKey) || "RSP_INF".equals(upperKey);
        }
        return false;
    }
}
