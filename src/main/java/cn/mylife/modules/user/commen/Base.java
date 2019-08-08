package cn.mylife.modules.user.commen;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author yangjie
 * @date 2018/12/4 16:40
 */
public class Base implements Serializable {

    private String id;

    private Boolean delFlag;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Objects.equals(id, base.id) &&
                Objects.equals(delFlag, base.delFlag) &&
                Objects.equals(createDate, base.createDate) &&
                Objects.equals(updateDate, base.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, delFlag, createDate, updateDate);
    }

    @Override
    public String toString() {
        return "Base{" +
                "id='" + id + '\'' +
                ", delFlag=" + delFlag +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
