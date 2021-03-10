import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;

public class pipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        ArrayList<String> fields = resultItems.get("content");
        for (String field : fields) {
            System.out.println(field);
        }
    }
}
