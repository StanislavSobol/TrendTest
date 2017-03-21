package com.gmail.sobol.i.stanislav.trendtest.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by VZ on 17.03.2017.
 */

@Getter
@Setter
@ToString
public class RecDTO {
    private int id;

    private String name = "";
    private String deadline = "";
    private String imageUrl = "";

    private List<MinPriceDTO> minPriceDTOs = new ArrayList<>();

    private String address = "";
    private String builder = "";

    private List<SubwayDTO> subwayDTOs = new ArrayList<>();
}
