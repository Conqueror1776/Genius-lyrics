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
          // Fetch the website of the lyrics of the song
          Document lyric_page = Jsoup.connect(lyrics_url).get();
          System.out.println(title + " " + lyric_page.title());
        }
      }

    // In case of any IO errors, we want the messages written to the console
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      Document doc2 = Jsoup.connect("https://genius.com/Drake-toosie-slide-lyrics").get();
      System.out.println(doc2.title());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
