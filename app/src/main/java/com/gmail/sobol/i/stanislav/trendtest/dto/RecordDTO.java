package com.gmail.sobol.i.stanislav.trendtest.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

import lombok.ToString;

/**
 * Created by VZ on 17.03.2017.
 */

@ToString
public class RecordDTO {
    @SerializedName("errors")
    public Object errors;

    @SerializedName("data")
    public Map<String, Object> data;


    static public RecDTO toRecDTO(RecordDTO recordDTO) {
//        final recordDTO.data.get("results");
        return null;
    }
}
