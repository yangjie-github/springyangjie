package cn.mylife.lucen;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

/**
 * @author yangjie
 * 2018/12/19 20:47
 *
 * 索引维护
 */
public class LuceneManage {


    public IndexWriter getIndexWriter() throws Exception {
        Directory directory = FSDirectory.open(new File("E:/yangjie/index"));//指定磁盘来存储
        //Directory directory = new RAMDirectory();//保存索引到内存中（内存成为了索引库,弊端，关机就没了）
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
        return new IndexWriter(directory, indexWriterConfig);
    }

    /**
     * 全部删除
     */
    @Test
    public void deleteAll() throws Exception {
        IndexWriter indexWriter = getIndexWriter();
        indexWriter.deleteAll();
        indexWriter.close();
    }

    /**
     * 条件删除
     */
    @Test
    public void deleteByCondition() throws Exception{
        IndexWriter indexWriter = getIndexWriter();
        TermQuery termQuery = new TermQuery(new Term("fileName", "jiejie"));//域名，值
        indexWriter.deleteDocuments(termQuery);
        indexWriter.close();
    }

    /**
     * 修改
     */
    @Test
    public void testUpdate() throws Exception {
        IndexWriter indexWriter = getIndexWriter();
        Document doc = new Document();
        doc.add(new TextField("fileN", "测试文件", Field.Store.YES));
        doc.add(new TextField("fileC", "测试文件内容", Field.Store.YES));
        indexWriter.updateDocument(new Term("fileName", "lucene"), doc, new IKAnalyzer());
        indexWriter.close();
    }

    public IndexSearcher getIndexSearcher() throws Exception {
        //第一步，创建一个directory对象，也就是索引库存放的位置
        Directory directory = FSDirectory.open(new File("E:/yangjie/index"));
        //第二步，创建一个indexReader对象，需要指定directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //第三部，创建一个indexsearch对象，需要指定indexReader对象
        return new IndexSearcher(indexReader);
    }



    /**
     * 执行查询结果
     */
    public void printResult(IndexSearcher indexSearcher, Query query) throws Exception {
        //第五步，执行查询
        TopDocs search = indexSearcher.search(query, 2);
        //第六步, 返回查询结果，遍历查询的结果并输出
        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc doc : scoreDocs) {
            int doc1 = doc.doc;//文档id
            Document doc2 = indexSearcher.doc(doc1);//返回文档
            //文件名称
            doc2.get("fileName");
            System.out.println(doc2.get("fileName"));
            //文件内容
            doc2.get("fileContent");
            System.out.println(doc2.get("fileContent"));
            //文件路径
            doc2.get("filePathField");
            System.out.println(doc2.get("filePathField"));
            //文件大小
            doc2.get("fileSize");
            System.out.println(doc2.get("fileSize"));
        }
    }

    /**
     * 查询所有
     */
    @Test
    public void testQueryAll() throws Exception {
        IndexSearcher indexSearcher = getIndexSearcher();
        Query query = new MatchAllDocsQuery();
        printResult(indexSearcher, query);
        indexSearcher.getIndexReader().close();
    }

    /**
     * 根据数值范围查询
     */
    @Test
    public void testNumericRangeQuery() throws Exception {
        IndexSearcher indexSearcher = getIndexSearcher();
        Query query = NumericRangeQuery.newLongRange("fileSize", 100L, 200L, true, true);
        printResult(indexSearcher, query);
        indexSearcher.getIndexReader().close();
    }

    /**
     * 布尔组合查询
     */
    @Test
    public void testBooleanQuery() throws Exception {
        IndexSearcher indexSearcher = getIndexSearcher();
        BooleanQuery booleanClauses = new BooleanQuery();
        TermQuery termQuery1 = new TermQuery(new Term("fileName", "apache"));
        TermQuery termQuery2 = new TermQuery(new Term("fileName", "lucene"));
        //MUST必须, SHOULD应该
        booleanClauses.add(termQuery1, BooleanClause.Occur.MUST);
        booleanClauses.add(termQuery2, BooleanClause.Occur.MUST);
        printResult(indexSearcher, booleanClauses);
        indexSearcher.getIndexReader().close();
    }




    /**
     * 查询语法查询
     */
    @Test
    public void testQueryParser() throws Exception {
        IndexSearcher indexSearcher = getIndexSearcher();
        //默认查询的域， 使用的分析器
        QueryParser queryParser = new QueryParser("fileName", new IKAnalyzer());
        //参数是语法*：* ，附一个*表示域，第二个表示值
        //"apache",会使用默认域
        //"fileContent:apache", 不使用默认域
        Query query = queryParser.parse("*：*");
        //java is apache会用IK分词器分词并查询
//        Query parse = queryParser.parse("fileContent：java is apache");
        printResult(indexSearcher, query);
        //打印查询语法
        System.out.println(query);
        indexSearcher.getIndexReader().close();
    }


    /**
     * 条件解析查询，多个默认查询域搜索
     */
    @Test
    public void testMultiFieldQueryParser() throws Exception {
        IndexSearcher indexSearcher = getIndexSearcher();
        String[] fields = {"fileName", "fileContent"};
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(fields, new IKAnalyzer());
        Query query = multiFieldQueryParser.parse("*：*");
        printResult(indexSearcher, query);
        indexSearcher.getIndexReader().close();
    }

}
