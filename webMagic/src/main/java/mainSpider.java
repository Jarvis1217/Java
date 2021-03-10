import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class mainSpider implements PageProcessor {
    private final Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    public void process(Page page) {
        page.putField("content", page.getHtml().xpath("//*[@id=\"pl_top_realtimehot\"]/table/tbody//tr/td[2]/a/text()").all());
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new mainSpider()).addUrl("https://s.weibo.com/top/summary?Refer=top_hot&topnav=1&wvr=6")
                .addPipeline(new pipeline()).run();
    }
}
