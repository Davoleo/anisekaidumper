package net.davoleo.anisekaidumper.scraping;

import javafx.concurrent.Task;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

public abstract class PageParser<T> extends Task<T> {

    String pageUrl;

    public PageParser(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    protected abstract T parse(Document document) throws Exception;

    protected Pattern getPattern(){
        return null;
    }


    @Override
    protected T call() throws Exception {
        if(getPattern() != null)
            if(!getPattern().matcher(this.pageUrl).find())
                return null;

        return parse(Jsoup.connect(this.pageUrl).get());
    }
}
