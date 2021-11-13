package ch07.after;

public class p7_25 {
    public static void main(String[] args) {
        Parseable parser = ParserManager.getParser("XML");
        parser.parse("document.xml");
        parser = ParserManager.getParser("HTML");
        parser.parse("document2.html");
    }
}
interface Parseable {
    // 구문분석 작업
    public abstract void parse(String filename);
}
class ParserManager {
    // 리턴 타입이 Parseable 인터페이스이므로 구현체를 리턴해야 함
    public static Parseable getParser(String type){
        if(type.equals("XML")){
            return new XMLParser();
        } else {
            Parseable p = new HTMLParser();
            return p;
        }
    }
}
class XMLParser implements Parseable {

    @Override
    public void parse(String filename) {
        System.out.println("XML 파일인 " + filename);
    }
}
class HTMLParser implements Parseable {

    @Override
    public void parse(String filename) {
        System.out.println("HTML 파일인 " + filename);
    }
}