package cn.mylife.lucen;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * @author yangjie
 * @date 2018/11/27 8:01
 *
 * 打开Lucene java -jar D:\luke-with-deps\luke-with-deps.jar
 */
public class FirstLucene {



    /**
     * 创建索引
     * 注意：搜索使用的分词器和索引使用的分词器要一致
     */
    @Test
    public void test1() throws Exception {
        //1. 创建indexwriter对象(1.指定索引库的存放位置Directiry对象 2.指定一个分析器，对文档内容进行分析)
        Directory directory = FSDirectory.open(new File("E:/yangjie/index"));//指定磁盘来存储
        //Directory directory = new RAMDirectory();//保存索引到内存中（内存成为了索引库,弊端，关机就没了）
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LATEST, analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //E://jiezijlfkjsl是文件夹
        File files = new File("E:/yangjie/docs");
        //拿到文件夹里文件
        try {
            for (File file : files.listFiles()) {
                //设置域 。四个域->名字，大小，位置，内容
                //文件名字
                String fileName = file.getName();
                Field fileNameField = new TextField("fileName", fileName, Field.Store.YES);
                //文件大小
                final long sizeOf = FileUtils.sizeOf(file);
                Field fileSizeField = new LongField("fileSize", sizeOf, Field.Store.YES);
                //文件位置
                final String path = file.getPath();
                Field filePathField = new StoredField("filePathField", path);
                //文件内容
                final String content = FileUtils.readFileToString(file);
                Field fileContentField = new TextField("fileContent", content, Field.Store.NO);
                //2. 创建document对象
                //3. 创建field对象，将field添加到document对象中
                Document document = new Document();
                document.add(fileNameField);
                document.add(fileSizeField);
                document.add(filePathField);
                document.add(fileContentField);
                //4. 使用ndexwriter对象将document对象写入索引库，此过程进行索引创建，并将索引和document随想写入索引库
                indexWriter.addDocument(document);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5. 关闭indexwriter对象
            indexWriter.close();
        }
    }

    /**
     * 搜索索引
     */
    @Test
    public void testSearch() throws Exception{
        //第一步，创建一个directory对象，也就是索引库存放的位置
        Directory directory = FSDirectory.open(new File("E:/yangjie/index"));
        //第二步，创建一个indexReader对象，需要指定directory对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //第三部，创建一个indexsearch对象，需要指定indexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //第四部，创建一个TerQuery对象，指定查询的域和查询的关键词
        Query query = new TermQuery(new Term("fileName", "123"));//fileNameField域名
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
        //第七部，关闭indexReader对对象
        indexReader.close();
    }

    /**
     * 支持中文分词器
     */
    @Test
    public void testTokenStream() throws Exception {
        IKAnalyzer ikAnalyzer = null;
        try {
            ikAnalyzer = new IKAnalyzer();
            //第一个参数，域名；第二个，要分析的文本
            TokenStream tokenStream = ikAnalyzer.tokenStream("test", "高富帅是我们当今社会的新名词");
            //添加一个引用，可以获取每个关键字
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            //添加一个偏移量的引用，记录可关键词的开始位置以及结束为止
            OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
            //将指针调整到列表的头部
            tokenStream.reset();
            //遍历关键词列表，通过incrementToken办法判断列表是否结束
            while (tokenStream.incrementToken()) {
                //关机词的起始位置
                System.out.println("start->" + offsetAttribute.startOffset());
                //取关键词
                System.out.println(charTermAttribute);
                //结束位置
                System.out.println("end->" + offsetAttribute.endOffset());
            }
            tokenStream.close();
        } catch (Exception e) {

        } finally {
            ikAnalyzer.close();
        }

    }
}
