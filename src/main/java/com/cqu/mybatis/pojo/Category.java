package com.cqu.mybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    private long categoryId;
    private String categoryName;

}
