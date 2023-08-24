import java.util.List;

public class JSONObject {
    private String content;



    public JSONObject(String content) {
        this.content = content;
    }

    public String findRates(String find){
        String result = "";
        int index = getContent().indexOf(find);
        String substring = getContent().substring(index);
        int index2 = substring.indexOf("value");
        String substring2 = substring.substring(index2+7);
        int index3 = substring2.indexOf("}");
        result = substring2.substring(0,index3);
        return result;
    }

    public String getContent() {
        return this.content;
    }
}
