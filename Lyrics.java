import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Lyrics {
  public static void main(String args[]) {
    try {
      // Here we create a document object and use JSoup to fetch the website
      Document doc = Jsoup.connect("https://genius.com/#top-songs").get();

      // With the document fetched, we use JSoup's title() method to fetch the title
      System.out.printf("Title: %s\n", doc.title());

    // In case of any IO errors, we want the messages written to the console
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
