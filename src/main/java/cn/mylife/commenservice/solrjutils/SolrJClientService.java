package cn.mylife.commenservice.solrjutils;

import cn.mylife.modules.user.entity.SysFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangjie
 * 2019/1/19 18:26
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class SolrJClientService {

    private static SolrServer solrServer = null;
    private static String BASE_URL = "http://solr:solr@localhost:8080/solr/";


    /**
     * 获取solr服务对象
     * @return SolrServer
     */
    @SuppressWarnings("Duplicates")
    private static synchronized SolrServer getSolrServer() {

        if (solrServer == null) {
            HttpClient createClient;

            ModifiableSolrParams invariantParams = new ModifiableSolrParams();

            //遵循重定向
            invariantParams.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false);
            //基本认证用户名
            invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER, "solr");
            //基本认证密码
            invariantParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS, "solr");
            //最大允许总连接
            invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 1000);
            //如果服务器支持的话，允许压缩
            invariantParams.set(HttpClientUtil.PROP_ALLOW_COMPRESSION, true);
            //每个主机允许的最大连接
            invariantParams.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 1000);
            createClient = HttpClientUtil.createClient(invariantParams);

            solrServer = new HttpSolrServer(BASE_URL, createClient);
        }

        return solrServer;
    }

    /**
     * 创建索引
     * 注意：搜索使用的分词器和索引使用的分词器要一致
     */
    @SuppressWarnings("Duplicates")
    public void addDocument(File file, SysFile myFile) throws SolrServerException, IOException {

        SolrServer mySolrServer = SolrJClientService.getSolrServer();

        //1. 创建document对象
        SolrInputDocument document = new SolrInputDocument();

        //2.设置域 。四个域->名字，大小，位置，内容
        //id是必须有的域，而且唯一
        //id和name 在schema.xml中的fieldType必须有， 没有的话需要配置
        document.setField("id", myFile.getId());
        document.setField("title_ik", myFile.getType());

        //文件内容
        document.setField("content_ik", myFile.getName());

        //3. 创建field对象，将field添加到document对象中
        mySolrServer.add(document);
        mySolrServer.commit();
    }

    public String getContent(File file) throws IOException {

        InputStream io = null;
        String str = null;
        try {
            io = new FileInputStream(file.getPath());

            byte[] head = new byte[1024];

            String code;

            code = "gb2312";

            if (head[0] == -1 && head[1] == -2 )
            {
                code = "UTF-16";
            }
            if (head[0] == -2 && head[1] == -1 )
            {
                code = "Unicode";
            }
            if(head[0] == -17 && head[1] == -69 && head[2] == -65)
            {
                code = "UTF-8";
            }

            str = "";

            int length;

            while(-1 !=(length =  io.read(head, 0, 1024))) {
                str = new String(head,0,length,code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(io)) {
                io.close();
            }
        }

        return str;
    }

    /**
     * 删除所有
     */
    public void delete() throws SolrServerException, IOException {

        SolrServer mySolrServer = SolrJClientService.getSolrServer();

        mySolrServer.deleteByQuery("*:*", 1000);
    }

    /**
     * 删除所有
     */
    public void deleteById(String id) throws SolrServerException, IOException {

        SolrServer mySolrServer = SolrJClientService.getSolrServer();

        if (StringUtils.isNotBlank(id)) {
            mySolrServer.deleteByQuery("id:" + id, 1000);
        }
    }

    /**
     * 查询
     * @throws SolrServerException solrServer
     */
    public Map<String, Map<String, List<String>>> search(String content, Integer startPage) throws SolrServerException {

        QueryResponse response = getSolrResponse(content, startPage);

        return response.getHighlighting();
    }

    public Long getTotal(String content, Integer startPage) throws SolrServerException {
        QueryResponse solrResponse = getSolrResponse(content, startPage);
        SolrDocumentList results = solrResponse.getResults();
        return results.getNumFound();
    }

    private QueryResponse getSolrResponse(String content, Integer startPage) throws SolrServerException {

        SolrServer mySolrServer = SolrJClientService.getSolrServer();

        SolrQuery solrQuery = new SolrQuery();

        if (StringUtils.isBlank(content)) {
            solrQuery.setQuery("item_copy:*");
        } else {
            solrQuery.setQuery("item_copy:" + content);
        }

        if (Objects.isNull(startPage) || startPage <= 0) {
            startPage = 1;
        }
        solrQuery.setStart((startPage - 1) * 16);
        solrQuery.setRows(16);
        //默认域
        solrQuery.set("df", "item_copy");
        //指定查询域
//        solrQuery.set("fl", "item_copy");
        //高亮
        //1.打开开关
        solrQuery.setHighlight(true);
        //2.指定高亮域
        solrQuery.addHighlightField("title_ik, content_ik");
        //3.前缀后缀
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");
        //4.后缀
        //执行查询
        return mySolrServer.query(solrQuery);
    }


}
