package com.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/6.
 */
public class ListDataBean {
    private int imgHeight;

    public int getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public ListDataBean(int imageUrl, String title, String subTitle) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.subTitle = subTitle;
    }

    public ListDataBean() {
    }

    public int getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    private int imageUrl;
    private String title;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSubTitle()
    {
        return subTitle;
    }

    public void setSubTitle(String subTitle)
    {
        this.subTitle = subTitle;
    }

    private String subTitle;



    //TODO 生成RecyclerView填充数据

    public static List<ListDataBean> SetListDatas()
    {
        List<ListDataBean> listDatas=new ArrayList<>();
        for(int i=0;i<8;i++)
        {
            ListDataBean listDataBean=new ListDataBean();
            listDataBean.setImageUrl(R.drawable.shitou);
            listDataBean.setTitle("今日头条");
            listDataBean.setSubTitle("该布局时已经进入了一个暂时分离状态子视图");
            listDatas.add(listDataBean);

            ListDataBean listDataBean2=new ListDataBean();
            listDataBean2.setImageUrl(R.drawable.shitou);
            listDataBean2.setTitle("腾讯新闻");
            listDataBean2.setSubTitle("在一个呼叫中使用附加子视图索引 getChildAt(int)。对比同位置");
            listDatas.add(listDataBean2);
        }
        return listDatas;
    }
    //TODO 生成RecyclerView瀑布流的填充数据
    public static List<ListDataBean> SetStaggeredListDatas()
    {
        List<ListDataBean> listDatas=new ArrayList<>();
        for(int i=0;i<18;i++)
        {
            ListDataBean listDataBean=new ListDataBean();
            //生成随机高度
            int max=450;
            int min=230;
            Random random = new Random();
            int height = random.nextInt(max)%(max-min+1) + min;
            listDataBean.setImgHeight(height+260);

            listDataBean.setImageUrl(R.drawable.shitou);
            listDataBean.setTitle("今日头条"+i);
            listDataBean.setSubTitle("子标题");
            listDatas.add(listDataBean);
        }
        return listDatas;
    }
}
