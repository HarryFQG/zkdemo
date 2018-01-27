package com.it.demo;

import org.I0Itec.zkclient.IZkDataListener;

/**
 * 监听节点的数据的变化
 * @author fengqigui
 * @Description
 * @Date 2018/01/19 22:07
 */
public class ZKDataLisenter implements IZkDataListener {
    /**
     * 数据的变化
     * @param path ：路径
     * @param data ：数据
     * @throws Exception
     */
    @Override
    public void handleDataChange(String path, Object data) throws Exception {
        System.out.println("--path:"+path+"--"+data);
    }

    /**
     *数据的删除
     * @param path ：路径
     * @throws Exception
     */
    @Override
    public void handleDataDeleted(String path) throws Exception {

    }
}
