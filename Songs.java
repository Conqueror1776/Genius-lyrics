import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Connection;

class Songs {

  // Number of songs parsed through
  Double song_count;

  // Songs that contain their title in the lyrics
  Double contain_count;

  // The percentage of songs that contain their title in their lyrics
  Double percentage;
  
  /*
  * Empty Constructor
  */
  public Songs(){
    song_count = 0.;
    contain_count = 0.;
  }

  /*
  * This method returns the percentage of songs on the top charts whose
  * title is contained somewhere in the song
  */
  public double getPercent(String url) {
    try {
      // Create a new Lyrics Comparer Object
      Lyrics comparer = new Lyrics();
      // Create a document and fetch the website
      Document doc = Jsoup.connect(url).get();

      // Find all elements "a", some of which are Songs
      Elements songs = doc.select("a");
      for (Element song : songs) {

        //Check if the current element is a song
        if (this.checkElement(song)) {
          this.song_count+= 1;
          // Check if the title is contained in the lyrics
          if (this.contains(song)) {
            this.contain_count+=1;
          }
        }
      }
      // Calculate and return the percentage
      this.percentage = this.contain_count / this.song_count;
      return this.percentage;

      // In case of any IO errors, we want the messages written to the console
      } catch (IOException e) {
      e.printStackTrace();
      }
      return 0.0;
  }

  public boolean checkElement(Element thing) {
    String attribute = thing.attr("class");
    if (attribute.equals("PageGriddesktop-a6v82w-0 ChartItemdesktop__Row-sc-3bmioe-0 qsIlk")) {
      return true;
    }
    return false;
  }

  public boolean contains(Element song) {
    // Create a new Lyrics Comparer Object
    Lyrics comparer = new Lyrics();
    // Get the title and url to the lyrics for that song
    String title = song.getElementsByClass("ChartSongdesktop__Title-sc-18658hh-3 fODYHn").text();
    String lyrics_url = song.attr("href");
    title = title.toLowerCase();
    // If the title is contained in the lyrics, return true;
    if (comparer.contains(lyrics_url, title)) {
      return true;
    }
    return false;
  }

  public static void main(String args[]) {
    Songs test = new Songs();
    System.out.println(test.getPercent("https://genius.com/#top-songs"));
  }
}
