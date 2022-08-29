package com.market.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Elias on 2019/4/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvgView {
    Long spuId;
    String spuName;
    Float averagePrice;
    Float minPrice;
    Float maxPrice;
    int year;
    int month;
    int day;
}
