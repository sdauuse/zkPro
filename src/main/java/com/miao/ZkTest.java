package com.miao;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.util.List;


/**
 * @author miaoyin
 * @date 2021/2/3 - 18:59
 * @commet:
 */
public class ZkTest {

    //先要在linux开启zookeeper服务
    @Test
    public void test() throws Exception {
        //1.创建zookeeper连接
        ZooKeeper zk = new ZooKeeper("192.168.56.101:2181", 300000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println("触发了" + watchedEvent.getType() + "的事件");
            }
        });
        //2.创建父节点 （节点名，给节点赋值需要用byte数据，当前节点所能访问的权限 都能访问，当前节点的类型）
        /*String path = zk.create("/test1", "saassasa".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("打印路径" + path);*/
        //3.创建子节点
       /* String childPath = zk.create("/test1/children", "childerenvalue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(childPath);*/
        //4.获取节点中的值
       /* byte[] data = zk.getData("/test1/children", false, null);
        System.out.println("获取节点中的值:" + data);*/

        List<String> child = zk.getChildren("/test1", false);
        for (String s : child) {
            System.out.println(s);
        }
        //5.修改节点中的值
        /*Stat stat = zk.setData("/tes1/children", "updateData".getBytes(), -1);
        System.out.println("修改节点中的值:" + stat);*/
        //6.判断某个节点是否存在
        byte[] data = zk.getData("/test1", true, new Stat());
        System.out.println(new String(data));
        //7.删除节点
        zk.delete("/test1/children",-1);
    }


}
