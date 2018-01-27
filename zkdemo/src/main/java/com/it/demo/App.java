package com.it.demo;

import com.it.entity.User;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * Hello world!
 *
 */
public class App 
{

    /**
     * 创建节点
     * @param args
     */
    public static void main( String[] args ){

        String zkServer = "localhost:2181,localhost:2182,localhost:2183";
        ZkClient zkClient = new ZkClient(zkServer, 10000, 10000, new SerializableSerializer());
        System.out.println( "connected OK"+zkClient);
        User user = new User(1, "lisi", "123456");
        /**创建节点
         * 参数：
         *  path(节点名字):
         *  data(数据):任意类型，但是要实现Serializable
         *  CreateMode(模式):临时节点,持久节点,顺序节点，非顺序节点
         *  ACL：权限
         */

        String path = zkClient.create("/test-node", user, CreateMode.PERSISTENT);
        System.out.println("path"+ path);




    }

    /**
     * 获取指定节点的数据
     */
    @Test
    public void testReadData(){
        String zkServer = "localhost:2181,localhost:2182,localhost:2183";
        ZkClient zkClient = new ZkClient(zkServer, 10000, 10000, new SerializableSerializer());
        User user = zkClient.readData("/test-node");
        System.out.println(user.getName());

    }

    /**
     * 判断节点是否存在，
     * 删除节点
     */
    @Test
    public void testExistsNode(){
        String zkServer = "localhost:2181,localhost:2182,localhost:2183";
        ZkClient zkClient = new ZkClient(zkServer, 10000, 10000, new SerializableSerializer());
        boolean  whetherExistsNode = zkClient.exists("/test-node");
        if(whetherExistsNode){
            // 删除节点
            zkClient.delete("/test-node");
            System.out.println("已删除节点");
        }else {
            System.out.println("/test-node,不存在");
        }

    }


    /**
     * 更新节点上的数据
     */
    @Test
    public void updateData(){
        String zkServer = "localhost:2181,localhost:2182,localhost:2183";
        ZkClient zkClient = new ZkClient(zkServer, 10000, 10000, new SerializableSerializer());
        User user = new User(1, "李四", "123456");
        zkClient.create("/test-node", user, CreateMode.PERSISTENT);
        User userRead = zkClient.readData("/test-node");
        System.out.println("reade---:"+userRead);

        user.setName("王五");
        // 更新节点上的数据
        zkClient.writeData("/test-node", user);


        User userRead2 =zkClient.readData("/test-node");


        System.out.println("reade2--:"+userRead2);

    }


    /**
     *注册子节点监听事件
     */
    @Test
    public void lisentnerZk() throws InterruptedException {
        String zkServer = "localhost:2181,localhost:2182,localhost:2183";
        ZkClient zkClient = new ZkClient(zkServer, 10000, 10000, new SerializableSerializer());
        User user = new User(1, "李四", "123456");
        zkClient.create("/test-node1", user, CreateMode.PERSISTENT);

        // 注册监听事件
        zkClient.subscribeChildChanges("/test-node1", new ZkLisenter());
        Thread.sleep(Integer.MAX_VALUE);

    }

    /**
     *注册子节点监听事件
     */
    @Test
    public void lisentnerZkData() throws InterruptedException {
        String zkServer = "localhost:2181,localhost:2182,localhost:2183";
        ZkClient zkClient = new ZkClient(zkServer, 10000, 10000, new SerializableSerializer());
        User user = new User(1, "李四", "123456");
        zkClient.create("/test-node2", user, CreateMode.PERSISTENT);

        // 注册监听事件
        zkClient.subscribeDataChanges("/test-node2", new ZKDataLisenter());
        Thread.sleep(5000);
        zkClient.writeData("/test-node2", 123456);
        Thread.sleep(Integer.MAX_VALUE);

    }

}
