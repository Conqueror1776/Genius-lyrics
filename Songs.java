import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

class Songs {

  public static void main(String args[]) {
    try {
      count = 0;
      Lyrics comparer = new Lyrics();
      // Here we create a document object and use JSoup to fetch the website
      Document doc = Jsoup.connect("https://genius.com/#top-songs").get();

      // Find all elements "a" (some are songs)
      Elements songs = doc.select("a");
      for (Element song : songs) {

        // Check if the current element is a song
        String attribute = song.attr("class");
        if(attribute.equalsIgnoreCase("PageGriddesktop-a6v82w-0 ChartItemdesktop__Row-sc-3bmioe-0 qsIlk")){
          // Get the title and url to the lyrics for that song
          String title = song.getElementsByClass("ChartSongdesktop__Title-sc-18658hh-3 fODYHn").text();
          String lyrics_url = song.attr("href");
          title = title.toLowerCase();
          if (comparer.compareLyrcis(lyrcis_url, title)) {
            count++;
          }
        }
      }
      return count;
    // In case of any IO errors, we want the messages written to the console
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
