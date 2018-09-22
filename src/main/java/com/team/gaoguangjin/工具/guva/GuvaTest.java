package com.team.gaoguangjin.工具.guva;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Slf4j
public class GuvaTest {
    public static void main(String[] args) {
        log.info(StringUtils.join(Arrays.asList("1", "2", "3"), ","));

        //函数式编程  把List<String> 类型转换成List<Integer>类型
        Function<String, Integer> square = new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String s) {
                return Integer.parseInt(s);
            }
        };

        Collection<Integer> list = Collections2.transform(Arrays.asList("4", "5", "6"), square);
        List<Integer> dd = Arrays.asList((Integer[])list.toArray());
        log.info(""+ Arrays.asList(list.toArray()).get(0));

    }
}
