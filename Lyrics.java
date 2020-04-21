import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

class Lyrics {

  public Lyrics(){
    null;
  }

  public boolean contains(String url, String title) {
    String lyrics = this.getLyrics(url);
    for (int i = 0; i < lyrics.length() - title.length(); i++) {
      if (title.equals(lyrics.substring(i,title.length()))) {
        return true;
      }
    }
    return false;
  }

  public String getLyrics(String url) {
    try {
      Document doc = Jsoup.connect(url).get();
      Elements

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    Songs stuff = new Songs();
    System.out.println(stuff.getLyrics("https://genius.com/Drake-toosie-slide-lyrics"));
  }
}
