package com.gmail.sobol.i.stanislav.trendtest.dto;

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

    public List<RecDTO> getRecDTOs(RawDTO rawDTO) {
        final List<RecDTO> result = new ArrayList<>();
        final List<Object> rawRes = (List<Object>) rawDTO.data.get("results");

        for (Object item : rawRes) {
            final Map<String, Object> itemMap = (Map<String, Object>) item;

            final RecDTO recDTO = new RecDTO();

            recDTO.setId((int) (double) itemMap.get("id"));
            recDTO.setName((String) itemMap.get("name"));
            recDTO.setImageUrl((String) itemMap.get("image"));

            final List<MinPriceDTO> minPriceDTOs = new ArrayList<>();

            final List<Object> minPricesObjs = (List<Object>) itemMap.get("min_prices");
//            for (final Object minPricesObj : minPricesObjs) {
//
//                final List<Object> lo = (List<Object>) minPricesObj;
//
//                Log.d("SSS", "minPricesObj = " + minPricesObj );
//            }


            recDTO.setMinPriceDTOs(minPriceDTOs);

            recDTO.setAddress((String) itemMap.get("address")); // odd

            result.add(recDTO);
        }

        return result;
    }
}
