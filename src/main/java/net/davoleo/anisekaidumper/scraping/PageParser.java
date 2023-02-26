package net.davoleo.anisekaidumper.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Pattern;

public abstract class PageParser<T> {

    String pageUrl;

    public PageParser(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    protected abstract T parse(Document document);

    protected Pattern getPattern(){
        return null;
    }

    public T requestAnimePageUrl()
    {

        if(getPattern() != null)
            if(!getPattern().matcher(this.pageUrl).find())
                return null;
        try {
            return parse(Jsoup.connect(this.pageUrl).get());
        } catch (Exception e) {
            return null;
        }
    }

}
