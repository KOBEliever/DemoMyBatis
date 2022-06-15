import com.cqu.mybatis.dao.DocDao;
import com.cqu.mybatis.pojo.Doc;
import com.cqu.mybatis.vo.CategoryAndDoc;
import com.cqu.mybatis.vo.DocAndCategory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import util.DateUtil;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {

    //1.将sqlSessionFactory声明为静态变量
    private static SqlSessionFactory sqlSessionFactory;
    //2.在静态代码块里使用sqlSessionFactoryBuilder创建sqlSessionFactory
    static {
        try {
            //加载mybatis核心配置文件
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //将读取的输入流放到sqlSessionFactoryBuilder里创建sqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //3.测试方法
    @org.junit.Test
    public void testOne(){
        //1.创建sqlSession 事务:默认不会自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.调用dao层方法 需要指定到dao层方法的完整限定名
        Doc doc = sqlSession.selectOne("com.cqu.mybatis.dao.DocDao.getDocById", 104);
        //3.展示获取的数据
        System.out.println(doc);
        //4.关闭sqlSession
        sqlSession.close();
    }
    @org.junit.Test
    public void testOneMapper(){
        //1.创建sql会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.使用反射的方式创建dao
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        Doc docById = mapper.getDocById(145);
        //3.展示数据
        System.out.println(docById);
        //4.关闭sql会话
        sqlSession.close();
    }
    @org.junit.Test
    public void testOneComm(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        Doc docByIdComm = mapper.getDocByIdComm(109);
    }
    @org.junit.Test
    public void testDocParamObj(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        Doc doc=new Doc();
        doc.setDocId(110);
        Doc docObj = mapper.getDocByIdObj(doc);
        System.out.println();
        sqlSession.close();
    }
    @org.junit.Test
    public void testInsertDoc(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        Doc doc=new Doc();
        doc.setCategoryId(2);
        doc.setDocTitle("测试");
        doc.setDocSummary("wwwwww");
        doc.setDocUploaduser("wow");
        doc.setDocCreatedate(DateUtil.getStringDate());
        doc.setDocModifyuser("abc");
        doc.setDocModifydate(DateUtil.getStringDate());
        doc.setDocImage("1-1.png");
        int i = mapper.insertDoc(doc);
        if(i==1){
            System.out.println("ok");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @org.junit.Test
    public void testUpdateDoc(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        Doc doc=new Doc();
        doc.setDocTitle("测试修改");
        doc.setDocId(444);
        int i = mapper.updateDoc(doc);
        if(i==1){
            System.out.println("ok");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    @org.junit.Test
    public void updateDocParam() {
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.封装修改数据
        Doc doc = new Doc();
        doc.setDocSummary("多参数多类型概要");
        //3.调用方法
        int i = mapper.updateDocParam(444, "多参数,多类型修改标题", doc);
        //4.展示结果
        if (i > 0) {
            System.out.println("修改成功");
        }
        //5. 关闭
        sqlSession.close();
    }
    @org.junit.Test
    public void deleteDoc(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.调用方法
        Doc doc = new Doc();
        doc.setDocId(444);
        int i = mapper.deleteDoc(doc);
        if (i > 0){
            System.out.println("删除成功");
        }
    }
    @org.junit.Test
    public void getDocList(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.调用方法
        List<Doc> docList=mapper.getDocList();
        System.out.println(docList);
        sqlSession.close();
    }
    @org.junit.Test
    public void getDocParamMap(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.封装查询参数
        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("docTitle","史莱姆");
        map.put("docSummary","发现自己");
        //4.调用方法
        List<Doc> docListParamMap=mapper.getDocListParamMap(map);
        System.out.println(docListParamMap);
        sqlSession.close();
    }
    //高级映射一对一
    @org.junit.Test
    public void getDocAndCategory(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //4.调用方法
        DocAndCategory docAndCategory=mapper.getDocAndCategory(104);
        System.out.println(docAndCategory.getCategory().getCategoryName());
        sqlSession.close();
    }
    @org.junit.Test
    public void getDocAndCategoryList(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //4.调用方法
        List<DocAndCategory> docAndCategoryList=mapper.getDocAndCategoryList();
        for(int i=0;i<docAndCategoryList.size();i++){
            System.out.println(docAndCategoryList.get(i));
        }
        sqlSession.close();
    }
    //高级映射一对多
    @org.junit.Test
    //TODO categoryID=0?
    public void getCategoryAndDoc(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //4.调用方法
        CategoryAndDoc categoryAndDoc=mapper.getCategoryAndDoc(2);
        System.out.println(categoryAndDoc);
        sqlSession.close();
    }
    @org.junit.Test
    public void getCategoryAndDocList(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //4.调用方法
        List<CategoryAndDoc> categoryAndDocs=mapper.getCategoryAndDocList();
        System.out.println(categoryAndDocs);
        System.out.println(categoryAndDocs.size());
        sqlSession.close();
    }
    @org.junit.Test
    //子查询
    public void getDocAndCategoryAll(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //4.调用方法
        List<DocAndCategory> docAndCategoryList =mapper.getCategoryAndDocAll();
        System.out.println(docAndCategoryList);
        sqlSession.close();
    }
    @org.junit.Test
    public void getDocsByCondition(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //封装查询参数
        Doc doc = new Doc();
        doc.setDocTitle("神域");
        //4.调用方法
        List<Doc> docList = mapper.getDocsByCondition(doc);
        System.out.println(docList);
        sqlSession.close();
    }
    //set if where
    @org.junit.Test
    public void updateDocsByCondition(){
        //1.开启会话
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //2.获取dao层
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //封装修改参数
        Doc doc = new Doc();
        doc.setDocId(104);
        doc.setDocTitle("测试动态sql");
        //4.调用方法
        int i = mapper.updateDocByCondition(doc);
        if(i>0){
            System.out.println("wow");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * forEach-array
     */
    @org.junit.Test
    public void getDocsByForEachArray(){
        //1.开会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.获取dao
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.封装查询参数
        long[] docIds = {396, 397, 398, 399};
        //4.调用方法查询
        List<Doc> docsByForEach = mapper.getDocsByForEach(docIds);
        //5.展示
        System.out.println(docsByForEach.size());
        //6.关闭会话
        sqlSession.close();
    }
    /**
     * forEach-list
     */
    @org.junit.Test
    public void getDocsByForEachList(){
        //1.开会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.获取dao
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.封装查询参数
        List<Long> docIds = new ArrayList<Long>();
        docIds.add(396l);
        docIds.add(397l);
        docIds.add(398l);
        docIds.add(399l);
        //4.调用方法查询
        List<Doc> docsByForEach = mapper.getDocsByForEachList(docIds);
        //5.展示
        System.out.println(docsByForEach.size());
        //6.关闭会话
        sqlSession.close();
    }
    /**
     * forEach-map
     */
    @org.junit.Test
    public void getDocsByForEachMap(){
        //1.开会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.获取dao
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.封装查询参数
        Map<String,Object> docIds = new HashMap<String,Object>();
        docIds.put("title","小埋");
        ArrayList<Long> longs=new ArrayList<Long>();
        longs.add(114l);
        longs.add(144l);
        longs.add(174l);
        longs.add(204l);
        docIds.put("docIds",longs);
        //4.调用方法查询
        List<Doc> docsByForEach = mapper.getDocsByForEachMap(docIds);
        //5.展示
        System.out.println(docsByForEach.size());
        //6.关闭会话
        sqlSession.close();
    }
    /**
     * choose
     */
    @org.junit.Test
    public void getDocsByChoose(){
        //1.开会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.获取dao
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.调用方法查询
        Doc doc = new Doc();
        doc.setDocId(300l);
        List<Doc> docsByChoose = mapper.getDocsByChoose(doc);
        //5.展示
        System.out.println(docsByChoose);
        //6.关闭会话
        sqlSession.close();
    }
    /**
     * 查询，分页
     */
    @org.junit.Test
    public void getDocsLimit(){
        //1.开会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.获取dao
        DocDao mapper = sqlSession.getMapper(DocDao.class);
        //3.调用方法查询
        Doc doc = new Doc();
        doc.setDocTitle("友人");
        List<Doc> docsLimit = mapper.getDocsByTitleLimit(doc,0,5);
        //5.展示
        System.out.println(docsLimit);
        //6.关闭会话
        sqlSession.close();
    }
}
