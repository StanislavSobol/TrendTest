package com.gmail.sobol.i.stanislav.trendtest.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by VZ on 17.03.2017.
 * <p>
 * Параметризированный Bundle
 */

@Setter
@Getter
public class RequestDTO {
    int offset;
    int from;
    int to = Integer.MAX_VALUE;
}
