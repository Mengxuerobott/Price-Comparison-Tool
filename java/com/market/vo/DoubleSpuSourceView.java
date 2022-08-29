package com.market.vo;

import com.market.entity.SpuSourceEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Elias on 2019/7/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoubleSpuSourceView {
    private SpuSourceEntity s1;
    private SpuSourceEntity s2;
}
