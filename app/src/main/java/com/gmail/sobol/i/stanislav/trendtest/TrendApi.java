package com.gmail.sobol.i.stanislav.trendtest;

import com.gmail.sobol.i.stanislav.trendtest.dto.RawDTO;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by VZ on 17.03.2017.
 */

public interface TrendApi {
    //        http://api.trend-dev.ru/v2/apartments/blocks/search/
    @GET("/v2/apartments/blocks/search")
    Observable<RawDTO> getData(
            @Query(value = "show_type") String showType, // 'list'
            @Query(value = "count") int count,
            @Query(value = "offset") int offset,
            @Query(value = "cache") boolean cache,
            @Query(value = "price_from") int priceFrom,
            @Query(value = "price_to") int priceTo);
}


//http://api.trend-dev.ru/v2/apartments/blocks/search/?show_type=list&count=1&offset=0&cache=false&price_from=0&price_to=0

//        Принимаемые GET параметры: {
//        show_type: 'list', // тип вывода
//        count: 10, // кол-во показываемых ЖК на странице
//        offset: 0, // смещение кол-ва показываемых объектов
//        cache: false,
//        price_from: 0, // Цена от
//        price_to: 0 // Цена до
//        }
