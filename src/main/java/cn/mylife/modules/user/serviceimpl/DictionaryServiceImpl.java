package cn.mylife.modules.user.serviceimpl;

import cn.mylife.modules.user.dao.DictionaryMapper;
import cn.mylife.modules.user.entity.Dictionary;
import cn.mylife.modules.user.entity.DictionaryExample;
import cn.mylife.modules.user.servicein.DictionaryServiceIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author yangjie
 * 2019/1/29 20:19
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DictionaryServiceImpl implements DictionaryServiceIn {

    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public synchronized void add(Dictionary dictionary) {
        dictionary.setDelFlag(false);
        dictionary.setCreateDate(new Date());
        dictionary.setUpdateDate(new Date());
        dictionaryMapper.insert(dictionary);
    }

    @Override
    public void delete(String id) {
        dictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Dictionary dictionary) {

    }

    @Override
    public Dictionary findById(String id) {
        return null;
    }

    @Override
    public List<Dictionary> findList() {
        return dictionaryMapper.selectByExample(new DictionaryExample());
    }

    @Override
    public List<Dictionary> findListByType() {

        return dictionaryMapper.selectByExample(new DictionaryExample());
    }
}
