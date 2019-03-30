package edu.nju.master.graduate.dto;

import java.util.List;

/**
 * @author Daniel
 * @title: ClassificationRespDto
 * @description: 返回的已分类数据
 * @date 2019-03-29 16:58
 */
public class ClassificationRespDto {
    // 正常图片的地址
    private List<String> normalPicture;
    // 异常图片的地址
    private List<String> abnormalPicture;

    public List<String> getNormalPicture() {
        return normalPicture;
    }

    public void setNormalPicture(List<String> normalPicture) {
        this.normalPicture = normalPicture;
    }

    public List<String> getAbnormalPicture() {
        return abnormalPicture;
    }

    public void setAbnormalPicture(List<String> abnormalPicture) {
        this.abnormalPicture = abnormalPicture;
    }
}
