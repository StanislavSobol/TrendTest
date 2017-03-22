package com.gmail.sobol.i.stanislav.trendtest.network;

import com.gmail.sobol.i.stanislav.trendtest.dto.RawDTO;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by VZ on 17.03.2017.
 */

public interface TrendApi {
    @GET("/v2/apartments/blocks/search")
    Observable<RawDTO> getData(
            @Query(value = "show_type") String showType, // 'list'
            @Query(value = "count") int count,
            @Query(value = "offset") int offset,
            @Query(value = "cache") boolean cache,
            @Query(value = "price_from") int priceFrom,
            @Query(value = "price_to") int priceTo);
}
