package com.cqu.mybatis.dao;

import com.cqu.mybatis.pojo.Category;
import com.cqu.mybatis.pojo.Doc;
import com.cqu.mybatis.vo.CategoryAndDoc;
import com.cqu.mybatis.vo.DocAndCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DocDao {
    Doc getDocById(long docId );
    @Select("select * from doc where doc_id = #{docId}")
    Doc getDocByIdComm(long docId);
    Doc getDocByIdObj(Doc doc);
    int insertDoc(Doc doc);
    int updateDoc(Doc doc);
    int updateDocParam(@Param("docId") int docId,
                       @Param("title") String docTitle,
                       @Param("doc") Doc doc);
    int deleteDoc(Doc doc);
    List<Doc> getDocList();
    List<Doc> getDocListParamMap(HashMap<String,Object> map);
    /**
     * 高级映射, 一对一
     * @return
     */
    DocAndCategory getDocAndCategory(long docId);
    List<DocAndCategory> getDocAndCategoryList();
    /**
     * 高级映射,一对多
     * @return
     */
    CategoryAndDoc getCategoryAndDoc(long categoryId);
    List<CategoryAndDoc> getCategoryAndDocList();
    //子查询
    List<DocAndCategory>  getCategoryAndDocAll();

    //动态sql
    List<Doc> getDocsByCondition(Doc doc);
    /**
     * 动态sql set-if
     * @param doc
     * @return
     */
    int updateDocByCondition(Doc doc);
    /**
     * forEach循环-array
     * @param docIds
     * @return
     */
    List<Doc> getDocsByForEach(long[] docIds);
    /**
     * forEach循环-list
     * @param docIds
     * @return
     */
    List<Doc> getDocsByForEachList(List<Long> docIds);
    /**
     * forEach循环-map
     * @param docIds
     * @return
     */
    List<Doc> getDocsByForEachMap(Map<String,Object> docIds);
    /**
     * choose
     * @param doc
     * @return
     */
    List<Doc> getDocsByChoose(Doc doc);
    /**
     * 分页
     * @param doc
     * @return
     */
    List<Doc> getDocsByTitleLimit(@Param("doc") Doc doc,
                                  @Param("start") int start,
                                  @Param("limit") int limit);
}
