import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

class Lyrics {
  public static void main(String args[]) {
    try {
      // Here we create a document object and use JSoup to fetch the website
      Document doc = Jsoup.connect("https://genius.com/#top-songs").get();

      // With the document fetched, we use JSoup's title() method to fetch the title
      System.out.printf("Title: %s\n", doc.title());

      Elements songs = doc.select("a");
      for (Element song : songs) {
        String attribute=song.attr("class");
        if(attribute.equalsIgnoreCase("PageGriddesktop-a6v82w-0 ChartItemdesktop__Row-sc-3bmioe-0 qsIlk")){
          String title = song.getElementsByClass("ChartSongdesktop__Title-sc-18658hh-3 fODYHn").text();
          String lyrics_url = song.attr("href");
          System.out.println(title + " "+ lyrics_url);
        }
      }
      Connection.Response execute = Jsoup.connect("https://genius.com/#top-songs")
        .header("Content-Type", "application/json")
        .execute();
    // In case of any IO errors, we want the messages written to the console
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
