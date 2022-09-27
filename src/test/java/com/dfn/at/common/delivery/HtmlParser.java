package com.dfn.at.common.delivery;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;

public class HtmlParser {
    public Document loadHtml(String filePath) throws IOException {
        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8");

        return doc;
    }

    public Element getFirstElement(Document document, String selector) {
        Element element = document.select(selector).first();
        return element;
    }
}
