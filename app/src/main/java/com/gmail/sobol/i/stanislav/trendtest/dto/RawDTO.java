package com.gmail.sobol.i.stanislav.trendtest.dto;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.ToString;

/**
 * Created by VZ on 17.03.2017.
 */

@ToString
public class RawDTO {
    @SerializedName("errors")
    public Object errors;

    @SerializedName("data")
    public Map<String, Object> data;


    static public RecDTO toRecDTO(RawDTO rawDTO) {
//        final recordDTO.data.get("results");
        return null;
    }

    public List<RecDTO> getRecDTOs(RawDTO rawDTO) {
        final List<RecDTO> result = new ArrayList<>();

//        JSONArray array = (JSONArray) rawDTO.data.get("results");


        final List<Object> rawRes = (List<Object>) rawDTO.data.get("results");

        for (Object item : rawRes) {
            Map<String, Object> itemMap = (Map<String, Object>) item;
            Log.d("SSS", "item =" + item);

            final RecDTO recDTO = new RecDTO();

//            double d  = (double) itemMap.get("id");
            recDTO.setId((int) (double) itemMap.get("id"));
            recDTO.setName((String) itemMap.get("name"));
            recDTO.setAddress((String) itemMap.get("address"));
            recDTO.setImageUrl((String) itemMap.get("image"));

//            recDTO.setId(itemMap.get("id").getInt());

            result.add(recDTO);
        }

//        List<Map<String, String>> results = (List<Map<String, String>>) rawDTO.data.get("results");
        return result;
    }
}
