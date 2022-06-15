package com.cqu.mybatis.vo;

import com.cqu.mybatis.pojo.Category;
import com.cqu.mybatis.pojo.Doc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DocAndCategory implements Serializable {
    private Doc doc;
    private Category category;
}
