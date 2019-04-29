package com.mmall.param;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 描述:
 *
 * @author xvanning
 * @create 2019-04-26 10:41
 */
@Data
public class TestVo {
    @NotBlank
    private String msg;

    @NotNull
    private Integer id;

    @NotEmpty
    private List<String> list;
}