package com.it.demo;

import org.I0Itec.zkclient.IZkChildListener;

import java.util.List;

/**
 * 监听节点的变化
 * @author fengqigui
 * @Description
 * @Date 2018/01/19 21:55
 */
public class ZkLisenter implements IZkChildListener{

    @Override
    public void handleChildChange(String parentPath, List<String> childrenList) throws Exception {

        System.out.println("父节点"+parentPath);
        childrenList.forEach(p ->System.out.println(p.toString()));

    }
}
