package com.zzh.boot3;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.util.StringUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description:
 * @Author: zzh
 * @Crete 2024/4/25 20:02
 */
@Slf4j
@SpringBootTest
public class ExeclTest {

    private static final Set<String> nameSet = Set.of("程明", "汪灵燕", "丁莹", "董莉", "李舒涵", "任苗苗", "沈佳时", "杨倩倩", "杨煜凡", "张沛丞", "周莉");

    @Test
    public void test() {
        try (ExcelWriter writer = ExcelUtil.getWriter("static/result_"+ System.currentTimeMillis()+".xlsx")) {
            InputStream stream = ResourceUtil.getStream("static/跨境运营管理权限列表.xlsx");
            ExcelReader reader = ExcelUtil.getReader(stream, 0);
            List<List<String>> result = new ArrayList<>();
            List<List<Object>> readAll = reader.read();
            readAll.forEach(row -> result.add(row.stream().map(String::valueOf).filter(str -> cond(String.valueOf(str))).toList()));
            for (List<String> list : result) {
                System.out.println(list);
            }
            result.forEach(writer::writeRow);
        }
    }

    public boolean cond(String str) {
        return StringUtils.isEmpty(str) || !str.contains("(") || nameSet.stream().anyMatch(str::contains);
    }
}
