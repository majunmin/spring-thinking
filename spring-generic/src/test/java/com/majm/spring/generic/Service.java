package com.majm.spring.generic;

import java.util.List;
import java.util.Map;

/**
 * 一句话功能简述 </br>
 *
 * @author majunmin
 * @description
 * @datetime 2021-05-02 09:37
 * @since
 */
public interface Service<N, M> {
}


class ServiceImpl<A, B> implements Service<String, Integer> {
    public ServiceImpl(List<List<String>> list, Map<Double, Map<Float, Integer>> map) {
    }
}