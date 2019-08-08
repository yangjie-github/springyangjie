package cn.solrjmanagetest;

import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author yangjie
 * 2018/12/27 22:52
 *
 * solrJ管理  都需要提交事务
 * 添加
 * 删除
 * 修改
 * 查询
 */
public class SolrJManage {

    /**
     * 测试会出错，需要导入包
     */
    @Test
    public void add() throws Exception {

        HttpClient createClient;

        ModifiableSolrParams invariantParams = new ModifiableSolrParams();
        invariantParams.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false); //遵循重定向
        invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER, "solr"); //基本认证用户名
        invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, "solr");//基本认证密码
        invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 1000);//最大允许总连接
        invariantParams.set(HttpClientUtil.PROP_ALLOW_COMPRESSION, true);//如果服务器支持的话，允许压缩
        invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 1000);//每个主机允许的最大连接
        createClient = HttpClientUtil.createClient(invariantParams);


        String baseURL = "http://solr:solr@localhost:8080/solr/";
        SolrServer solrServer = new HttpSolrServer(baseURL, createClient);
        //默认添加到collection1中；
//        String baseURL = "http://localhost:8080/solr/colletion2";
        //单机版连接, 集群new cloudServer
        SolrInputDocument document = new SolrInputDocument();
        //id是必须有的域，
        //id和name 在schema.xml中的fieldType必须有， 没有的话需要配置
        document.setField("id", "yangjie");
        document.setField("name", "高富帅");
        solrServer.add(document);
        solrServer.commit();
    }


    /**
     * 删除
     */
    @Test
    public void delete() throws Exception {
        HttpClient createClient;

        ModifiableSolrParams invariantParams = new ModifiableSolrParams();
        invariantParams.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false); //遵循重定向
        invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER, "solr"); //基本认证用户名
        invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, "solr");//基本认证密码
        invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 1000);//最大允许总连接
        invariantParams.set(HttpClientUtil.PROP_ALLOW_COMPRESSION, true);//如果服务器支持的话，允许压缩
        invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 1000);//每个主机允许的最大连接
        createClient = HttpClientUtil.createClient(invariantParams);


        String baseURL = "http://solr:solr@jiajielove.com/solr/";
        SolrServer solrServer = new HttpSolrServer(baseURL, createClient);

        //删除所有
        solrServer.deleteByQuery("*:*", 1000);
//        solrServer.deleteByQuery("id:yangjie", 1000);
        //条件删除
        solrServer.commit();
    }

    /**
     * 更新
     */
    @Test
    public void update() throws Exception {
        //和添加一样，只要id不一样就是添加，一样就是更新
        String baseURL = "http://localhost:8080/solr/";
        SolrServer solrServer = new HttpSolrServer(baseURL);
        SolrInputDocument document = new SolrInputDocument();
        document.setField("id", "yangjie");
        document.setField("name", "高富帅");
        solrServer.add(document);
        solrServer.commit();
    }

    /**
     * 查询
     */
    @Test
    public void search() throws Exception {

        HttpClient createClient;

        ModifiableSolrParams invariantParams = new ModifiableSolrParams();
        invariantParams.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false); //遵循重定向
        invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER, "solr"); //基本认证用户名
        invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, "solr");//基本认证密码
        invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 1000);//最大允许总连接
        invariantParams.set(HttpClientUtil.PROP_ALLOW_COMPRESSION, true);//如果服务器支持的话，允许压缩
        invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 1000);//每个主机允许的最大连接
        createClient = HttpClientUtil.createClient(invariantParams);
//    private static HttpSolrClient client = new HttpSolrClient("http://ip:8089/solr7/coll",createClient);


        String baseURL = "http://localhost:8080/solr/";
        SolrServer solrServer = new HttpSolrServer(baseURL, createClient);
        //关键词输入台灯，价格区间查询，价格排序， 分页开始行，每页条数， 高亮， 默认域， 查询指定域
        SolrQuery solrQuery = new SolrQuery();
        //设置条件
        //关键词, 关键词是q,
//        solrQuery.set("q", "loginName:han");
        solrQuery.setQuery("loginName:han");//效果同上

        //过滤条件
        solrQuery.set("fq", "loginName:han3cgs");
        //价格区间
//        solrQuery.set("fq", "_version_:[* TO 10]");
        //排序
//        solrQuery.addSort("_version_", SolrQuery.ORDER.asc);
        //分页
        solrQuery.setStart(0);
        solrQuery.setRows(5);
        //默认域
        solrQuery.set("df", "loginName");
        //指定查询域
        solrQuery.set("fl", "id,loginName");
        //高亮
        //1.打开开关
        solrQuery.setHighlight(true);
        //2.指定高亮域
        solrQuery.addHighlightField("loginName");
        //3.前缀后缀
        solrQuery.setHighlightSimplePre("<span style='solor:red'>");
        solrQuery.setHighlightSimplePost("</span>");
        //4.后缀
        //执行查询
        QueryResponse response = solrServer.query(solrQuery);

        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        //第一个map， key id, value map
        //第二个map， key 域名， value list
        //list list.get(0)
        //文档结果集
        SolrDocumentList docs = response.getResults();
        //总条数
        long numFound = docs.getNumFound();
        System.out.println(numFound);
        /*
        "passWord": "c7f07f71983f208138ae2a63924c08c4",
        "loginName": "han3cgs",
        "id": "202b3305-f006-11e8-97f4-fa163e19e406",
        "email": "1036536054@qq.com",
        "_version_": 1620922070565126100
        */
        for (SolrDocument doc : docs) {
            System.out.println(doc.get("passWord"));
            System.out.println(doc.get("loginName"));
            System.out.println(doc.get("id"));
            System.out.println(doc.get("email"));
            Map<String, List<String>> map = highlighting.get(doc.get("id"));
            List<String> loginName = map.get("loginName");
            System.out.println(loginName.get(0));
        }

    }

}
