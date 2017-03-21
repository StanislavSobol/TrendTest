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
            recDTO.setDeadline((String) itemMap.get("deadline"));

            final List<MinPriceDTO> minPriceDTOs = new ArrayList<>();
            final List<Map<String, Object>> minPricesMap = (List<Map<String, Object>>) itemMap.get("min_prices");
            for (final Map<String, Object> minPricesPair : minPricesMap) {
                final MinPriceDTO minPriceDTO = new MinPriceDTO();
                minPriceDTO.setName((String) minPricesPair.get("rooms"));
                minPriceDTO.setPrice((double) minPricesPair.get("price"));
                minPriceDTOs.add(minPriceDTO);
            }
            recDTO.setMinPriceDTOs(minPriceDTOs);

            recDTO.setAddress((String) itemMap.get("address"));

            final Map<String, Object> builderMap = (Map<String, Object>) itemMap.get("builder");
            recDTO.setBuilder((String) builderMap.get("name"));

            final List<SubwayDTO> subwayDTOs = new ArrayList<>();
            final List<Map<String, Object>> subwaysMap = (List<Map<String, Object>>) itemMap.get("subways");
            for (final Map<String, Object> subwayPair : subwaysMap) {
                final SubwayDTO subwayDTO = new SubwayDTO();
                subwayDTO.setName((String) subwayPair.get("name"));
                subwayDTO.setDistTiming((double) subwayPair.get("distance_timing"));
                subwayDTO.setDistType((String) subwayPair.get("distance_type"));
                subwayDTOs.add(subwayDTO);
            }
            recDTO.setSubwayDTOs(subwayDTOs);

            result.add(recDTO);
        }

        return result;
    }
}
