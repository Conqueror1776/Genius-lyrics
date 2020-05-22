import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

class Lyrics {

  public Lyrics(){

  }

  public boolean contains(String url, String title) {
    // Get the lyrics of the song
    String lyrics = this.getLyrics(url).toLowerCase();
    // Check if the title can be found in the lyrics
    if (lyrics.contains(title)) {
      System.out.println(title+ ": "+ lyrics.contains(title));
      return true;
    }
    System.out.println(title+ ": "+ lyrics.contains(title));
    return false;
  }

  public String getLyrics(String url) {
    String result = "";
    try {
      Document doc = Jsoup.connect(url).get();
      Elements lyrics = doc.select("div.lyrics");
      result = lyrics.text();
      result.replace('\n', ' ');
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}
