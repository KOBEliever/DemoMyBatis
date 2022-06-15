package com.cqu.mybatis.vo;

import com.cqu.mybatis.pojo.Category;
import com.cqu.mybatis.pojo.Doc;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryAndDoc implements Serializable {
    private Category category;
    private List<Doc> docList;
}
